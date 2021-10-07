package ru.rubberteam.inventa.retrofit.interfaces

import retrofit2.Call
import retrofit2.http.GET
import ru.rubberteam.inventa.restTestModel.TestDTO

interface RetrofitServices {
	@GET("marvel")
	fun getTestList(): Call<MutableList<TestDTO>>
}