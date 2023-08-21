package com.example.tb_mob_elisabetegoncalves.model

class Administrador (var id: Int = 0, var username: String = "", var password: String = "") {

    override fun toString(): String {
        return username
    }
}