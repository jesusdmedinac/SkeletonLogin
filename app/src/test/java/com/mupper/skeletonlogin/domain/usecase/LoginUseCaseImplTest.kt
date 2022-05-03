package com.mupper.skeletonlogin.domain.usecase

import com.mupper.skeletonlogin.domain.exception.LoginException
import com.mupper.skeletonlogin.domain.model.UserCredentials
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


@ExperimentalCoroutinesApi
class LoginUseCaseImplTest {
    private lateinit var loginUseCaseImpl: LoginUseCaseImpl

    @Before
    fun setUp() {
        loginUseCaseImpl = LoginUseCaseImpl()
    }

    @Test(expected = LoginException::class)
    fun `invoke should throw LoginException given userCredentials are invalid`() = runTest {
        // Given
        val email = Random.nextInt().toString()
        val password = Random.nextInt().toString()
        val userCredentials = UserCredentials(email, password)

        // When
        loginUseCaseImpl(userCredentials)
    }

    @Test
    fun `invoke just run given userCredentials are valid`() = runTest {
        // Given
        val email = "user@baubap.com"
        val password = "pass123"
        val userCredentials = UserCredentials(email, password)

        // When
        loginUseCaseImpl(userCredentials)
    }
}