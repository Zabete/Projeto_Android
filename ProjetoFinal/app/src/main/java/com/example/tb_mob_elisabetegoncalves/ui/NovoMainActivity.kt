package com.example.tb_mob_elisabetegoncalves.ui

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tb_mob_elisabetegoncalves.R
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityDetalhesMainBinding
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityNovoMainBinding
import com.example.tb_mob_elisabetegoncalves.model.DBHelper

class NovoMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNovoMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)
        val i = intent

        binding.buttonGuardar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val local = binding.editLocal.text.toString()
            val dataInicio = binding.editDataFim.text.toString()
            val dataFim = binding.editDataFim.text.toString()
            val preco = binding.editPreco.text.toString()
            val duracao = binding.editDuracao.text.toString().toInt()
            val edicao = binding.editEdicao.text.toString().toInt()

            if (nome.isNotEmpty() && local.isNotEmpty() && dataInicio.isNotEmpty() && dataFim.isNotEmpty() && preco.isNotEmpty()) {
                val res =
                    dbHelper.insertCurso(nome, local, dataInicio, dataFim, preco, duracao, edicao)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Curso inserido!", Toast.LENGTH_SHORT).show()
                    setResult(1, i)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Erro ao inserir o curso!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.buttonVoltar.setOnClickListener {
            finish()
        }

    }
}