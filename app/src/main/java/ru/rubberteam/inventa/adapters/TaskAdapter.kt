package ru.rubberteam.inventa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.rubberteam.inventa.R
import ru.rubberteam.inventa.databinding.TaskCardBinding
import ru.rubberteam.inventa.domain.task.Task


class TaskAdapter(private val context: Context, private val taskList: MutableList<Task>):
	RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

	class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		var binding: TaskCardBinding = TaskCardBinding.bind(itemView)

		val txt_id: TextView = binding.taskId
		val txt_status: TextView = binding.taskStatus
		val txt_order: TextView = binding.orderDocument

		fun bind(listItem: Task) {
			itemView.setOnClickListener {
				Toast.makeText(it.context, "нажал на ${binding.taskId.text}", Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_card, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val listItem = taskList[position]
		holder.bind(listItem)

		holder.txt_id.text = taskList[position].taskId.toString()
		holder.txt_status.text = taskList[position].taskStatus.statusName
		holder.txt_order.text = taskList[position].orderDocument
	}

	override fun getItemCount() = taskList.size

}