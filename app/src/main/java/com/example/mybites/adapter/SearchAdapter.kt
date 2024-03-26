package com.example.mybites.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybites.RecipeActivity
import com.example.mybites.databinding.RecipeSearchRvBinding
import com.example.mybites.model.Recipe

class SearchAdapter(var dataList:ArrayList<Recipe>, var context: Context):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: RecipeSearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = RecipeSearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: ArrayList<Recipe>) {
        dataList = filterList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.recipeSearchImg)
        holder.binding.searchTxt.text = dataList.get(position).tittle
        holder.itemView.setOnClickListener {
            var intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("img", dataList.get(position).img)
            intent.putExtra("tittle", dataList.get(position).tittle)
            intent.putExtra("des", dataList.get(position).des)
            intent.putExtra("ing", dataList.get(position).ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }
}