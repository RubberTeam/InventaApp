package ru.rubberteam.inventa.activities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.App
import ru.rubberteam.inventa.activities.MainActivity
import ru.rubberteam.inventa.activities.login.LoginConstants.APP_PREFERENCES
import ru.rubberteam.inventa.activities.login.LoginConstants.PIN_CODE_KEY
import ru.rubberteam.inventa.databinding.ActivityRepeatPinCodeBinding
import ru.rubberteam.inventa.services.SecurityService
import javax.inject.Inject


class RepeatPinCodeActivity : AppCompatActivity() {
    private var pinCodeRepeated: StringBuilder = StringBuilder()

    private lateinit var binding: ActivityRepeatPinCodeBinding
    private lateinit var pinCodeActual: String
    private lateinit var mSettings: SharedPreferences

    @Inject
    lateinit var securityService: SecurityService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.injectRepeatPinCodeActivity(this)
        binding = ActivityRepeatPinCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras != null) {
            pinCodeActual = extras.getString(PIN_CODE_KEY).toString()
        }
    }

    private fun setOnClickListeners() {
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
        if (pinCodeRepeated.length == LoginConstants.PIN_CODE_SIZE) {
            if (pinCodeRepeated.toString() == pinCodeActual) {
                mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                securityService.saveHashPinCode(pinCodeActual, mSettings)
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