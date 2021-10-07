package ru.rubberteam.inventa.retrofit.common

import ru.rubberteam.inventa.retrofit.RetrofitClient
import ru.rubberteam.inventa.retrofit.interfaces.RetrofitServices

object Common {
	private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
	val retrofitService: RetrofitServices
		get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}