package com.example.myapplication.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.Content
import com.example.myapplication.databinding.ItemsBinding

class ContentListAdapter(
    private var list: ArrayList<Content>,
    private val listener: (data: Any) -> Unit
) : RecyclerView.Adapter<ContentListAdapter.ViewHolder>() {

    private var mainList = list
    inner class ViewHolder(val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: ArrayList<Content>, search: Boolean) {
        if (!search) {
            if (list.size == 0) {
                this.list = list
            } else {
                this.list.addAll(list)
            }
        } else {
            this.list = list
        }
        notifyDataSetChanged()
    }


    fun updateListWithMain(){
        this.list = mainList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context)
            .load(holder.itemView.context.getResources()
                .getIdentifier(list[position].posterImage?.replace(".jpg",""), "drawable", holder.itemView.context.getPackageName()))
            .placeholder(R.drawable.placeholder_for_missing_posters)
            .into(holder.binding.appCompatImageView)
        holder.binding.textView.text = list[position].name

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun filter(text: String?) {
        val temp: ArrayList<Content> = ArrayList()
        for (data in mainList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (data.name!!.toLowerCase().contains(text!!.trim().toString().toLowerCase())) {
                temp.add(data)
            }
        }
        //update recyclerview
        this.updateList(temp, true)
    }
}