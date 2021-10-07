package ru.rubberteam.inventa.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rubberteam.inventa.adapters.TaskAdapter
import ru.rubberteam.inventa.databinding.ActivityMainBinding
import ru.rubberteam.inventa.restTestModel.TaskTestDTO
import ru.rubberteam.inventa.retrofit.interfaces.GetTasksRetro

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	lateinit var taskAdapter: TaskAdapter
	lateinit var dialog: AlertDialog
	lateinit var layoutManager: LinearLayoutManager


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.btnScan.setOnClickListener {
			val intentIntegrator = IntentIntegrator(this)
			intentIntegrator.setPrompt("For flash use volume up key")
			intentIntegrator.setBeepEnabled(true)
			intentIntegrator.setOrientationLocked(true)
			intentIntegrator.captureActivity = ScanActivity::class.java
			startForResult.launch(Intent(this, ScanActivity::class.java))
			intentIntegrator.initiateScan()
		}

		binding.recyclerTestList.setHasFixedSize(true)
		layoutManager = LinearLayoutManager(this)
		binding.recyclerTestList.layoutManager = layoutManager
		dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

		getAllTasks()
	}

	private fun getAllTasks() {
		var retrofit: Retrofit = Retrofit.Builder()
			//.baseUrl(R.string.baseUrl.toString())
			.baseUrl("http://188.242.160.111:8080")
//			.baseUrl("https://api.github.com/")
			.addConverterFactory(GsonConverterFactory.create()).build()

		dialog.show()

		var client: GetTasksRetro = retrofit.create(GetTasksRetro::class.java)
		client.tasksForUser("user").enqueue(object : Callback<MutableList<TaskTestDTO>> {
			override fun onResponse(call: Call<MutableList<TaskTestDTO>>,
									response: Response<MutableList<TaskTestDTO>>) {
				dialog.dismiss()

				if (response.isSuccessful) {
					var task = (response.body() as MutableList<TaskTestDTO>).get(0).orderDocument
//					Toast.makeText(applicationContext, "Successful response! $task", Toast.LENGTH_SHORT).show()

					taskAdapter = TaskAdapter(baseContext, response.body() as MutableList<TaskTestDTO>)
					taskAdapter.notifyDataSetChanged()
					binding.recyclerTestList.adapter = taskAdapter
				}
				else
					Toast.makeText(applicationContext, "Response went wrong..", Toast.LENGTH_SHORT)
						.show()
			}

			override fun onFailure(call: Call<MutableList<TaskTestDTO>>, t: Throwable) {
				Toast.makeText(applicationContext, "Oops: ${t.message}", Toast.LENGTH_SHORT)
					.show()
				dialog.dismiss()
			}
		})
	}


	override fun onBackPressed() {
		// Заглушка на выход из приложения через главную активность
		//Toast.makeText(applicationContext, "Не ходи туда по братски", Toast.LENGTH_SHORT).show()
	}

	//callback for get data from ScanActivity
	private val startForResult =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == Activity.RESULT_OK) {
				// There are no request codes
				val data: Intent? = result.data

				val intentResult = IntentIntegrator.parseActivityResult(
					result.resultCode, data
				)
				if (intentResult.contents != null) {
					val builder = AlertDialog.Builder(this)
					builder.setTitle("Result")
					builder.setMessage(intentResult.contents)
					builder.setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
					builder.show()
				} else {
					Toast.makeText(applicationContext, "Oops you didn't scan anything", Toast.LENGTH_SHORT)
						.show()
				}
			}
		}
}