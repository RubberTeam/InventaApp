package ru.rubberteam.inventa.modules

import dagger.Module
import dagger.Provides
import ru.rubberteam.inventa.services.SecurityService

@Module
class SecurityModule {

	@Provides
	fun provideSecurityService(): SecurityService {
		return SecurityService()
	}

}