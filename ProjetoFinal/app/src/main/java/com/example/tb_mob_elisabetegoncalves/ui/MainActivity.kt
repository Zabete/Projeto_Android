package com.example.tb_mob_elisabetegoncalves.ui

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tb_mob_elisabetegoncalves.R
import com.example.tb_mob_elisabetegoncalves.model.DBHelper
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)

        binding.buttonEntrar.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            val administrador1 = dbHelper.loginAdministrador("admin1", "pass1")
            val administrador2 = dbHelper.loginAdministrador("admin2", "pass2")
            val administrador3 = dbHelper.loginAdministrador("admin3", "pass3")

            val administrador = when (username) {
                "admin1" -> administrador1
                "admin2" -> administrador2
                "admin3" -> administrador3
                else -> null
            }

            if (administrador?.username == username && administrador?.password == password) {
                Toast.makeText(applicationContext, "Login Válido!", Toast.LENGTH_SHORT).show()
                val i = Intent(this, CursosMainActivity::class.java)
                startActivity(i)
            } else {
                Toast.makeText(applicationContext, "Login Inválido!", Toast.LENGTH_SHORT).show()
            }

            binding.editUsername.setText("")
            binding.editPassword.setText("")
        }

        binding.buttonSobre.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.barulho)
            mediaPlayer.start()
            startActivity(Intent(this, SobreActivity::class.java))
        }

    }
}
