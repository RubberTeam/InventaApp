package ru.rubberteam.inventa.retrofit.clients

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rubberteam.inventa.retrofit.interfaces.GetTasksRetro
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


class TasksRetroClient {
	private val BASE_URL = "http://188.242.160.111:8080"
	private var retrofit: Retrofit? = null

	val tasksRetroClient: GetTasksRetro
		get() = getClient(BASE_URL).create(GetTasksRetro::class.java)

	var gsonCustom = GsonBuilder().
	registerTypeAdapter(LocalDateTime::class.java, object: JsonDeserializer<LocalDateTime> {
		override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {
//			return LocalDateTime.parse(json?.asString,
//				DateTimeFormatter.ofPattern("uuuu-MM-dd[ ]['T']HH:mm:ss.SS[X]").withLocale(Locale.ENGLISH));
			return ZonedDateTime.parse(json?.asString).truncatedTo(ChronoUnit.SECONDS).toLocalDateTime()
		}
	}).registerTypeAdapter(LocalDate::class.java, object: JsonDeserializer<LocalDate> {
		override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
			return LocalDate.parse(json?.asString,
				DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH));
		}
	}).create()

	private fun getClient(baseUrl: String): Retrofit {
		if (retrofit == null) {
			retrofit = Retrofit.Builder()
				.baseUrl(baseUrl)
				.addConverterFactory(GsonConverterFactory.create(gsonCustom))
				.build()
		}
		return retrofit!!
	}
}