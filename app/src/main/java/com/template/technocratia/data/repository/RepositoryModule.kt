package com.template.technocratia.data.repository

import com.template.technocratia.domain.repository.ProfileRepository
import com.template.technocratia.domain.repository.UserRepositoryDB
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProfileRepository(impl: ProfileRepositoryImp): ProfileRepository

    @Binds
    abstract fun provideUserRepositoryDb(impl: UserRepositoryDbImp): UserRepositoryDB
}