package com.mupper.skeletonlogin.domain.usecase

import com.mupper.skeletonlogin.domain.exception.LoginException
import com.mupper.skeletonlogin.domain.model.UserCredentials
import javax.inject.Inject
import kotlin.jvm.Throws

interface LoginUseCase {
    @Throws(LoginException::class)
    operator fun invoke(userCredentials: UserCredentials)
}

class LoginUseCaseImpl @Inject constructor() : LoginUseCase {
    override fun invoke(userCredentials: UserCredentials) {
        TODO("Not yet implemented")
    }
}