package com.thanhvo.todothanhvo.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.thanhvo.todothanhvo.R
import com.thanhvo.todothanhvo.tasklist.Task
import java.util.*

class TaskListFragment : Fragment() {
    private val adapter = TaskListAdapter()
    private var taskList = listOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.currentList = taskList
        recyclerView.adapter = adapter
        view.findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener{
            val newTask = Task(id = UUID.randomUUID().toString(), title = "Task ${taskList.size + 1}")
            taskList = taskList + newTask
            refreshList()
        }

        // "implémentation" de la lambda dans le fragment:
        adapter.onClickDelete = { task ->
            // Supprimer la tâche
            taskList = taskList - task
            refreshList()
        }

    }

    private fun refreshList(){
        adapter.currentList = taskList
        adapter.notifyDataSetChanged()
    }


}