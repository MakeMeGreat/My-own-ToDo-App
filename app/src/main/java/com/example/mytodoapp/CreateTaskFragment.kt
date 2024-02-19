package com.example.mytodoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.databinding.FragmentCreateTaskBinding


class CreateTaskFragment : Fragment() {

    private val viewModel: ToDoViewModel by activityViewModels{
        TodoViewModelFactory(
            (activity?.application as ToDoApplication).database.taskDao()
        )
    }

    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = binding.createEditText.text
        binding.createButton.setOnClickListener {
            viewModel.addNewTask(text.toString())
            findNavController().navigate(R.id.action_createTaskFragment_to_itemListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}