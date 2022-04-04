package com.example.miqueiascordeiro.intelligentbank;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase; //Reponsavel pelo criacao, alteracao, exclusao e executar comandos no gerenciamento
//do bando de dados.
import android.database.sqlite.SQLiteOpenHelper; //Classe responsavel por auxiliar no gerenciamento da criacao de banco
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
// e no controle de versao.

/**
 * Created by Miqueias Cordeiro on 12/05/2017.
 */
public class DatabaseIntelligent extends SQLiteOpenHelper {
    private static final int VERSAO_BANCODADOS = 1;
    private static final String NOME_BANCODADOS = "IntelligentBankDB.db";
    private static final String NOME_TABELA_USUARIO = "usuario";
    private static final String NOME_TABELA_SAQUE = "saque";
    private static final String NOME_TABELA_DEPOSITO = "deposito";
    private static final String NOME_TABELA_SALDO = "saldo";
    private static final String NOME_TABELA_OPERACAO = "operacao";

    private static final String IDUSUARIO = "idUsuario";
    private static final String IDSAQUE = "idSaque";
    private static final String IDOPERACAO = "idOperacao";
    private static final String IDDEPOSITO = "idDeposito";
    private static final String IDSALDO = "idSaldo";
    private static final String SALDO = "saldo";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String SOBRENOME = "sobrenome";
    private static final String SENHA = "senha";
    private static final String VALOR = "valor";
    private static final String DESCRICAO = "descricao";
    private static final String DATA = "data";
    private static final String HORA = "hora";

    private SQLiteDatabase database;
    private ArrayList<deposito> DepList;
    private static final String CRIAR_TABELA_USUARIO = "create table usuario (idUsuario integer primary key not null,"+
            "nome text not null, email text not null, sobrenome text not null, senha text not null, saldo real not null);";

    private static final String CRIAR_TABELA_DEPOSITO = "create table deposito (idDeposito integer primary key not null," +
    "data text not null, hora text not null, valor text not null, descricao text not null, idUsuario integer not null,"+
    "foreign key(idUsuario) references usuario(idUsuario));";

    private static final String CRIAR_TABELA_SAQUE =  "create table saque (idSaque integer primary key not null," +
    "data text not null, hora text not null, valor text not null, descricao text not null,"+
    "idUsuario integer not null, foreign key(idUsuario) references usuario(idUsuario));";

    private static final String CRIAR_TABELA_OPERACAO = "create table operacao (idOperacao integer primary key not null," +
    "data text not null, hora text not null, valor text not null, descricao text not null," +
    "idUsuario integer not null, foreign key(idUsuario) references usuario(idUsuario));";


    private static final String DELETAR_TABELA_USUARIO = "DROP TABLE IF EXISTS usuario";
    private static final String DELETAR_TABELA_DEPOSITO = "DROP TABLE IF EXISTS deposito";
    private static final String DELETAR_TABELA_SAQUE= "DROP TABLE IF EXISTS saque";
    private static final String DELETAR_TABELA_OPERACAO= "DROP TABLE IF EXISTS operacao";

    public DatabaseIntelligent(Context context) {
        super(context, NOME_BANCODADOS, null, VERSAO_BANCODADOS);
    }

    public void insertSaque(saque saque){
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        String query ="select * from saque";
        Cursor cursor = database.rawQuery(query,null);

        String queryUser = "select * from usuario";
        Cursor cursorUser = database.rawQuery(queryUser,null);

        cursorUser.moveToFirst();
        String idUser= cursorUser.getString(cursorUser.getColumnIndex("idUsuario"));

        int contSaque = cursor.getCount();
        try{
            values.put(IDSAQUE,contSaque);
            values.put(DATA,saque.getData());
            values.put(HORA,saque.getHora());
            values.put(VALOR, saque.getValor());
            values.put(DESCRICAO, saque.getDescricao());
            values.put(IDUSUARIO,new StringBuilder().append(idUser).toString());//captura dados da coluna seleciona atraves do tostring
            database.insert("saque", null, values);

            String queryOperacao = "select * from operacao";
            String querySaque = "select * from saque";
            Cursor cursorOperacao = database.rawQuery(queryOperacao,null);
            Cursor cursorSaque = database.rawQuery(querySaque,null);
            int contOperacao = cursorOperacao.getCount();
            ContentValues valuesOperacao = new ContentValues();
            cursorSaque.moveToLast();

            valuesOperacao.put(IDOPERACAO,contOperacao);
            valuesOperacao.put(DATA,cursorSaque.getString(1));
            valuesOperacao.put(HORA,cursorSaque.getString(2));
            valuesOperacao.put(VALOR, cursorSaque.getString(3));
            valuesOperacao.put(DESCRICAO, cursorSaque.getString(4));
            valuesOperacao.put(IDUSUARIO,cursorSaque.getString(5));
            database.insert(NOME_TABELA_OPERACAO,null,valuesOperacao);

        }finally {
            database.close();
        }


    }

