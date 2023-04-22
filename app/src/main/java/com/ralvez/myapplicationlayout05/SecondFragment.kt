package com.ralvez.myapplicationlayout05


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ralvez.myapplicationlayout05.databinding.FragmentSecondBinding


class SecondFragment: Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var myAdapter: ItemAdapter

    val myData = MyDataTest.myDataList
    val filteredItems = MyDataTest.myDataListSearch


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)


        val searchView = binding.searchView
        val rootView: View = binding.root

        val myAdapter=  ItemAdapter(myData) { position ->
            MyDataTest.myItemSelected=position
            val myData1 =MyDataTest.myDataList[position]
            val myDataName =myData1.name
            MyDataTest.myDataListOrder.add(myData1)
            val snackbar = Snackbar.make(rootView, "   Added to Cart", Snackbar.LENGTH_LONG).setAction("$myDataName"){}
            snackbar.show()

        }

        binding.rvBooklist.adapter= myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter


        binding.searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    searchView.requestFocus()
                    recyclerView.adapter = ItemAdapter(myData) { position ->
                        MyDataTest.myItemSelected=position
                        val myData1 =MyDataTest.myDataList[position]
                        val myDataName =myData1.name
                        MyDataTest.myDataListOrder.add(myData1)
                        val snackbar = Snackbar.make(rootView, "   Added to Cart", Snackbar.LENGTH_LONG).setAction("$myDataName"){}
                        snackbar.show()
                    }

                    binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
                    myAdapter.notifyDataSetChanged()
                } else {
                    filteredItems.clear()
                    binding.searchView.requestFocus()
                    val searchResults = myData.filter { it.name.contains(newText, ignoreCase = true) }
                    filteredItems.addAll(searchResults)
                    recyclerView.adapter = ItemAdapter(filteredItems) { position ->
                        val myData1 =MyDataTest.myDataListSearch[position]
                        val myDataName =myData1.name
                        MyDataTest.myDataListOrder.add(myData1)
                        val snackbar = Snackbar.make(rootView, "   Added to Cart", Snackbar.LENGTH_LONG).setAction("$myDataName"){}
                        snackbar.show()
                    }
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