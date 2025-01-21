package com.example.explora2025
import android.app.Application
class ExploraApplication : Application() {
    val credentialsManager by lazy { CredentialsManager() }
}
