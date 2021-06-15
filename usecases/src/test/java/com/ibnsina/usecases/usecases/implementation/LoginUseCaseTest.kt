package com.ibnsina.usecases.usecases.implementation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import com.ibnsina.entites.login.LoginStatus
import com.ibnsina.entites.login.User
import com.ibnsina.usecases.dagger.UseCasesModule
import com.ibnsina.usecases.datasource.remote.gateways.ServerGateway
import com.ibnsina.usecases.usecases.abstraction.LoginUseCase
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @Test
    fun `login() when right credential return user`() {
        //arrange
        val mock = mock<LoginUseCase>() {
            onBlocking { login("test@ibnsina.com", "123456") } doReturn
                    flow {
                        emit(Resource.success(LoginResponse(1,
                            User(1, "Test User", UseCasesModule.EMAIL, "dummy_token"))))
                    }
        }
        //Act
        //skip delay
        runBlockingTest {
            val result = mock.login("test@ibnsina.com", "123456")
            //assert
            assertEquals(result.first().data!!.loginStatus, LoginStatus.LOGIN_SUCCESS)
        }
    }
    @Test
    fun `login() when wrong email return email not exist`() {
        //arrange
        val mock = mock<LoginUseCase>() {
            onBlocking { login("test@ibnsina.com2", "123456") } doReturn
                    flow {
                        emit(Resource.success(LoginResponse(2,
                            null)))
                    }
        }
        //Act
        //skip delay
        runBlockingTest {
            val result = mock.login("test@ibnsina.com2", "123456")
            //assert
            assertEquals(result.first().data!!.loginStatus, LoginStatus.EMAIL_ERROR)
        }
    }

    @Test
    fun `login() when wrong password return wrong password status`() {
        //arrange
        val mock = mock<LoginUseCase>() {
            onBlocking { login("test@ibnsina.com2", "123456") } doReturn
                    flow {
                        emit(Resource.success(LoginResponse(2,
                            null)))
                    }
        }
        //Act
        //skip delay
        runBlockingTest {
            val result = mock.login("test@ibnsina.com2", "123456")
            //assert
            assertEquals(result.first().data!!.loginStatus, LoginStatus.EMAIL_ERROR)
        }
    }

}