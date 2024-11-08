package com.chandra.practice.pointofsaleapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.Base64

object EncryptionUtils {

    private const val ALGORITHM = "AES"
    private const val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding"

    // Encrypt plaintext using AES encryption
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun encrypt(plainText: String, secretKey: SecretKey): String {
        // Generate a random IV for each encryption
        val iv = generateRandomIV()
        val ivParameterSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)

        // Encrypt the plaintext
        val encryptedBytes = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))

        // Encode the encrypted bytes along with IV to Base64
        val encryptedText = Base64.getEncoder().encodeToString(encryptedBytes)
        val encodedIV = Base64.getEncoder().encodeToString(iv)

        // Return a concatenation of the encrypted text and IV, separated by a delimiter
        return "$encryptedText:$encodedIV"
    }

    // Decrypt the encrypted text using AES encryption
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(Exception::class)
    fun decrypt(encryptedTextWithIV: String, secretKey: SecretKey): String {
        // Split the encrypted text and IV
        val parts = encryptedTextWithIV.split(":")
        val encryptedText = parts[0]
        val encodedIV = parts[1]

        // Decode the Base64 encoded encrypted bytes and IV
        val encryptedBytes = Base64.getDecoder().decode(encryptedText)
        val iv = Base64.getDecoder().decode(encodedIV)

        val ivParameterSpec = IvParameterSpec(iv)

        val cipher = Cipher.getInstance(CIPHER_TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)

        // Decrypt the bytes
        val decryptedBytes = cipher.doFinal(encryptedBytes)

        // Convert the decrypted bytes back to a string
        return String(decryptedBytes, StandardCharsets.UTF_8)
    }

    // Generate a random IV for encryption
    private fun generateRandomIV(): ByteArray {
        val iv = ByteArray(16) // AES block size is 16 bytes
        val random = SecureRandom()
        random.nextBytes(iv)
        return iv
    }

    // Generate a secret key (for demonstration purposes)
    @Throws(Exception::class)
    fun generateSecretKey(): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM)
        keyGenerator.init(256) // 256-bit key for AES
        return keyGenerator.generateKey()
    }
}


