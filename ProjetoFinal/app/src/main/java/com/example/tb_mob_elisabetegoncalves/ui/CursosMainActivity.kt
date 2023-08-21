package com.example.tb_mob_elisabetegoncalves.ui

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tb_mob_elisabetegoncalves.model.DBHelper
import com.example.tb_mob_elisabetegoncalves.databinding.ActivityCursosMainBinding
import com.example.tb_mob_elisabetegoncalves.model.Curso

class CursosMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCursosMainBinding
    private lateinit var listaCurso: ArrayList<Curso>
    private lateinit var adapter: ArrayAdapter<Curso>
    private lateinit var result: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)
        CarregarCursos(dbHelper)

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val cursoSelecionado = listaCurso[position]
            val i = Intent(this, DetalhesMainActivity::class.java)
            i.putExtra("id", cursoSelecionado.id)
            i.putExtra("nome", cursoSelecionado.nome)
            i.putExtra("local", cursoSelecionado.local)
            i.putExtra("data_arranque", cursoSelecionado.data_arranque)
            i.putExtra("data_fim", cursoSelecionado.data_fim)
            i.putExtra("preco", cursoSelecionado.preco)
            i.putExtra("duracao", cursoSelecionado.duracao)
            i.putExtra("edicao", cursoSelecionado.edicao)
            startActivity(i)
        }

        binding.buttonOrdenarNome.setOnClickListener {
            listaCurso.sortBy { it.nome }
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        }

        binding.buttonOrdenarNomeDesc.setOnClickListener {
            listaCurso.sortByDescending { it.nome }
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        }

        binding.buttonOrdenarData.setOnClickListener {
            listaCurso.sortBy { it.data_arranque }
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        }

        binding.buttonOrdenarDataDesc.setOnClickListener {
            listaCurso.sortByDescending { it.data_arranque }
            (binding.listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
        }

        binding.buttonAdicionar.setOnClickListener {
            val i = Intent(this, NovoMainActivity::class.java)
            result.launch(i)
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                listaCurso.clear()
                listaCurso.addAll(dbHelper.selectAllCursoLista())
                adapter.notifyDataSetChanged()
            }
            else if (it.data != null && it.resultCode == 0) {
                Toast.makeText(applicationContext, "Operação cancelada!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonLogout.setOnClickListener {
            finish()
        }

    }

    private fun CarregarCursos(db: DBHelper) {
        listaCurso = db.selectAllCursoLista()
        adapter = ArrayAdapter(this, R.layout.simple_list_item_1, listaCurso)
        binding.listView.adapter = adapter

    }
}
