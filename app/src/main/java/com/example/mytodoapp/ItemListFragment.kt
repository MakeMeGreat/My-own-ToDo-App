package com.example.mytodoapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodoapp.adapter.ItemListAdapter
import com.example.mytodoapp.data.Task
import com.example.mytodoapp.databinding.FragmentItemListBinding

class ItemListFragment : Fragment() {

    private val viewModel: ToDoViewModel by activityViewModels {
        TodoViewModelFactory(
            (activity?.application as ToDoApplication).database.taskDao()
        )
    }

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemListAdapter(object : ItemListAdapter.Listener {
            override fun onTaskClicked(task: Task) {
                val action =
                    ItemListFragmentDirections.actionItemListFragmentToEditFragment(task.id)
                findNavController().navigate(action)
            }

            override fun onCompleteCheckBoxClicked(task: Task) {
                viewModel.updateComplete(task)
            }

            override fun onLongClick(task: Task) {
                showDialog(task)
            }
        })
        binding.recyclerView.adapter = adapter
        viewModel.allTasks.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        binding.FAB.setOnClickListener {
            findNavController().navigate(R.id.action_itemListFragment_to_createTaskFragment)
        }
    }

    fun showDialog(task: Task) {
        val listener = DialogInterface.OnClickListener { _, which ->
            if (which == DialogInterface.BUTTON_POSITIVE) {
                viewModel.deleteTask(task)
            }
        }

        val dialog = AlertDialog.Builder(context)
            .setIcon(R.drawable.delete_img)
            .setTitle("Delete task")
            .setMessage("Are you sure want delete this task?")
            .setPositiveButton("Yes", listener)
            .setNegativeButton("No", listener)
            .create()

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}