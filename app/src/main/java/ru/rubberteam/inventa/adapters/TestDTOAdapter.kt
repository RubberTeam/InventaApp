package ru.rubberteam.inventa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ru.rubberteam.inventa.R
import ru.rubberteam.inventa.databinding.ItemLayoutBinding
import ru.rubberteam.inventa.restTestModel.TestDTO

class TestDTOAdapter(private val context: Context, private val testList: MutableList<TestDTO>):
	RecyclerView.Adapter<TestDTOAdapter.ViewHolder>() {

	class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		var binding: ItemLayoutBinding = ItemLayoutBinding.bind(itemView)
		val image: ImageView = binding.imageMovie
		val txt_name: TextView = binding.txtName
		val txt_team: TextView = binding.txtTeam
		val txt_createdby: TextView = binding.txtCreatedby

		fun bind(listItem: TestDTO) {
			image.setOnClickListener {
				Toast.makeText(it.context, "нажал на ${binding.imageMovie}", Toast.LENGTH_SHORT)
					.show()
			}
			itemView.setOnClickListener {
				Toast.makeText(it.context, "нажал на ${binding.txtName.text}", Toast.LENGTH_SHORT).show()
			}
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
		return ViewHolder(itemView)
	}

	override fun getItemCount() = testList.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val listItem = testList[position]
		holder.bind(listItem)

		Picasso.get().load(testList[position].imageurl).into(holder.image)
		holder.txt_name.text = testList[position].name
		holder.txt_team.text = testList[position].team
		holder.txt_createdby.text = testList[position].createdby
	}

}