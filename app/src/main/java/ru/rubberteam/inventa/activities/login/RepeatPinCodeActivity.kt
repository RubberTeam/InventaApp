package ru.rubberteam.inventa.activities.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.activities.MainActivity
import ru.rubberteam.inventa.activities.login.LoginConstants.PIN_CODE_KEY
import ru.rubberteam.inventa.databinding.ActivityRepeatPinCodeBinding

class RepeatPinCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepeatPinCodeBinding
    private var pinCodeActual: String? = null
    private var pinCodeRepeated: StringBuilder = StringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras
        if (extras != null) {
            pinCodeActual = extras.getString(PIN_CODE_KEY)
        }

        binding = ActivityRepeatPinCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnKey1.setOnClickListener {
            pinCodeLogicExecute(1)
        }
        binding.btnKey2.setOnClickListener {
            pinCodeLogicExecute(2)
        }
        binding.btnKey3.setOnClickListener {
            pinCodeLogicExecute(3)
        }
        binding.btnKey4.setOnClickListener {
            pinCodeLogicExecute(4)
        }
        binding.btnKey5.setOnClickListener {
            pinCodeLogicExecute(5)
        }
        binding.btnKey6.setOnClickListener {
            pinCodeLogicExecute(6)
        }
        binding.btnKey7.setOnClickListener {
            pinCodeLogicExecute(7)
        }
        binding.btnKey8.setOnClickListener {
            pinCodeLogicExecute(8)
        }
        binding.btnKey9.setOnClickListener {
            pinCodeLogicExecute(9)
        }
        binding.btnKey0.setOnClickListener {
            pinCodeLogicExecute(0)
        }
        binding.btnDelete.setOnClickListener {
            if (pinCodeRepeated.isNotEmpty()) {
                pinCodeRepeated.deleteCharAt(pinCodeRepeated.length - 1)
            }
        }
    }

    private fun pinCodeLogicExecute(code: Int) {
        pinCodeRepeated.append(code)
        if (pinCodeRepeated.length == 4) {
            if (pinCodeRepeated.toString().equals(pinCodeActual)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Пин коды не совпадают, пожалуйста повторите попытку",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, SetPinCodeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}