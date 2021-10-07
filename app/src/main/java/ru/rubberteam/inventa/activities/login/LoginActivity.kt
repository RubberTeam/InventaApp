package ru.rubberteam.inventa.activities.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.App
import ru.rubberteam.inventa.databinding.ActivityLoginBinding
import ru.rubberteam.inventa.services.SecurityService
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mSettings: SharedPreferences

    @Inject
    lateinit var securityService: SecurityService

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.injectLoginActivity(this)
        mSettings = getSharedPreferences(LoginConstants.APP_PREFERENCES, Context.MODE_PRIVATE)
        if (securityService.isUserLogged(mSettings)) {
            val intent = Intent(this, PinCodeActivity::class.java)
            startActivity(intent)
            finish()
        }
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            val login = binding.editTextLogin.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (securityService.checkUser(login, password)) {
                val intent = Intent(this, SetPinCodeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Пожалуйста, повторите попытку",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }
    }
}