package com.example.mybites.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybites.GroceriesActivity
import com.example.mybites.RecipeActivity
import com.example.mybites.databinding.GrocerycategoryRvBinding
import com.example.mybites.model.Grocery

class ProdCategoryAdapter(var dataList: ArrayList<Grocery>, var context: Context, var prodName: String) :
    RecyclerView.Adapter<ProdCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: GrocerycategoryRvBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.prodName.text = prodName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GrocerycategoryRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].prodImg).into(holder.binding.prodimg)
        holder.binding.prodName.text = dataList[position].prodName
        val temp = dataList[position].price
        holder.binding.price.text = temp.toString()
        holder.binding.next.setOnClickListener {
            val intent = Intent(context, GroceriesActivity::class.java)
            intent.putExtra("prodImg", dataList[position].prodImg)
            intent.putExtra("prodName", dataList[position].prodName)
            intent.putExtra("dcrp", dataList[position].dcrp)
            intent.putExtra("price", dataList[position].price)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return dataList.size
    }

}
