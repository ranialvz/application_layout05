package com.ralvez.myapplicationlayout05


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ralvez.myapplicationlayout05.databinding.FragmentSecondBinding


class SecondFragment: Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var myAdapter: MyAdapter

    var myData = MyDataTest.myDataList





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        val filteredItems = MyDataTest.myDataListSearch
        val searchView = binding.searchView

        val recyclerView: RecyclerView = binding.rvBooklist
        myAdapter = MyAdapter(myData)
        recyclerView.adapter = myAdapter

        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)


        binding.searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    searchView.requestFocus()
                    recyclerView.adapter = MyAdapter(myData)
                    binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
                    myAdapter.notifyDataSetChanged()
                } else {
                    filteredItems.clear()
                    binding.searchView.requestFocus()
                    val searchResults = myData.filter { it.name.contains(newText, ignoreCase = true) }
                    filteredItems.addAll(searchResults)
                    recyclerView.adapter = MyAdapter(filteredItems)
                    binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
                    myAdapter.notifyDataSetChanged()
                }
                return true
            }
        })


        return binding.root

    }



    override fun onResume() {
        super.onResume()
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
        val searchView = binding.searchView
        searchView.requestFocus()
    }



}