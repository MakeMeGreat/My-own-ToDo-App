package com.example.mytodoapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mytodoapp.R
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.databinding.ItemListItemBinding

class ItemListAdapter(
    private val listener: Listener
) : ListAdapter<Task, ItemListAdapter.ItemListViewHolder>(DiffCallback), View.OnClickListener,
    View.OnLongClickListener {

    override fun onClick(v: View) {
        val task = v.tag as Task
        when (v.id) {
            R.id.task_complete -> listener.onCompleteCheckBoxClicked(task)
            else -> listener.onTaskClicked(task)
        }
    }

    override fun onLongClick(v: View): Boolean {
        val task = v.tag as Task
        listener.onLongClick(task)
        return true
    }

    class ItemListViewHolder(val binding: ItemListItemBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskDescription.text = task.description
            binding.taskComplete.isChecked = task.complete
            if (task.complete) {
                binding.taskDescription.apply {
                    paint.isStrikeThruText = true
                    setTextColor(Color.GRAY)
                }
            } else {
                binding.taskDescription.apply {
                    paint.isStrikeThruText = false
                    setTextColor(Color.BLACK)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListItemBinding.inflate(inflater)

        binding.taskComplete.setOnClickListener(this)
        binding.root.setOnClickListener(this)
        binding.root.setOnLongClickListener(this)

        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val current = getItem(position)
        holder.apply {
            binding.root.tag = current
            binding.taskComplete.tag = current
            bind(current)
        }
    }

    interface Listener {
        fun onTaskClicked(task: Task)
        fun onCompleteCheckBoxClicked(task: Task)
        fun onLongClick(task: Task)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
                return oldItem == newItem
            }
        }
    }
}