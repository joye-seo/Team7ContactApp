package com.example.team7contactapp.home

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.team7contactapp.databinding.FragmentKeypadBinding
import android.Manifest
import android.view.Gravity

class KeypadFragment : Fragment() {
    companion object {
        private const val REQUEST_CALL_PHONE_PERMISSION = 1001
    } // 전화 통화 권한 요청을 식별 하는 상수



    private var _binding: FragmentKeypadBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeypadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpNumberClickListeners()

        binding.numberTxt.apply {
            isSingleLine = true
            gravity = Gravity.END
        }  //텍스트 숫자 입력시 먼저 입력된 숫자가 옆으로 밀리게 적용

    }


    private fun setUpNumberClickListeners() {

        binding.apply {

            numberOne.setOnClickListener{appendNumber("1")}
            numberOne1.setOnClickListener{appendNumber("1")}
            numberOne2.setOnClickListener{appendNumber("1")}

            numberTwo.setOnClickListener { appendNumber("2")}
            numberTwo2.setOnClickListener { appendNumber("2")}
            numberTwo3.setOnClickListener { appendNumber("2")}

            numberThree.setOnClickListener { appendNumber("3")}
            numberTwo2.setOnClickListener { appendNumber("2")}
            numberTwo3.setOnClickListener { appendNumber("2")}

            numberFour.setOnClickListener { appendNumber("4") }
            numberFour2.setOnClickListener { appendNumber("4") }
            numberFour3.setOnClickListener { appendNumber("4") }

            numberFive.setOnClickListener { appendNumber("5") }
            numberFive2.setOnClickListener { appendNumber("5") }
            numberFive3.setOnClickListener { appendNumber("5") }

            numberSix.setOnClickListener { appendNumber("6") }
            numberSix2.setOnClickListener { appendNumber("6") }
            numberSix3.setOnClickListener { appendNumber("6") }

            numberSeven.setOnClickListener { appendNumber("7") }
            numberSeven2.setOnClickListener { appendNumber("7") }
            numberSeven3.setOnClickListener { appendNumber("7") }

            numberEight.setOnClickListener { appendNumber("8") }
            numberEight2.setOnClickListener { appendNumber("8") }
            numberEight3.setOnClickListener { appendNumber("8") }

            numberNine.setOnClickListener { appendNumber("9") }
            numberNine2.setOnClickListener { appendNumber("9") }
            numberNine3.setOnClickListener { appendNumber("9") }

            numberZero.setOnClickListener { appendNumber("0") }
            numberZero2.setOnClickListener { appendNumber("0") }
            numberZero3.setOnClickListener { appendNumber("0") }

            star.setOnClickListener { appendNumber("*") }
            star2.setOnClickListener { appendNumber("*") }
            star3.setOnClickListener { appendNumber("*") }

            numberShop.setOnClickListener { appendNumber("#") }
            numberShop2.setOnClickListener { appendNumber("#") }
            numberShop3.setOnClickListener { appendNumber("#") }


            closeBtn.setOnClickListener {
                removeLastCharacter()      //숫자를 하나씩 제거하는 함수 실행
            }
            closeBtn.setOnLongClickListener {
                clearAllText()                  //숫자를 전부제거하는 함수 실행
                true
            }
            binding.callBtn.setOnClickListener {
                val  number = binding.numberTxt.text.toString()
                if (number.isNotEmpty()) {
                    makeCall(number)
                }
                else {
                    Toast.makeText(requireContext(), "번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            } // call버튼 누를시 번호가 있으면 makecall , 없을시 토스트메세지 발송


        } //숫자 클릭 이벤트 , 숫자 제거 이벤트 , 숫자 전체 제거 이벤트 ,  전화 거는 버튼 이벤트 , 번호가 없을시 토스트 메세지



    }  //숫자 클릭 이벤트


    private fun makeCall(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        // Check if the app has the necessary permissions
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        } else {
            // Request permission if not granted
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL_PHONE_PERMISSION)
        }
    }   // makeCall 함수 앱에서 전화를 거는데 필요한 권한이 있는지 확인후 확인 되면 통화 시작 그렇지 않을시 필요한 권한을 요청함

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CALL_PHONE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)  {
                    val number = binding.numberTxt.text.toString()
                    if (number.isNotEmpty()){
                        makeCall(number)
                    }
                } else {
                    Toast.makeText(requireContext(), "허가가 거부되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }  // 사용자가 요청된 권한을 부여 하거나 거부 할때 사용 하는 콜백 전화 통화 권한을 부여 하면 전화 통화 시작 , 거부시 토스트 메세지

    private fun removeLastCharacter() {
        val  currenText = binding.numberTxt.text.toString()
        if (currenText.isNotEmpty()) {
            binding.numberTxt.text = currenText.substring(0,currenText.length - 1)
        }
    }   // 숫자를 하나씩 제거 하는 함수 실행  = text가 비어 있지 않으면 텍스트 - 1칸을 삭제함.

    private fun clearAllText() {
        binding.numberTxt.text = ""
    }  //숫자를 전부 제거하는 함수 실행 = 전부 제거



    private fun appendNumber(s: String) {

        val currentText = binding.numberTxt.text.toString()

        binding.numberTxt.text = currentText + s

        when(currentText.replace("-","").length){
            3,7->binding.numberTxt.text="$currentText-$s"
            else->binding.numberTxt.text=currentText+s
        }
    }  // text에 클릭한 숫자 출력 , 3번째숫자 뒤 ,7번째숫자 뒤 - 표시


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
