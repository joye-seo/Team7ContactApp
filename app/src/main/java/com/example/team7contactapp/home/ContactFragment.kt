package com.example.team7contactapp.home

import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDialogFragment
import com.example.team7contactapp.R
import com.example.team7contactapp.adapter.ContactFragmentAdapter
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User.dataList
import com.example.team7contactapp.databinding.FragmentContactBinding

class ContactFragment : Fragment(), ContactDialogFragment.AddItem {

    private var _binding: FragmentContactBinding? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var isGridLayout = false
    private val binding get() = _binding!!
    val adapter = ContactFragmentAdapter(dataList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val view = binding.root
        return view  //End
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //여기서부터 작업
        //데이터 원본준비
        dataList.sortWith(compareBy({ !it.favorite }, { it.name }))
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        isGridLayout = false
        adapter.switchLayout(false)
        contactAdd()

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.RIGHT) {
                        if (hasCallPermission()) {
                            makeCall(dataList[position].contact)
                        }
                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)

        binding.icMore.setOnClickListener {
            showPopupMenu()
        }
    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(requireContext(), binding.icMore)
        popupMenu.menuInflater.inflate(R.menu.menu_contact, popupMenu.menu)

        // 팝업 메뉴 아이템 클릭 이벤트 처리
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_linear -> {
                    layoutManager = LinearLayoutManager(context)
                    binding.recyclerview.layoutManager = layoutManager
                    isGridLayout = false
//                    binding.recyclerview.removeItemDecoration(
//                        GridSpacingItemDecoration(4, spacing = 8f.fromDpToPx())
//                    )
                    adapter.switchLayout(false) // Linear 레이아웃으로 변경
                    true
                }

                R.id.menu_grid -> {
                    layoutManager = GridLayoutManager(context, 2)
                    binding.recyclerview.layoutManager = layoutManager
//                    binding.recyclerview.addItemDecoration(
//                        GridSpacingItemDecoration(2, spacing = 8f.fromDpToPx())
//                    )
                    isGridLayout = true
                    adapter.switchLayout(true) // Linear 레이아웃으로 변경
                    true
                }

                else -> false
            }
        }

        popupMenu.show()
    }

    internal class GridSpacingItemDecoration(
        private val spanCount: Int, // Grid의 column 수
        private val spacing: Int // 간격
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position: Int = parent.getChildAdapterPosition(view)

            if (position >= 0) {
                val column = position % spanCount // item column
                outRect.apply {
                    // spacing - column * ((1f / spanCount) * spacing)
                    left = spacing - column * spacing / spanCount
                    // (column + 1) * ((1f / spanCount) * spacing)
                    right = (column + 1) * spacing / spanCount
                    if (position < spanCount) top = spacing
                    bottom = spacing
                }
            } else {
                outRect.apply {
                    left = 0
                    right = 0
                    top = 0
                    bottom = 0
                }
            }
        }
    }


    private fun hasCallPermission(): Boolean {
        return true
    }

    private fun makeCall(contact: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$contact")
        startActivity(intent)
    }

    private fun contactAdd() {
        binding.btnContactAdd.setOnClickListener {
            loadFragment()
        }
    }

    private fun loadFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val newFragment = ContactDialogFragment()
        newFragment.show(fragmentManager, "ContactDialogFragment")
        newFragment.setListener(this)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun add(contact: MyItem) {
        adapter.addList(contact)
    }

    fun Float.fromDpToPx(): Int =
        (this * Resources.getSystem().displayMetrics.density).toInt()
}
