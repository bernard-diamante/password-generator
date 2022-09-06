package com.bernarddiamante.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.bernarddiamante.passwordgenerator.databinding.ActivityMainBinding

const val TAG = "MainActivity"
const val LOWERCASE: String = "abcdefghijklmnopqrstuvwxyz"
const val UPPERCASE: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val NUMBERS: String = "0123456789"
const val SPECIAL: String = "@#=+!£$%&?"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val passwordOptions = mutableListOf<SwitchCompat>(
            binding.swLowercase,
            binding.swUppercase,
            binding.swNumber,
            binding.swSpecial
        )

        setContentView(view)

        binding.btGeneratePassword.setOnClickListener {
            val passwordOptionsChecked = mutableListOf<SwitchCompat>()
            for (i in passwordOptions) {
                if (i.isChecked) {
                    passwordOptionsChecked.add(i)
                }
            }
            val myPassword = Password()
            val length = binding.sbLength.progress
            binding.tvGeneratedPassword.text = myPassword.generatePassword(
                passwordOptionsChecked,
                length
            )
        }

        binding.tvGeneratedPassword.setOnClickListener {
            setClipboard(binding.tvGeneratedPassword)
        }

        binding.sbLength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvLength.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    private fun setClipboard(tv: TextView) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", tv.text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_LONG).show()
    }

}


class Password {
    /*
    * Generates a password given a list of required password options.
    * @param passwordOptions A list of SwitchCompat that are selected for use in
    *   password generation.
    * @return generated password
    * */
    fun generatePassword(
        passwordOptions: List<SwitchCompat>,
        length: Int
    ): String {

        val password = StringBuilder()

        // If no option is selected, return empty string
        if (passwordOptions.isEmpty()) {
            return ""
        } else {
            val charSelection = StringBuilder()
            for (i in passwordOptions) {
                charSelection.append(
                    when (i.text) {
                        "Lowercase" -> LOWERCASE
                        "Uppercase" -> UPPERCASE
                        "Numbers" -> NUMBERS
                        else -> SPECIAL
                    }
                )
            }
            Log.d(TAG, "$charSelection")
            for (i in 0 until length) {
                password.append(charSelection.random())
            }
            return password.toString()
        }
    }
}