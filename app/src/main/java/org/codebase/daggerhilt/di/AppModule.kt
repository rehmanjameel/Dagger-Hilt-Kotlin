package org.codebase.daggerhilt.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.codebase.daggerhilt.utils.BaseApplication
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext appContext: Context) : BaseApplication {
        return appContext as BaseApplication
    }

//    @Impl3
    @Singleton
    @Provides
    fun provideRandomString() : String {
        return "Hey look a random String!!! GIFHI"
    }
}