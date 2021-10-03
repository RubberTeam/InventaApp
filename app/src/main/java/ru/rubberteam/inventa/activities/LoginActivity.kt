package ru.rubberteam.inventa.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.rubberteam.inventa.App
import ru.rubberteam.inventa.R
import ru.rubberteam.inventa.services.SecurityService
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

	@Inject
	lateinit var securityService: SecurityService
	lateinit var btnLogin: Button

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		(application as App).appComponent.injectLoginActivity(this)

		btnLogin = findViewById(R.id.btnLogin)
		btnLogin.setOnClickListener {
			val login = findViewById<EditText>(R.id.editTextLogin).text.toString()
			val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

			if (securityService.checkUser(login, password)) {
				val intent = Intent(this, MainActivity::class.java)
				startActivity(intent)
			} else {
				Toast.makeText(applicationContext, "Please, try again", Toast.LENGTH_SHORT)
					.show()
			}

		}
	}
}