package ru.rubberteam.inventa.activities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.App
import ru.rubberteam.inventa.activities.MainActivity
import ru.rubberteam.inventa.activities.login.LoginConstants.PIN_CODE_SIZE
import ru.rubberteam.inventa.activities.login.LoginConstants.PIN_SYMBOL
import ru.rubberteam.inventa.databinding.ActivitySetPinCodeBinding
import ru.rubberteam.inventa.services.SecurityService
import javax.inject.Inject

class PinCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetPinCodeBinding
    private lateinit var pinCodeTextView: TextView
    private var pinCode: StringBuilder = StringBuilder()
    private lateinit var mSettings: SharedPreferences

    @Inject
    lateinit var securityService: SecurityService

    override fun onStop() {
        pinCode.clear()
        pinCodeTextView.text = ""
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSettings = getSharedPreferences(LoginConstants.APP_PREFERENCES, Context.MODE_PRIVATE)
        binding = ActivitySetPinCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pinCodeTextView = binding.pinCode
        (application as App).appComponent.injectPinCodeActivity(this)
        setOnClickListeners()
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
            if (pinCode.isNotEmpty()) {
                val textLength = pinCodeTextView.editableText.length
                pinCodeTextView.editableText.delete(
                    textLength - PIN_SYMBOL.length,
                    textLength
                )
                pinCode.deleteCharAt(pinCode.length - 1)
            }
        }
    }

    private fun pinCodeLogicExecute(code: Int) {
        pinCode.append(code)
        pinCodeTextView.append(PIN_SYMBOL)
        if (pinCode.length == PIN_CODE_SIZE) {
            if (securityService.checkPinCode(pinCode.toString(), mSettings)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                pinCode.clear()
                pinCodeTextView.text = ""
                Toast.makeText(
                    applicationContext,
                    "Код не верен, пожаулйста, повторите попытку",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }
}