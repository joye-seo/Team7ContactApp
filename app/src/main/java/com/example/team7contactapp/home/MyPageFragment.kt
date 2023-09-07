package com.example.team7contactapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.team7contactapp.ContactDialogFragment
import com.example.team7contactapp.R
import com.example.team7contactapp.databinding.FragmentKeypadBinding
import com.example.team7contactapp.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

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

        binding.icShare.setOnClickListener {
            // xml에서 데이터 가져오기
            val name = binding.txName.text.toString()
            val number = binding.txNumber.text.toString()
            val email = binding.txEmail.text.toString()

            // 출력될 메세지 내용
            val message = "이름: $name\n 전화번호: $number\n 이메일: $email"

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, message)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        binding.icModify.setOnClickListener {

            loadFragment()
        }
    }

    //▲위 내용은, 에뮬 구동시, 구글 로그인 해놔야, 메일 전송으로 바로 내용 복사되어 보낼수 있음.
    // 발표시 참고하기 !

    private fun loadFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val newFragment = ContactDialogFragment()
        newFragment.show(fragmentManager, "ContactDialogFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}