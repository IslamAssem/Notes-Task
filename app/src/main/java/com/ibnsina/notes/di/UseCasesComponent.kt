package com.ibnsina.notes.di

import com.ibnsina.usecases.dagger.UseCasesModule
import dagger.Component
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
//interface CoreComponent {
//
//
//    fun provideOkHttpClient(): OkHttpClient
//    fun provideGson(): Gson
//    fun provideGsonConverterFactory(): GsonConverterFactory
//}
@Component(modules = [UseCasesModule::class,FeaturesModule::class])
@Singleton
interface UseCasesComponent {
    @Component.Builder interface Builder {
        fun build(): UseCasesComponent
    }
}