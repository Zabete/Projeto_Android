package com.example.tb_mob_elisabetegoncalves.ui

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityCursosMainBinding
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityDetalhesMainBinding
import com.example.tb_mob_elisabetegoncalves.model.Curso
import com.example.tb_mob_elisabetegoncalves.model.DBHelper

class DetalhesMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesMainBinding
    private lateinit var dbHelper: DBHelper
    private var curso = Curso()
    private lateinit var result: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(applicationContext)

        val i = intent

        val id = i.getIntExtra("id", 0)

        if (id != null) {
            curso = dbHelper.selectCursoByIDObjeto(id)
            binding.editNome.setText(curso.nome)
            binding.editLocal.setText(curso.local)
            binding.editDataInicio.setText(curso.data_arranque)
            binding.editDataFim.setText(curso.data_fim)
            binding.editPreco.setText(curso.preco)
            binding.editDuracao.setText(curso.duracao.toString())
            binding.editEdicao.setText(curso.edicao.toString())
        } else {
            finish()
        }

        binding.buttonEditar.setOnClickListener {
            val res = dbHelper.updateCurso(
                id = curso.id,
                nome = binding.editNome.text.toString(),
                local = binding.editLocal.text.toString(),
                data_arranque = binding.editDataInicio.text.toString(),
                data_fim = binding.editDataFim.text.toString(),
                preco = binding.editPreco.text.toString(),
                duracao = binding.editDuracao.text.toString().toInt(),
                edicao = binding.editEdicao.text.toString().toInt()
            )

            if (res > 0) {
                Toast.makeText(applicationContext, "As alterações foram guardadas", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Erro no update", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.buttonEliminar.setOnClickListener {
            val dbHelper = DBHelper(this)
            val res = dbHelper.deleteCurso(id)
            if (res > 0) {
                Toast.makeText(applicationContext, "Curso eliminado!", Toast.LENGTH_SHORT).show()
                setResult(1, i)
                result.launch(i)
                finish()
            } else {
                Toast.makeText(applicationContext, "Falha ao eliminar o curso!", Toast.LENGTH_SHORT).show()
                setResult(0, i)
                finish()
            }
        }

        binding.buttonVoltar.setOnClickListener {
            finish()
        }

    }
}