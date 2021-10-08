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


class TaskAdapter(private val context: Context, private val addresses: List<Pair<String, String>>):
	RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

	class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		var binding: TaskCardBinding = TaskCardBinding.bind(itemView)

		val streetTitle: TextView = binding.street
		val adressTitle: TextView = binding.addressPart

//		fun bind(listItem: Task) {
//			itemView.setOnClickListener {
//				Toast.makeText(it.context, "нажал на ${binding.street}", Toast.LENGTH_SHORT).show()
//			}
//		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_card, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val listItem = addresses[position]
		//holder.bind(listItem)

		holder.streetTitle.text = addresses[position].first
		holder.adressTitle.text = addresses[position].second
	}

	override fun getItemCount() = addresses.size

}