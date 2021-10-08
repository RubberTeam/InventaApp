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
import ru.rubberteam.inventa.adapters.TaskAdapter
import ru.rubberteam.inventa.adapters.TaskProcessing
import ru.rubberteam.inventa.databinding.ActivityMainBinding
import ru.rubberteam.inventa.domain.item.Item
import ru.rubberteam.inventa.domain.task.Task
import ru.rubberteam.inventa.retrofit.clients.TasksRetroClient

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	lateinit var taskAdapter: TaskAdapter
	lateinit var dialog: AlertDialog
	lateinit var layoutManager: LinearLayoutManager


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.btnScanQrCode.setOnClickListener {
			val intentIntegrator = IntentIntegrator(this)
			intentIntegrator.setPrompt("For flash use volume up key")
			intentIntegrator.setBeepEnabled(true)
			intentIntegrator.setOrientationLocked(true)
			intentIntegrator.captureActivity = ScanActivity::class.java
			startForResult.launch(Intent(this, ScanActivity::class.java))
			intentIntegrator.initiateScan()
		}

		binding.btnSearch.setOnClickListener{
			val intent = Intent(this, SearchActivity::class.java)
			startActivity(intent)
		}

		binding.btnSettings.setOnClickListener{
			val intent = Intent(this, SettingsActivity::class.java)
			startActivity(intent)
		}

		binding.recyclerTestList.setHasFixedSize(true)
		layoutManager = LinearLayoutManager(this)
		binding.recyclerTestList.layoutManager = layoutManager
		dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

		getAllTasks()
	}

	private fun getAllTasks() {
		dialog.show()

		TasksRetroClient().tasksRetroClient.tasksForUser("user").enqueue(object : Callback<MutableList<Task>> {
			override fun onResponse(call: Call<MutableList<Task>>,
									response: Response<MutableList<Task>>) {
				dialog.dismiss()

				if (response.isSuccessful) {
//					var task = (response.body() as MutableList<Task>).get(0).orderDocument
//					Toast.makeText(applicationContext, "Successful response! $task", Toast.LENGTH_SHORT).show()
//
//					var lineBefore = (response.body() as MutableList<Task>).get(0).taskId.toString()
//					Toast.makeText(applicationContext, "$lineBefore", Toast.LENGTH_SHORT)
//						.show()

					val processing = TaskProcessing(response.body() as MutableList<Task>)
//
//					var line: String
//
//					if (processing.allItems != null) {
//						line = " " + processing.allItems.size
//					} else {
//						line = "This is null!!!"
//					}
//
//					Toast.makeText(applicationContext, "$line", Toast.LENGTH_SHORT)
//						.show()

					taskAdapter = TaskAdapter(baseContext, processing.splitAddresses) //need to consume list of Pairs here

//					taskAdapter = TaskAdapter(baseContext, response.body() as MutableList<Task>)
					taskAdapter.notifyDataSetChanged()
					binding.recyclerTestList.adapter = taskAdapter
				}
				else
					Toast.makeText(applicationContext, "Response went wrong..", Toast.LENGTH_SHORT)
						.show()
			}

			override fun onFailure(call: Call<MutableList<Task>>, t: Throwable) {
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
					Toast.makeText(applicationContext, "Не удалось отсканировать", Toast.LENGTH_SHORT)
						.show()
					val intent = Intent(this, ErrorActivity::class.java)
					startActivity(intent)
				}
			}
		}
}