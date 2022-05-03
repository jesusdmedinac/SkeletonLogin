package com.mupper.skeletonlogin.domain.usecase

import com.mupper.skeletonlogin.domain.exception.LoginException
import com.mupper.skeletonlogin.domain.model.UserCredentials
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.jvm.Throws

interface LoginUseCase {
    @Throws(LoginException::class)
    suspend operator fun invoke(userCredentials: UserCredentials)
}

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override suspend fun invoke(userCredentials: UserCredentials) {
        delay(500)
    }
}