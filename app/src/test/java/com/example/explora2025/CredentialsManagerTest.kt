// Put your package name here. Check your activity for reference.
package com.example.explora2025

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {
    private val credentialsManager = CredentialsManager()

    @Test
    fun givenNewEmailAndPassword_whenRegister_thenReturnSuccessMessage() {
        val result = credentialsManager.register("test@te.st", "1234")
        assertEquals("Registration is successful! :)", result)
    }

    @Test
    fun givenExistingEmail_whenRegister_thenReturnErrorMessage() {
        credentialsManager.register("test@te.st", "1234")
        val result = credentialsManager.register("TEST@te.st", "5678")
        assertEquals("Error!! Email is already registered.", result)
    }

    @Test
    fun givenCaseInsensitiveEmail_whenRegister_thenTreatAsSame() {
        credentialsManager.register("test@te.st", "1234")
        val result = credentialsManager.register("TEST@TE.ST", "5678")
        assertEquals("Error!! Email is already registered.", result)
    }

    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenRegisteredAccount_whenAuthenticate_thenReturnTrue() {
        credentialsManager.register("test@te.st", "1234")
        val isAuthenticated = credentialsManager.authenticate("test@te.st", "1234")
        assertTrue(isAuthenticated)
    }

    @Test
    fun givenWrongEmailFormat_whenRegister_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("invalid-email")
        assertEquals(false, isEmailValid)
    }

    @Test
    fun givenProperEmailFormat_whenRegister_thenReturnTrue() {
        val isEmailValid = credentialsManager.isEmailValid("test@example.com")
        assertEquals(true, isEmailValid)
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val isPasswordValid = credentialsManager.isPasswordValid("")
        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val isPasswordValid = credentialsManager.isPasswordValid("securepassword123")
        assertEquals(true, isPasswordValid)
    }
}
