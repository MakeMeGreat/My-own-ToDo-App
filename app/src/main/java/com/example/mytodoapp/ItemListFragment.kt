package com.example.mytodoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodoapp.adapter.ItemListAdapter
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.data.TaskDao
import com.example.mytodoapp.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private val viewModel: ToDoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as ToDoApplication).database.taskDao()
        )
    }

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
        val adapter = ItemListAdapter {
            val action = ItemListFragmentDirections.actionItemListFragmentToEditFragment(it.id)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        viewModel.allTasks.observe(this.viewLifecycleOwner) {items ->
            items.let {
                adapter.submitList(it)
            }
        }




        binding.FAB.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_createTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}