package ru.rubberteam.inventa.activities.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.activities.MainActivity
import ru.rubberteam.inventa.databinding.ActivitySetPincodeBinding

class PinLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySetPincodeBinding
    private var pinCode: StringBuilder = StringBuilder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetPincodeBinding.inflate(layoutInflater)
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
            if (pinCode.isNotEmpty()) {
                pinCode.deleteCharAt(pinCode.length - 1)
            }
        }
    }

    private fun pinCodeLogicExecute(code: Int) {
        pinCode.append(code)
        if (pinCode.length == 4) {
            val intent = Intent(this, MainActivity::class.java)
//            intent.flags = intent.flags or Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
        }
    }
}