package ru.rubberteam.inventa.services

class SecurityService {

	fun checkUser(login: String, password: String): Boolean {
		return login.length == password.length
	}

	fun checkPinCode(pinCode: String): Boolean {
		return pinCode == "1111"
	}

	fun isUserLogged(): Boolean {
		return true
	}

}