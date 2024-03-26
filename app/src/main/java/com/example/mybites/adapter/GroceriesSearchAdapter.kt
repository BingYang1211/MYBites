package com.example.mybites.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybites.GroceriesActivity
import com.example.mybites.databinding.GrocerySearchRvBinding
import com.example.mybites.model.Grocery

class GroceriesSearchAdapter(var dataList:ArrayList<Grocery>, var context: Context):RecyclerView.Adapter<GroceriesSearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: GrocerySearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = GrocerySearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Grocery>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).prodImg).into(holder.binding.grocerySearchImg)
        holder.binding.grocerySearchTxt.text = dataList.get(position).prodName
        holder.itemView.setOnClickListener {
            var intent = Intent(context, GroceriesActivity::class.java)
            intent.putExtra("prodImg", dataList.get(position).prodImg)
            intent.putExtra("prodName", dataList.get(position).prodName)
            intent.putExtra("dcrp", dataList.get(position).dcrp)
            intent.putExtra("price", dataList.get(position).price)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }
}