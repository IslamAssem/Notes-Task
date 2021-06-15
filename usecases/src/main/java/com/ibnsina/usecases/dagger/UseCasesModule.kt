package com.ibnsina.usecases.dagger

import android.app.Application
import com.ibnsina.entites.login.LoginResponse
import com.ibnsina.entites.login.User
import com.ibnsina.usecases.datasource.abstraction.NotesDataSource
import com.ibnsina.usecases.datasource.database.NotesDao
import com.ibnsina.usecases.datasource.database.NotesDatabase
import com.ibnsina.usecases.datasource.implementation.NotesDataSourceImpl
import com.ibnsina.usecases.datasource.remote.abstraction.RemoteDataSource
import com.ibnsina.usecases.datasource.remote.gateways.ServerGateway
import com.ibnsina.usecases.datasource.remote.implementation.RemoteDataSourceImpl
import com.ibnsina.usecases.repositories.abstraction.Repository
import com.ibnsina.usecases.repositories.implementation.RepositoryImpl
import com.ibnsina.usecases.usecases.abstraction.LoginUseCase
import com.ibnsina.usecases.usecases.abstraction.NotesUseCase
import com.ibnsina.usecases.usecases.implementation.LoginUseCaseImpl
import com.ibnsina.usecases.usecases.implementation.NotesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import retrofit2.Response

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    companion object {
        const val EMAIL = "test@ibnsina.com"
        const val PASSWORD = "123456"
    }

    @Provides
    fun provideServerGateWay() = object : ServerGateway {
        override suspend fun login(email: String, password: String): Response<LoginResponse> {
            delay(1000)
            if (email == EMAIL && password == PASSWORD)
                return Response.success(
                    LoginResponse(1, User(1, "Test User", EMAIL, "dummy_token")))
            if (email != EMAIL)
                return Response.success(LoginResponse(2, null))
            return Response.success(LoginResponse(3, null))
        }

    }

    @Provides
    fun provideDatabaseDao(application: Application): NotesDao =
        NotesDatabase.getInstance(application).notesDao

    @Provides
    fun provideRemoteDataSource(serverGateway: ServerGateway): RemoteDataSource =
        RemoteDataSourceImpl(serverGateway)

    @Provides
    fun provideNotesDataSource(dao: NotesDao): NotesDataSource =
        NotesDataSourceImpl(dao)

    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        notesDataSource: NotesDataSource,
    ): Repository = RepositoryImpl(remoteDataSource, notesDataSource)

    @Provides
    fun provideLoginUseCase(repository: Repository): LoginUseCase = LoginUseCaseImpl(repository)

    @Provides
    fun provideNotesUseCase(repository: Repository): NotesUseCase = NotesUseCaseImpl(repository)
}