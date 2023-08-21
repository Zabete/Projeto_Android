package com.example.tb_mob_elisabetegoncalves.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context) : SQLiteOpenHelper(context, "dbexemplo", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE administrador (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)",
        "INSERT INTO administrador (username, password) VALUES ('admin1', 'pass1')",
        "INSERT INTO administrador (username, password) VALUES ('admin2', 'pass2')",
        "INSERT INTO administrador (username, password) VALUES ('admin3', 'pass3')",

        "CREATE TABLE curso (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, local TEXT, data_arranque Text, data_fim Text, preco TEXT, duracao INTEGER, edicao INTEGER)",
        "INSERT INTO curso (nome, local, data_arranque, data_fim, preco, duracao, edicao) VALUES ('Visualização de Dados', 'Porto', '2023-10-12', '2023-11-07', 'Gratuito', 25, 1)",
        "INSERT INTO curso (nome, local, data_arranque, data_fim, preco, duracao, edicao) VALUES ('Data Analyst (SQL)', 'Porto', '2023-09-18', '2023-11-14', 'Gratuito', 50, 1)",
        "INSERT INTO curso (nome, local, data_arranque, data_fim, preco, duracao, edicao) VALUES ('Análise de Dados', 'Aveiro', '2023-09-20', '2024-01-23', 'Gratuito', 300, 1)",
        "INSERT INTO curso (nome, local, data_arranque, data_fim, preco, duracao, edicao) VALUES ('Design UX/UI', 'Porto', '2023-09-21', '2023-11-20', 'Gratuito', 75, 1)"
    )

    override fun onCreate(db: SQLiteDatabase?) {
        sql.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE curso")
        onCreate(db)
    }

    fun loginAdministrador(username: String, password: String): Administrador {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM administrador WHERE username = ? AND password = ?", arrayOf(username, password))
        var administrador = Administrador()

        if (cursor.count == 1) {
            cursor.moveToFirst()

            val idIndex = cursor.getColumnIndex("id")
            val usernameIndex = cursor.getColumnIndex("username")
            val passwordIndex = cursor.getColumnIndex("password")

            val id = cursor.getInt(idIndex)
            val username = cursor.getString(usernameIndex)
            val password = cursor.getString(passwordIndex)

            administrador = Administrador(id, username, password)
        }
        db.close()
        return administrador
    }

    fun insertCurso(nome: String, local: String, data_arranque: String, data_fim: String, preco:String, duracao: Int, edicao: Int ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("local", local)
        contentValues.put("data_arranque", data_arranque)
        contentValues.put("data_fim", data_fim)
        contentValues.put("preco", preco)
        contentValues.put("duracao", duracao)
        contentValues.put("edicao", edicao)
        val res = db.insert("curso", null, contentValues)
        db.close()
        return res
    }

    fun updateCurso(id: Int, nome: String, local: String, data_arranque: String, data_fim: String, preco:String, duracao: Int, edicao: Int ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("local", local)
        contentValues.put("data_arranque", data_arranque)
        contentValues.put("data_fim", data_fim)
        contentValues.put("preco", preco)
        contentValues.put("duracao", duracao)
        contentValues.put("edicao", edicao)
        val res = db.update("curso", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteCurso(id: Int): Int {
        val db = this.writableDatabase
        val res = db.delete("curso", "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun selectAllCursoCursor(): Cursor {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM curso", null)
        db.close()
        return cursor
    }

    fun selectCursoByIDCursor(id: Int): Cursor {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM curso WHERE id = ?", arrayOf(id.toString()))
        db.close()
        return cursor
    }

    fun selectCursoByIDObjeto(id: Int): Curso {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM curso WHERE id = ?", arrayOf(id.toString()))

        var curso = Curso()

        if (cursor.count == 1) {
            cursor.moveToFirst()

            val idIndex = cursor.getColumnIndex("id")
            val nomeIndex = cursor.getColumnIndex("nome")
            val localIndex = cursor.getColumnIndex("local")
            val data_arranqueIndex = cursor.getColumnIndex("data_arranque")
            val data_fimIndex = cursor.getColumnIndex("data_fim")
            val precoIndex = cursor.getColumnIndex("preco")
            val duracaoIndex = cursor.getColumnIndex("duracao")
            val edicaoIndex = cursor.getColumnIndex("edicao")

            val id = cursor.getInt(idIndex)
            val nome = cursor.getString(nomeIndex)
            val local = cursor.getString(localIndex)
            val data_arranque = cursor.getString(data_arranqueIndex)
            val data_fim = cursor.getString(data_fimIndex)
            val preco = cursor.getString(precoIndex)
            val duracao = cursor.getInt(duracaoIndex)
            val edicao = cursor.getInt(edicaoIndex)

            curso = Curso(id, nome, local, data_arranque, data_fim, preco, duracao, edicao)
        }
        db.close()
        return curso
    }

    fun selectAllCursoLista(): ArrayList<Curso> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM curso", null)

        val listaCurso: ArrayList<Curso> = ArrayList()

        if (cursor.count > 0) {
            cursor.moveToFirst()
            val idIndex = cursor.getColumnIndex("id")
            val nomeIndex = cursor.getColumnIndex("nome")
            val localIndex = cursor.getColumnIndex("local")
            val data_arranqueIndex = cursor.getColumnIndex("data_arranque")
            val data_fimIndex = cursor.getColumnIndex("data_fim")
            val precoIndex = cursor.getColumnIndex("preco")
            val duracaoIndex = cursor.getColumnIndex("duracao")
            val edicaoIndex = cursor.getColumnIndex("edicao")

            do {
                val id = cursor.getInt(idIndex)
                val nome = cursor.getString(nomeIndex)
                val local = cursor.getString(localIndex)
                val data_arranque = cursor.getString(data_arranqueIndex)
                val data_fim = cursor.getString(data_fimIndex)
                val preco = cursor.getString(precoIndex)
                val duracao = cursor.getInt(duracaoIndex)
                val edicao = cursor.getInt(edicaoIndex)
                listaCurso.add(Curso(id, nome, local, data_arranque, data_fim,preco, duracao, edicao))
            } while (cursor.moveToNext())
        }
        db.close()
        return listaCurso
    }
}