package com.ralvez.myapplicationlayout05


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ralvez.myapplicationlayout05.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var myAdapter: MyAdapter
    val myData = MyDataTest.myDataListOrder



    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?):View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)

        myAdapter = MyAdapter(myData)



        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)

        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                myData.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
                var sumAll = myData.sumOf { it.price }
                binding.tvTotal.text= sumAll.toString()
            }


        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        binding.btnAddlist.setOnClickListener {


        }

        return binding.root

    }

    override fun onResume() {
        var sumAll = myData.sumOf { it.price }
        binding.tvTotal.text= sumAll.toString()
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
        super.onResume()
    }

}