package com.example.miqueiascordeiro.intelligentbank;

/**
 * Created by Miqueias Cordeiro on 12/05/2017.
 */

public class Usuario {
    int idUsuario;
    String nome;
    String email;
    String sobrenome;
    String senha;
    Double saldo;


    public Usuario() {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.sobrenome = sobrenome;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return idUsuario;
    }

    public void setId(int id) {
        this.idUsuario = id;
    }
}
