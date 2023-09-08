package com.example.team7contactapp

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.adapter.ContactFragmentAdapter
import com.example.team7contactapp.data.ContactManager
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User
import com.example.team7contactapp.data.User.dataList
import com.example.team7contactapp.databinding.FragmentContactDialogBinding
import java.util.Calendar

class ContactDialogFragment : DialogFragment() {
    private var _binding: FragmentContactDialogBinding? = null
    private val binding get() = _binding!!

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
            userData()
        }
        btnMore.setOnClickListener {
            layoutMore.visibility = View.VISIBLE
            btnMore.visibility = View.GONE
        }
        edtBirth.setOnClickListener {
            showDatePicker()
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
        val user = MyItem(null, name, number, "",false, "", "", "")

//        val bundle = Bundle()
//        bundle.putParcelable("data", user)
//        ContactFragment().arguments = bundle
//        edtAddress
//        edtBirth
//        edtMemo
        ContactManager.addContact(user)
        dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}