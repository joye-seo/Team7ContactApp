package com.example.team7contactapp

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.view.View
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.team7contactapp.data.MypageConText
import com.example.team7contactapp.databinding.FragmentModifyMyPageBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import java.io.File
import java.io.FileOutputStream


class ModifyMyPageFragment : DialogFragment() {

    private lateinit var targetFile: File // 필드로 선언


    private var listener: OnDataModifiedListener? = null

    fun setOnDataModifiedListener(listener: OnDataModifiedListener) {
        this.listener = listener
    }



    //수정을 바로 반영하기 위한 인터페이스 추가.
    interface OnDataModifiedListener {
        fun onDataModified()
    }


    private var _binding: FragmentModifyMyPageBinding? = null
    private val binding get() = _binding!!

    private val REQUEST_IMAGE_PICK = 1000  // 이미지 선택을 위한 요청 코드
    private val REQUEST_CODE_PICK_FILE = 2001  // 파일 선택을 위한 요청 코드



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModifyMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // ic_mypage_image_change 클릭 리스너 설정
        binding.icMypageImageChange.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "*/*"
            startActivityForResult(intent, REQUEST_CODE_PICK_FILE)
        }

//수정시, 이전 내용 보여주기
        val sharedPreferences =
            requireActivity().getSharedPreferences(MypageConText.PREF_MYPAGE, Context.MODE_PRIVATE)

//        만약 해당 키로 저장된 값이 없을 경우, 기본값 ""
        //이전이름 보여주기
        val previousName = sharedPreferences.getString(MypageConText.KEY_MYNAME, "")
        binding.edtMyName.setText(previousName)

        //이전전화번호 보여주기
        val previousNumber = sharedPreferences.getString(MypageConText.KEY_MYNUMBER,"")
        binding.edtMyNumber.setText(previousNumber)

        //이전 이메일 보여주기
        val previousMymail = sharedPreferences.getString(MypageConText.KEY_MYEMAIL,"")
        binding.edtMyEmail.setText(previousMymail)

        //이전 주소 보여주기
        val previousAddress = sharedPreferences.getString(MypageConText.KEY_MYADDRESS,"")
        binding.edtMyAddress.setText(previousAddress)

        //이전 생일 보여주기
        val previousBirth = sharedPreferences.getString(MypageConText.KEY_MYBIRTHDAY,"")
        binding.edtMyBirth.setText(previousBirth)

        //이전 메모 보여주기
        val previousMemo = sharedPreferences.getString(MypageConText.KEY_MYMEMO,"")
        binding.edtMyMemo.setText(previousMemo)


        // "수정완료" 버튼에 리스너 추가
        binding.btMypageModifyFinish.setOnClickListener {
            saveUserInfoToPreferences()
            dismiss() // 다이얼로그 종료
        }
    }

    private fun saveUserInfoToPreferences() {
        val sharedPreferences = requireActivity().getSharedPreferences(MypageConText.PREF_MYPAGE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // 이름 저장
        editor.putString(MypageConText.KEY_MYNAME, binding.edtMyName.text.toString())
        // 전화번호 저장
        editor.putString(MypageConText.KEY_MYNUMBER, binding.edtMyNumber.text.toString())
        // 이메일 저장
        editor.putString(MypageConText.KEY_MYEMAIL, binding.edtMyEmail.text.toString())
        // 주소 저장
        editor.putString(MypageConText.KEY_MYADDRESS, binding.edtMyAddress.text.toString())
        // 생일 저장
        editor.putString(MypageConText.KEY_MYBIRTHDAY, binding.edtMyBirth.text.toString())
        // 메모 저장
        editor.putString(MypageConText.KEY_MYMEMO, binding.edtMyMemo.text.toString())


        editor.apply()
        listener?.onDataModified()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // 파일 선택에 대한 처리 로직
        if (requestCode == REQUEST_CODE_PICK_FILE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data

            uri?.let {
                val inputStream = requireActivity().contentResolver.openInputStream(uri)
                val targetFile =
                    File(requireActivity().filesDir, "selectedImage.jpg") // 원하는 파일 이름으로 변경 가능
                val outputStream = FileOutputStream(targetFile)

                inputStream?.copyTo(outputStream)
                inputStream?.close()
                outputStream.close()

                // 이미지 뷰에 선택된 이미지 설정
                binding.imgMyimageModify.setImageURI(Uri.fromFile(targetFile))

                // 여기에 로깅 코드와 SharedPreferences 저장 코드를 추가
                val newImagePath = targetFile.absolutePath
                val sharedPreferences = requireActivity().getSharedPreferences(
                    MypageConText.PREF_MYPAGE,
                    Context.MODE_PRIVATE
                )
                val editor = sharedPreferences.edit()
                editor.putString(MypageConText.KEY_MYIMAGE_PATH, newImagePath)
                editor.apply()

                Log.d("Mypage", "Saved Image Path: $newImagePath")


            }
        } else if (requestCode == REQUEST_IMAGE_PICK) {
            // 이미지 선택에 관한 기존 로직...
            if (resultCode == Activity.RESULT_OK) {
                binding.imgMyimageModify.setImageURI(data?.data)  // 선택된 이미지를 img_myimage_modify에 설정
            } else {
                Toast.makeText(context, "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀 제거 (옵션)
        }
    }
}