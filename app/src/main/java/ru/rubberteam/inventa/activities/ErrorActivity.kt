package ru.rubberteam.inventa.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import ru.rubberteam.inventa.databinding.ActivityErrorBinding


class ErrorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityErrorBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanAgain.setOnClickListener {
            val intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setPrompt("For flash use volume up key")
            intentIntegrator.setBeepEnabled(true)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.captureActivity = ScanActivity::class.java
            startForResult.launch(Intent(this, ScanActivity::class.java))
            intentIntegrator.initiateScan()
        }

        binding.btnInputManual.setOnClickListener {
        }

        binding.btnSomeWrong.setOnClickListener {
            Toast.makeText(applicationContext, "Очень жаль(", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data

                val intentResult = IntentIntegrator.parseActivityResult(
                    result.resultCode, data
                )
                if (intentResult.contents != null) {
                    Toast.makeText(applicationContext, "Успешное сканирование", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
//                    val builder = AlertDialog.Builder(this)
//                    builder.setTitle("Result")
//                    builder.setMessage(intentResult.contents)
//                    builder.setPositiveButton("OK") { dialogInterface, _ -> dialogInterface.dismiss() }
//                    builder.show()
                    //TODO logic
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Не удалось отсканировать",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
}