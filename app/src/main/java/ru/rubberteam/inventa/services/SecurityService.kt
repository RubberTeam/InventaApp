package ru.rubberteam.inventa.services

class SecurityService {

	fun checkUser(login: String, password: String): Boolean {
		return login.length == password.length
	}
}