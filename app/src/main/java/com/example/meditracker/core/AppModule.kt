package com.example.meditracker.core

import com.example.meditracker.data.api.UserAnalyzesApi
import com.example.meditracker.data.api.UserAuthenticationApi
import com.example.meditracker.data.api.UserDiaryApi
import com.example.meditracker.data.repository.UserAnalyzesRepository
import com.example.meditracker.data.repository.UserDiaryRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserAnalyzesRepository(
        userAnalyzesApi: UserAnalyzesApi,
    ): UserAnalyzesRepository {
        return UserAnalyzesRepository(
            userAnalyzesApi = userAnalyzesApi,
        )
    }

    @Provides
    @Singleton
    fun provideUserDiaryRepository(
        userDiaryApi: UserDiaryApi,
    ): UserDiaryRepository {
        return UserDiaryRepository(
            userDiaryApi = userDiaryApi,
        )
    }

    @Provides
    @Singleton
    fun provideUserAnalyzesApi(
        auth: FirebaseAuth,
        database: FirebaseFirestore,
    ): UserAnalyzesApi {
        return UserAnalyzesApi(
            auth = auth,
            database = database,
        )
    }

    @Provides
    @Singleton
    fun provideUserAuthenticationApi(
        auth: FirebaseAuth,
        database: FirebaseFirestore,
    ): UserAuthenticationApi {
        return UserAuthenticationApi(
            auth = auth,
            database = database,
        )
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideDataBase(): FirebaseFirestore {
        return Firebase.firestore
    }
}