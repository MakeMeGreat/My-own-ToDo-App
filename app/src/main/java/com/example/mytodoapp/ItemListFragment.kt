package com.example.mytodoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodoapp.adapter.ItemListAdapter
import com.example.mytodoapp.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ToDoViewModel()
        val dataset = viewModel.getAllItems()
        val adapter = ItemListAdapter()
        adapter.submitList(dataset)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(false)
        }
    }
}