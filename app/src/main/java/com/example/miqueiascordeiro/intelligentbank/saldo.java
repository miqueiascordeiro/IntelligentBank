package com.example.miqueiascordeiro.intelligentbank;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Miqueias Cordeiro on 18/05/2017.
 */
public class saldo extends AppCompatActivity{
    DatabaseIntelligent database = new DatabaseIntelligent(this);
    int idSaldo;
    double saldo;
    int idUsuario;

    public saldo(int idSaldo, int idUsuario, double saldo) {
        this.idSaldo = idSaldo;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
    }

    public saldo() {
        this.idSaldo = idSaldo;
        this.idUsuario = idUsuario;
        this.saldo = saldo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

}
