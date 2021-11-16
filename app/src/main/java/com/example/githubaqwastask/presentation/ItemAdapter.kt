package com.example.githubaqwastask.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.githubaqwastask.BR
import com.example.githubaqwastask.databinding.LayoutFinalItemLinearBinding
import com.example.githubaqwastask.domain.entities.ItemModel


class ItemAdapter( val callback: () -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: LayoutFinalItemLinearBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var list = listOf<ItemModel>()
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ItemModel>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutFinalItemLinearBinding.inflate(
            LayoutInflater.from(parent.context)
        )
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val mItem = list.get(index = position)
        holder.binding.apply {
            item = mItem
            llRoot.setOnClickListener {
                llExtra.isVisible = !llExtra.isVisible
                if (llExtra.isVisible) {
                    callback()
                    mItem.isCurrentInFocus = true
                } else {
                    mItem.isCurrentInFocus = false
                }
                notifyPropertyChanged(BR.item)
            }
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
      return list.size
    }
}