package com.example.tb_mob_elisabetegoncalves.ui

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tb_mob_elisabetegoncalves.R
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityMainBinding
import com.example.tb_mob_elisabetegoncalves.databinding.ActivitySobreBinding

class SobreActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonVoltar.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.som)
            mediaPlayer.start()
            finish()
        }
    }
}