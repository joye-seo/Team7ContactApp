package com.example.team7contactapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.databinding.FragmentContactDialogBinding
import java.util.Calendar
import java.util.regex.Pattern

class ContactDialogFragment : DialogFragment() {
    private var _binding: FragmentContactDialogBinding? = null
    private val binding get() = _binding!!

    private var listener: AddItem? = null

    private var nameCorrect = false
    private var phoneCorrect = false

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
        btnSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val phoneNumber = binding.edtNumber.text.toString()

            if (name.isEmpty() || phoneNumber.isEmpty() || !nameCorrect || !phoneCorrect) {
                Toast.makeText(requireContext(), "필수 값을 확인해주세요", Toast.LENGTH_SHORT).show()
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

        val user = MyItem(null, name, number, email, false, address, birth, memo)

        listener?.add(user)
        dismiss()
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