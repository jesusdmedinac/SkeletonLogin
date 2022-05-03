package com.mupper.skeletonlogin.di

import com.mupper.skeletonlogin.domain.usecase.LoginUseCase
import com.mupper.skeletonlogin.domain.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindsLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase
}