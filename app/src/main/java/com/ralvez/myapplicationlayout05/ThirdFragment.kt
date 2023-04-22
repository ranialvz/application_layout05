package com.ralvez.myapplicationlayout05


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import com.ralvez.myapplicationlayout05.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private lateinit var myAdapter: ItemAdapter
    val myData = MyDataTest.myDataListOrder



    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?):View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)

        val myAdapter=  ItemAdapter(myData) { position ->
        }

        binding.rvBooklist.adapter= myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter

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