package com.example.team7contactapp

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.MypageConText
import com.example.team7contactapp.databinding.FragmentContactDialogBinding
import java.io.File
import java.io.FileOutputStream
import java.text.Format
import java.util.Calendar
import java.util.Formatter
import java.util.regex.Pattern

class ContactDialogFragment : DialogFragment() {
    private var _binding: FragmentContactDialogBinding? = null
    private val binding get() = _binding!!

    private var listener: AddItem? = null

    private var nameCorrect = false
    private var phoneCorrect = false
    private var uri: Uri? = null

    val REQUEST_IMAGE_PICK = 1000  // 이미지 선택을 위한 요청 코드
    val REQUEST_CODE_PICK_FILE = 2001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edtBirth.isFocusable = false
        initView()
    }

    private fun initView() = with(binding) {
        btnCancel.setOnClickListener {
            dismiss()
        }
        btnImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "*/*"
            startActivityForResult(intent, REQUEST_CODE_PICK_FILE)
            btnImage.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val phoneNumber = binding.edtNumber.text.toString()

            if (name.isEmpty() || phoneNumber.isEmpty() || !nameCorrect || !phoneCorrect) {
                Toast.makeText(requireContext(), "필수 값을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                userData()
            }
        }
        btnMore.setOnClickListener {
            layoutMore.visibility = View.VISIBLE
            btnMore.visibility = View.GONE
        }
        edtBirth.setOnClickListener {
            showDatePicker()
        }
        textChanged()
    }

    private fun textChanged() = with(binding) {
        val ps: Pattern =
            Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")

        edtName.addTextChangedListener {
            if (it.isNullOrBlank()) {
                textInputLayoutName.error = "이름을 입력해주세요"
                nameCorrect = false
            } else {
                textInputLayoutName.error = null
                nameCorrect = true
            }
        }
        edtNumber.addTextChangedListener {
            if (it.isNullOrBlank()) {
                textInputLayoutNumber.error = "연락처를 입력해주세요"
                phoneCorrect = false
            } else if (!it.toString().matches("[0-9]+".toRegex())) {
                textInputLayoutNumber.error = "숫자만 입력해주세요"
                phoneCorrect = false
            } else {
                textInputLayoutNumber.error = null
                phoneCorrect = true
            }
        }
        edtEmail.addTextChangedListener {
            if (!ps.matcher(binding.edtEmail.text.toString()).matches()) {
                textInputLayoutEmail.error = "이메일 형식을 확인해주세요"
            } else {
                textInputLayoutEmail.error = null
            }
        }
    }

    private fun showDatePicker() {
        // 현재 날짜로 DatePickerDialog 초기화
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // DatePickerDialog 생성 및 표시
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDay"
                binding.edtBirth.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun userData() = with(binding) {
        val name = edtName.text.toString()
        val number = edtNumber.text.toString()
        val email = edtEmail.text.toString()
        val address = edtAddress.text.toString()
        val birth = edtBirth.text.toString()
        val memo = edtMemo.text.toString()
//        val image = uri?.toString()?.toInt() ?: null

        val user = MyItem(null, name, number, email, false, address, birth, memo)

        listener?.add(user)
        dismiss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 파일 선택에 대한 처리 로직
        if (requestCode == REQUEST_CODE_PICK_FILE && resultCode == Activity.RESULT_OK) {
            uri = data?.data

            uri?.let {
                val inputStream = requireActivity().contentResolver.openInputStream(uri!!)
                val targetFile =
                    File(requireActivity().filesDir, "selectedImage.jpg") // 원하는 파일 이름으로 변경 가능
                val outputStream = FileOutputStream(targetFile)

                inputStream?.copyTo(outputStream)
                inputStream?.close()
                outputStream.close()

                // 이미지 뷰에 선택된 이미지 설정
                binding.btnImage.setImageURI(Uri.fromFile(targetFile))

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
                binding.btnImage.setImageURI(data?.data)  // 선택된 이미지를 img_myimage_modify에 설정
            } else {
                Toast.makeText(context, "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setListener(context: AddItem) {
        listener = context
    }

    interface AddItem {
        fun add(contact: MyItem)
    }


}