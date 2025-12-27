package org.playlearn.core.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.playlearn.core.util.DefaultDispatchersProvider
import org.playlearn.core.util.DispatchersProvider
import org.playlearn.data.local.AppDatabase
import org.playlearn.data.remote.ApiService
import org.playlearn.data.repo.ContentRepositoryImpl
import org.playlearn.data.repo.PreferencesRepositoryImpl
import org.playlearn.data.repo.UserRepositoryImpl
import org.playlearn.domain.repo.ContentRepository
import org.playlearn.domain.repo.PreferencesRepository
import org.playlearn.domain.repo.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideDispatchers(): DispatchersProvider = DefaultDispatchersProvider()

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase =
        Room.databaseBuilder(ctx, AppDatabase::class.java, "playlearn.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Provides @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.playlearn.example/") // replace in real
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

    @Provides @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides @Singleton
    fun provideUserRepo(db: AppDatabase, api: ApiService): UserRepository =
        UserRepositoryImpl(db.userDao(), api)

    @Provides @Singleton
    fun provideContentRepo(db: AppDatabase, api: ApiService): ContentRepository =
        ContentRepositoryImpl(db.contentDao(), api)

    @Provides @Singleton
    fun providePreferencesRepo(db: AppDatabase): PreferencesRepository =
        PreferencesRepositoryImpl(db.preferencesDao())
}
