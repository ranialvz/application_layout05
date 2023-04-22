package com.ralvez.myapplicationlayout05



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar



class MyAdapter( private val itemList: MutableList<Item>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemDescription: TextView = itemView.findViewById(R.id.item_description)

        init{
            itemView.setOnClickListener{v: View->
                val position: Int = adapterPosition
                //Toast.makeText(itemView.context, "click${position +1}", Toast.LENGTH_SHORT).show()
                try {
                    val myData1 =MyDataTest.myDataListSearch[position]
                    val myDataName =myData1.name
                    MyDataTest.myDataListOrder.add(myData1)
                    val snackbar = Snackbar.make(itemName, "   Added to Cart", Snackbar.LENGTH_LONG).setAction("$myDataName"){}
                    snackbar.show()
                } catch (e: IndexOutOfBoundsException) {
                    val myData1 =MyDataTest.myDataList[position]
                    val myDataName =myData1.name
                    val snackbar = Snackbar.make(itemName, "   Added to Cart", Snackbar.LENGTH_LONG).setAction("$myDataName"){}
                    snackbar.show()
                    MyDataTest.myDataListOrder.add(myData1)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemName.text = item.name
        holder.itemDescription.text = item.price.toString()


    }

    override fun getItemCount() = itemList.size


}
