package ru.rubberteam.inventa.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import ru.rubberteam.inventa.adapters.ItemsAdapter
import ru.rubberteam.inventa.adapters.TaskAdapter
import ru.rubberteam.inventa.databinding.ActivityItemsBinding
import ru.rubberteam.inventa.databinding.ActivityMainBinding

class ItemsActivity : AppCompatActivity() {
	private lateinit var binding: ActivityItemsBinding

	lateinit var itemsAdapter: ItemsAdapter
	lateinit var layoutManager: LinearLayoutManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityItemsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.recyclerItemList.setHasFixedSize(true)
		layoutManager = LinearLayoutManager(this)
		binding.recyclerItemList.layoutManager = layoutManager

		val bundle = intent.extras

		var someSpecial: MutableList<Pair<String, String>> = mutableListOf()

		if (bundle != null) {
			for (key in bundle.keySet()) {
				someSpecial.add(Pair(key, "${bundle.get(key)}"))
			}
		}

		itemsAdapter = ItemsAdapter(baseContext, someSpecial) //need to consume specific items here

		itemsAdapter.notifyDataSetChanged()
		binding.recyclerItemList.adapter = itemsAdapter
	}
}