package com.example.androidkeystore

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidkeystore.databinding.ActivityMainBinding
import java.io.IOException
import java.security.*
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.security.cert.CertificateException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val TAG = MainActivity::class.java.simpleName
    private val SAMPLE_ALIAS = "MYALIAS"
    private val ANDROID_KEY_STORE = "ANDROID_KEY_STORE"

    private var encryptor: EnCryptor? = null
    private var decryptor: DeCryptor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        encryptor = EnCryptor()

        try {
            decryptor = DeCryptor()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }


        binding.btnEncrypt.setOnClickListener {
            encryptText()
        }

        binding.btnDecrypt.setOnClickListener {
            decryptText()
        }

    }

    private fun decryptText() {
        try {
            binding.tvDecrypt.text =
                decryptor?.decryptData(SAMPLE_ALIAS, encryptor?.getEncryption(), encryptor?.getIv())

        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG, "decryptData() called with: " + e.message, e)
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        }
    }

    private fun encryptText() {
        if (binding.etEncrypt.text.toString().trim().isEmpty())
            return

        try {
            val encryptedText: ByteArray? =
                encryptor?.encryptText(SAMPLE_ALIAS, binding.etEncrypt.text.toString())
            binding.tvEncrypt.text = Base64.encodeToString(encryptedText, Base64.DEFAULT)
        } catch (e: UnrecoverableEntryException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchAlgorithmException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchProviderException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: KeyStoreException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: IOException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: NoSuchPaddingException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: InvalidKeyException) {
            Log.e(TAG, "onClick() called with: " + e.message, e)
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: SignatureException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        }
    }

}