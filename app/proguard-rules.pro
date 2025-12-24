# ProGuard rules for Play Learn Grow

# Mantém classes usadas pelo Hilt (injeção de dependência)
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponentManager { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }

# Mantém classes anotadas com @HiltViewModel
-keep @dagger.hilt.android.lifecycle.HiltViewModel class * { *; }

# Mantém classes do Jetpack Compose
-keep class androidx.compose.** { *; }
-keep class androidx.lifecycle.** { *; }
-keep class androidx.activity.** { *; }
-keep class androidx.navigation.** { *; }

# Mantém classes anotadas com @Composable
-keepclassmembers class * {
    @androidx.compose.runtime.Composable <methods>;
}

# Mantém classes de ViewModel
-keep class * extends androidx.lifecycle.ViewModel { *; }

# Mantém recursos usados por reflection
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Mantém classes usadas por Retrofit/Gson (se aplicável)
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }

# Mantém classes de splash screen
-keep class androidx.core.splashscreen.** { *; }

# Mantém classes de Material3
-keep class androidx.compose.material3.** { *; }

# Remove logs em produção
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
