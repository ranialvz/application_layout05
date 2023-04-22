package com.ralvez.myapplicationlayout05




import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import com.ralvez.myapplicationlayout05.databinding.FragmentFirstBinding


var price = 0

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var myAdapter: ItemAdapter
    var myData = MyDataTest.myDataList

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?):View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        MyDataTest.test1()



        val myAdapter=  ItemAdapter(myData) { position ->
        }

        binding.rvBooklist.adapter= myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(activity)


        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter





        //myAdapter.notifyDataSetChanged()


        binding.btnAddlist.setOnClickListener {
            showAddItemDialog()
           // binding.textView2.text= mydata.toString()

        }



        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                myData.removeAt(position)

                recyclerView.adapter?.notifyItemRemoved(position)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return binding.root


    }




    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Add Item")

        val view = LayoutInflater.from(activity).inflate(R.layout.add_item_dialog, null)
        builder.setView(view)

        val itemNameEditText = view.findViewById<EditText>(R.id.item_name_edit_text)
        val itemDescriptionEditText = view.findViewById<EditText>(R.id.item_description_edit_text)

        builder.setPositiveButton("Add") { _, _ ->
            val itemName = itemNameEditText.text.toString()
            val itemDescription = itemDescriptionEditText.text.toString()

            try {
                price = itemDescription.toInt()
            } catch (e: NumberFormatException) {
                val toast = Toast.makeText(activity, "Pls input price number", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()
            }

            if (itemName.isNullOrEmpty() || itemDescription.isNullOrEmpty()){
                val toast = Toast.makeText(activity, "Pls input item and price", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()


            }
            else if (price==0){
                val toast = Toast.makeText(activity, "Pls input price number", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()
            }
            else {

                      // Add the new item to the list
            MyDataTest.myDataList.add(Item(itemName, price))

            // Notify the adapter that the data set has changed
            binding.rvBooklist.layoutManager = LinearLayoutManager(activity)
             }
        }

        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }









}