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
import ru.rubberteam.inventa.adapters.TestDTOAdapter
import ru.rubberteam.inventa.databinding.ActivityMainBinding
import ru.rubberteam.inventa.restTestModel.TestDTO
import ru.rubberteam.inventa.retrofit.common.Common
import ru.rubberteam.inventa.retrofit.interfaces.RetrofitServices

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	//Retrofit test
	lateinit var mService: RetrofitServices
	lateinit var layoutManager: LinearLayoutManager
	lateinit var adapter: TestDTOAdapter
	lateinit var dialog: AlertDialog
	//test

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

		mService = Common.retrofitService
		binding.recyclerTestList.setHasFixedSize(true)
		layoutManager = LinearLayoutManager(this)
		binding.recyclerTestList.layoutManager = layoutManager
		dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

		getAllTestList()
	}

	private fun getAllTestList() {
		dialog.show()
		mService.getTestList().enqueue(object : Callback<MutableList<TestDTO>> {
			override fun onFailure(call: Call<MutableList<TestDTO>>, t: Throwable) {

			}

			override fun onResponse(call: Call<MutableList<TestDTO>>, response: Response<MutableList<TestDTO>>) {
				adapter = TestDTOAdapter(baseContext, response.body() as MutableList<TestDTO>)
				adapter.notifyDataSetChanged()
				binding.recyclerTestList.adapter = adapter

				dialog.dismiss()
			}
		})
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