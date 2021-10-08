package ru.rubberteam.inventa.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.rubberteam.inventa.R
import ru.rubberteam.inventa.activities.ErrorActivity
import ru.rubberteam.inventa.databinding.TaskCardBinding
import ru.rubberteam.inventa.domain.item.Item


class TaskAdapter(
    private val context: Context,
    private val taskProcessing: TaskProcessing
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: TaskCardBinding = TaskCardBinding.bind(itemView)

        val streetTitle: TextView = binding.street
        val addressTitle: TextView = binding.addressPart

        fun bind(position: Int, items: MutableList<Item>) {
            itemView.setOnClickListener {
                items[position]
                Toast.makeText(
                    it.context,
                    "нажал на ${items[position].itemLocation}",
                    Toast.LENGTH_SHORT
                ).show()
                it.context.startActivity(Intent(it.context, ErrorActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = taskProcessing.splitAddresses[position]
        holder.bind(position, taskProcessing.allItems as MutableList<Item>)
        holder.streetTitle.text = taskProcessing.splitAddresses[position].first
        holder.addressTitle.text = taskProcessing.splitAddresses[position].second
    }

    override fun getItemCount() = taskProcessing.splitAddresses.size

}