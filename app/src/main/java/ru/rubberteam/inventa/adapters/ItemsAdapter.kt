package ru.rubberteam.inventa.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.rubberteam.inventa.R
import ru.rubberteam.inventa.activities.ErrorActivity
import ru.rubberteam.inventa.databinding.ItemCardBinding
import ru.rubberteam.inventa.databinding.TaskCardBinding
import ru.rubberteam.inventa.domain.item.Item

class ItemsAdapter(private val context: Context,
				   private val data: MutableList<Pair<String, String>>
) :
	RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var binding: ItemCardBinding = ItemCardBinding.bind(itemView)

		val desc: TextView = binding.description
		val count: TextView = binding.counter

//		fun bind(position: Int, items: MutableList<Item>) {
//			itemView.setOnClickListener {
//				items[position]
//				Toast.makeText(
//					it.context,
//					"нажал на ${items[position].itemLocation}",
//					Toast.LENGTH_SHORT
//				).show()
//				it.context.startActivity(Intent(it.context, ErrorActivity::class.java))
//			}
//		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView =
			LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
		return ItemsAdapter.ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//		val listItem = taskProcessing.splitAddresses[position]
//		holder.bind(position, taskProcessing.allItems as MutableList<Item>)
		holder.desc.text = data[position].first
		holder.count.text = data[position].second
	}

	override fun getItemCount() = data.size


}