package com.example.mytodoapp

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    lateinit var task: Task

    private val viewModel: ToDoViewModel by activityViewModels {
        TodoViewModelFactory(
            (activity?.application as ToDoApplication).database.taskDao()
        )
    }

    private val navigationArgs: EditFragmentArgs by navArgs()

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater)
        return binding.root
    }

    fun bind(task: Task) {
        binding.editText.text = Editable.Factory.getInstance().newEditable(task.description)
        binding.isDoneCheckBox.isChecked = task.complete
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {
            viewModel.retrieveTask(id).observe(this.viewLifecycleOwner) { selectedTask ->
                task = selectedTask
                bind(task)
            }
        }
        binding.deleteButton.setOnClickListener {
            viewModel.deleteTask(task)
            findNavController().navigate(R.id.action_editFragment_to_itemListFragment)
        }

        binding.upgradeButton.setOnClickListener {
            viewModel.updateTask(
                navigationArgs.id,
                binding.editText.text.toString(),
                binding.isDoneCheckBox.isChecked
            )
            findNavController().navigate(R.id.action_editFragment_to_itemListFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}