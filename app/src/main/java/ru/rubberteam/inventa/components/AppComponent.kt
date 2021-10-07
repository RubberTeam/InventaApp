package ru.rubberteam.inventa.components

import dagger.Component
import ru.rubberteam.inventa.activities.login.LoginActivity
import ru.rubberteam.inventa.modules.SecurityModule

@Component(modules = [SecurityModule::class])
interface AppComponent {

	fun injectLoginActivity(loginActivity: LoginActivity)
}