package com.sachet.appdataroom.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sachet.appdataroom.R
import com.sachet.appdataroom.databinding.ListItemBinding
import com.sachet.appdataroom.db.Subscriber

class SubscriberRecyclerViewAdapter(
    private val subscriberList: List<Subscriber>,
    val onClick: (Subscriber) -> Unit
):
    RecyclerView.Adapter<MyViewHolder>()
{
    //Inflate the xml layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflator, R.layout.list_item, parent, false)
        return MyViewHolder(binding){
            onClick(it)
        }
    }

    //get the subscriber at the particular position
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        println("$position pp")
        holder.bind(subscriberList[position])
    }

    override fun getItemCount(): Int {
        println(subscriberList.size)
        return subscriberList.size
    }
}

/**
 * Use this class to bind values to each list item
 */
class MyViewHolder(val binding: ListItemBinding, val onClick: (Subscriber) -> Unit): RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber: Subscriber){
        binding.nameView.text = subscriber.name
        binding.emailView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            onClick(subscriber)
        }
    }

}