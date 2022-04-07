package com.template.technocratia.data.repository

import com.template.technocratia.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePlannedListRepository(impl: ProfileRepositoryImp) : ProfileRepository
}