package com.bernarddiamante.passwordgenerator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bernarddiamante.passwordgenerator.databinding.ActivityMainBinding

const val TAG = "MainActivity"
const val LOWERCASE : String = "abcdefghijklmnopqrstuvwxyz"
const val UPPERCASE : String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val NUMBERS : String = "0123456789"
const val SPECIAL : String = "@#=+!£$%&?"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btCreatePassword.setOnClickListener {
            val myPassword = Password()
            val containsLowercase = binding.swLowercase.isChecked
            val containsUppercase = binding.swUppercase.isChecked
            val containsNumber = binding.swNumber.isChecked
            val containsSpecial = binding.swSpecial.isChecked
            val length = binding.sbLength.progress
            binding.tvCreatedPassword.text = myPassword.generatePassword(
                containsLowercase,
                containsUppercase,
                containsNumber,
                containsSpecial,
                length)

        }
    }

}


class Password {
    fun generatePassword(containsLowercase: Boolean,
                         containsUppercase: Boolean,
                         containsNumber: Boolean,
                         containsSpecial: Boolean,
                         length: Int): String {
        Log.d(TAG, "Lowercase: $containsLowercase\n" +
                "Uppercase: $containsUppercase\n" +
                "Number: $containsNumber\n" +
                "Special: $containsSpecial\n" +
                "Length: $length")

        return "Hello"
    }
}