    public void insertDeposito(deposito dep) {
        database = getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from deposito";
        Cursor cursor = database.rawQuery(query, null);


        String queryUser = "select * from usuario";
        Cursor cursorUser = database.rawQuery(queryUser,null);
        cursorUser.moveToFirst(); // vai na primeira linha do banco
        String idUser = cursorUser.getString(cursorUser.getColumnIndex("idUsuario"));//selecionar a coloca desejada

        int cont = cursor.getCount();



        try {
            values.put(IDDEPOSITO,cont);
            values.put(DATA,dep.getData());
            values.put(HORA,dep.getHora());
            values.put(VALOR, dep.getValor());
            values.put(DESCRICAO, dep.getDescricao());
            values.put(IDUSUARIO,new StringBuilder().append(idUser).toString());//captura dados da coluna seleciona atraves do tostring
            database.insert("deposito", null, values);

            String queryOperacao = "select * from operacao";
            String queryDeposito = "select * from deposito";
            Cursor cursorOperacao = database.rawQuery(queryOperacao,null);
            Cursor cursorDeposito = database.rawQuery(queryDeposito,null);
            int contOperacao = cursorOperacao.getCount();
            ContentValues valuesOperacao = new ContentValues();
            cursorDeposito.moveToLast();

            valuesOperacao.put(IDOPERACAO,contOperacao);
            valuesOperacao.put(DATA,cursorDeposito.getString(1));
            valuesOperacao.put(HORA,cursorDeposito.getString(2));
            valuesOperacao.put(VALOR, cursorDeposito.getString(3));
            valuesOperacao.put(DESCRICAO, cursorDeposito.getString(4));
            valuesOperacao.put(IDUSUARIO,cursorDeposito.getString(5));
            database.insert(NOME_TABELA_OPERACAO,null,valuesOperacao);
        }finally {
            database.close();
        }
    }

    public Double ResgateSaldo(ArrayList<deposito> deps){
        database = getWritableDatabase();
        double saldo=0,valor=0;

        for (int i=0;i<deps.size();i++){
            valor = Double.parseDouble(deps.get(i).getValor());
            saldo = saldo + valor;
        }
        String query = "update "+ NOME_TABELA_USUARIO +" set "+ SALDO +" = '"+ saldo +"'";
        database.execSQL(query);
        return saldo;
    }

    /*public void ResgateOperacao(Double saque){
        database = getWritableDatabase();
        double valorRetorno=0;
        ContentValues values = new ContentValues();
        String querySaldo = "select saldo from usuario";
        Cursor cursor = database.rawQuery(querySaldo,null);
        double valor = Double.parseDouble(cursor.getString(0));
        valorRetorno = valor - saque;
        String query = "update "+ NOME_TABELA_USUARIO +" set "+ SALDO +" = '"+ valor +"'";
        database.execSQL(query);
    }*/

    public void insertUsuario(Usuario user){
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        double saldoUser = 0;
        user.setSaldo(saldoUser);

        String query = ("select * from " + NOME_TABELA_USUARIO);
        Cursor cursor = database.rawQuery(query,null);
        int cont = cursor.getCount();
        try {
            values.put(IDUSUARIO, cont);
            values.put(NOME, user.getNome());
            values.put(EMAIL, user.getEmail());
            values.put(SOBRENOME, user.getSobrenome());
            values.put(SENHA, user.getSenha());
            values.put(SALDO,user.getSaldo());
            database.insert(NOME_TABELA_USUARIO, null, values);
        }finally {
            database.close();
        }
    }
    public boolean ConsultaExistenciaUsuario(){//consulta se possui usuario na tabela
        database = getReadableDatabase();
        String query = "SELECT COUNT(*) FROM usuario";
        Cursor cursor = database.rawQuery(query,null);
         if(cursor != null){
             cursor.moveToFirst();
             if (cursor.getInt(0)==0){ //tabela vazia
                 return true;// tabela vazia
             }
             }
            return false;//tabela com pelo menos um elemento
         }

    public void DeletarTabela(){//apagar tabela usuario
        database.delete(NOME_TABELA_USUARIO,null,null);
    }
    public void DeletarTabelaDeposito(){//apagar tabela usuario
        database.delete(NOME_TABELA_DEPOSITO,null,null);
    }

    public boolean AutenticaUsuario(String nome, String senha){//Verifica se os dados login e senha confere com cadastrados no BD.
        database = this.getReadableDatabase();
        String query = "select * from usuario where nome==nome AND senha==senha";
        String queryVerif = "select count(*) from usuario";
        Cursor cursor = database.rawQuery(query,null);
        Cursor cursor1 = database.rawQuery(queryVerif,null);
        if(cursor1 != null){
            cursor.moveToFirst();
            if((cursor.getString(1).equals(nome)) && cursor.getString(4).equals(senha)){
                return true;
            }else{return false;}
        }
        return false;
    }

    public Cursor getListContents() { //retorna os valores da tabela operacao
        database = this.getWritableDatabase();
        String query = "select data,hora,descricao,valor from operacao where idUsuario==0";
        Cursor data = database.rawQuery(query, null);
        return data;
    }


        @Override
    public void onCreate(SQLiteDatabase db) {//Criar banco
        db.execSQL(CRIAR_TABELA_USUARIO);
        db.execSQL(CRIAR_TABELA_DEPOSITO);
        db.execSQL(CRIAR_TABELA_SAQUE);
        db.execSQL(CRIAR_TABELA_OPERACAO);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//Atualizar banco
        db.execSQL(DELETAR_TABELA_USUARIO);
        db.execSQL(DELETAR_TABELA_DEPOSITO);
        db.execSQL(DELETAR_TABELA_SAQUE);
        db.execSQL(DELETAR_TABELA_OPERACAO);
        onCreate(db);
    }

}
