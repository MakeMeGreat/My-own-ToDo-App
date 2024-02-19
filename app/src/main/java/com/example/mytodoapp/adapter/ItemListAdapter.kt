package com.example.mytodoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.databinding.ItemListItemBinding

class ItemListAdapter(
    private val onTaskClicked: (Task) -> Unit
) : ListAdapter<Task, ItemListAdapter.ItemListViewHolder>(DiffCallback) {

    class ItemListViewHolder(private var binding: ItemListItemBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskDescription.text = task.description
            binding.taskComplete.isChecked = task.complete
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder(
            ItemListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onTaskClicked(current)
        }
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }
}