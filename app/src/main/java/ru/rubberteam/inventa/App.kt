package ru.rubberteam.inventa

import android.app.Application
import ru.rubberteam.inventa.components.AppComponent
import ru.rubberteam.inventa.components.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent = DaggerAppComponent.create()

}