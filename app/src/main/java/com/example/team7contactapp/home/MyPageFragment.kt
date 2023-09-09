package com.example.team7contactapp.home


import com.example.team7contactapp.data.MypageConText
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.team7contactapp.ModifyMyPageFragment
import com.example.team7contactapp.databinding.FragmentMyPageBinding
import java.io.File


class MyPageFragment : Fragment(), ModifyMyPageFragment.OnDataModifiedListener {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMyPageBinding.bind(view)

        // SharedPreferences에서 저장된 이름 및 이미지 불러오기 (이 부분은 updateData()를 호출함으로써 간결하게 처리)
        updateData()

        //내페이지 공유버튼
        binding.icShare.setOnClickListener {
            // xml에서 데이터 가져오기
            val myname = binding.txName.text.toString()
            val mynumber = binding.txNumber.text.toString()
            val myemail = binding.txEmail.text.toString()


            // 출력될 메세지 내용
            val message = "이름: $myname\n 전화번호: $mynumber\n 이메일: $myemail"

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

//내페이지수정버튼
        binding.icModify.setOnClickListener {
            val modifyFragment = ModifyMyPageFragment()
            modifyFragment.setOnDataModifiedListener(this)  // listener 설정
            modifyFragment.show(requireFragmentManager(), "ModifyMyPageDialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    override fun onDataModified() {
        updateData()  // 데이터 수정 시에도 동일한 데이터 갱신 메소드 호출
    }

    private fun updateData() {
        // SharedPreferences에서 정보 가져오기
        val sharedPreferences =
            requireActivity().getSharedPreferences(MypageConText.PREF_MYPAGE, Context.MODE_PRIVATE)

        // 이름 가져와서 설정하기
        binding.txName.text =
            sharedPreferences.getString(MypageConText.KEY_MYNAME, binding.txName.text.toString())

        // 전화번호 가져와서 설정하기
        binding.txNumber.text =
            sharedPreferences.getString(MypageConText.KEY_MYNUMBER, binding.txNumber.text.toString())

        // 이메일 가져와서 설정하기
        binding.txEmail.text =
            sharedPreferences.getString(MypageConText.KEY_MYEMAIL, binding.txEmail.text.toString())

        // 주소 가져와서 설정하기
        binding.txAddress.text =
            sharedPreferences.getString(MypageConText.KEY_MYADDRESS, binding.txAddress.text.toString())

        // 생일 가져와서 설정하기
        binding.txBirth.text =
            sharedPreferences.getString(MypageConText.KEY_MYBIRTHDAY, binding.txBirth.text.toString())

        // 메모 가져와서 설정하기
        binding.txMemo.text =
            sharedPreferences.getString(MypageConText.KEY_MYMEMO, binding.txMemo.text.toString())


        // 이미지 경로 가져와서 Glide를 사용하여 이미지 로드하기
        val savedImagePath = sharedPreferences.getString(MypageConText.KEY_MYIMAGE_PATH, null)
        savedImagePath?.let {
            val imageFile = File(it)
            if (imageFile.exists()) {
                Glide.with(requireActivity())
                    .load(Uri.fromFile(imageFile))
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.imgMyimage)
            }
        }
    }
}
