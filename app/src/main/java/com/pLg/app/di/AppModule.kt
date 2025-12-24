package com.pLg.app.di

import android.content.Context
import com.pLg.app.core.AppConfig
import com.pLg.app.core.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppConfig(@ApplicationContext context: Context): AppConfig {
        // Example: read from BuildConfig or remote config in the future
        return AppConfig(
            appName = context.packageManager.getApplicationLabel(context.applicationInfo).toString(),
            supportEmail = "support@playlearngrow.app",
            versionName = com.pLg.app.BuildConfig.VERSION_NAME
        )
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }
}
