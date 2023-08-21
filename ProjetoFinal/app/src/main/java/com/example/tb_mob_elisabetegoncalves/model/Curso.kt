package com.example.tb_mob_elisabetegoncalves.model

import java.util.Date

class Curso (var id: Int = 0, var nome: String = "", var local: String = "", var data_arranque: String = "" , var data_fim: String = "", var preco: String = "", var duracao: Int = 0, var edicao: Int = 0 ) {

    override fun toString(): String {
        return "${nome} | ${local} - ${data_arranque}"
    }
}