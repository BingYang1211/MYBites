package com.example.mybites.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybites.model.Recipe
import com.example.mybites.RecipeActivity
import com.example.mybites.databinding.CategoryRvBinding

class CategoryAdapter(var dataList: ArrayList<Recipe>, var context: Context, var categoryTitle: String) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: CategoryRvBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.CategoryTitle.text = categoryTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].img).into(holder.binding.img)
        holder.binding.CategoryTitle.text = dataList[position].tittle
        val temp = dataList[position].ing.split("\n").dropLastWhile { it.isEmpty() }.toTypedArray()
        holder.binding.time.text = temp[0]
        holder.binding.next.setOnClickListener {
            val intent = Intent(context, RecipeActivity::class.java)
            intent.putExtra("img", dataList[position].img)
            intent.putExtra("tittle", dataList[position].tittle)
            intent.putExtra("des", dataList[position].des)
            intent.putExtra("ing", dataList[position].ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}
