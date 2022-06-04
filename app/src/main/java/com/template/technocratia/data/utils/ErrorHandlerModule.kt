package com.template.technocratia.data.utils

import com.template.technocratia.domain.utils.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    abstract fun provideErrorHandlerImpl(impl: ErrorHandlerImpl): ErrorHandler

}