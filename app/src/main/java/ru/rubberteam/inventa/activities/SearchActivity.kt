package ru.rubberteam.inventa.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearchText.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Поиск будет сделан позже",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnClearAll.setOnClickListener {
            binding.editTextItemLocation.setText("")
            binding.editTextItemCategory.setText("")
            binding.editTextItemName.setText("")
        }


    }
}