package com.example.miqueiascordeiro.intelligentbank;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class deposito extends AppCompatActivity {
    DatabaseIntelligent database = new DatabaseIntelligent(this);
    SQLiteDatabase db;
    int idDeposito;
    String data;
    String hora;
    String valor;
    String descricao;
    int idUsuario;

    public deposito(){
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.descricao = descricao;

    }

    public deposito(String data,String hora,String descricao, String valor){
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.descricao = descricao;

    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

    }

    public void CadastroDeposito(View view){
        int ano,mes,dia,hora,minutos,segundos;
        if(view.getId() == R.id.bDepositar){
            EditText editValor = (EditText) findViewById(R.id.eValor);
            EditText editDescricao = (EditText) findViewById(R.id.eDescricao);
            TextView tdata = (TextView) findViewById(R.id.tdata);
            TextView thora = (TextView) findViewById(R.id.thora);

            Calendar calendario = Calendar.getInstance();
            //ano = calendario.get(Calendar.YEAR);
            mes = calendario.get(Calendar.MONTH);
            dia = calendario.get(Calendar.DAY_OF_MONTH);
            hora = calendario.get(Calendar.HOUR_OF_DAY);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

           tdata.setText(new StringBuffer().append(dia).append("/").append(mes+1));/*.append("/").
                   append(ano));*/
           thora.setText(new StringBuffer().append(" ").append(hora).append(":").append(minutos).append(":").append(segundos));

            String valor =  editValor.getText().toString();
            String descricao = editDescricao.getText().toString();
            String data = tdata.getText().toString();
            String horaS = thora.getText().toString();


            this.setData(data);
            this.setHora(horaS);
            this.setValor(valor);
            this.setDescricao(descricao);
            database.insertDeposito(this);

            alerta("Deposito realizado com sucesso!");
            editDescricao.setText("");
            editValor.setText("");
        }

    }

     /*   //Emite data do dispositivo na tela
        TextView txt = (TextView) findViewById(R.id.tdata);
        Calendar data = Calendar.getInstance();
        txt.setText(data.getTime().toString());

        Button btDepositar = (Button) findViewById(R.id.bSacar);//capturar clique do botao depositar
        Button btCancelar = (Button) findViewById(R.id.bCancelar);//capturar clique do botao cancelar

        //realizar as operacoes sobre a tela deposito
        btDepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvalor = (TextView) findViewById(R.id.eValor);
                TextView tdescricao = (TextView) findViewById(R.id.eDescricao);
                tvalor.setText("");
                tdescricao.setText("");
                alerta("Transação realizada com sucesso!");
            }
        });
        //realiza operacoes sobre o botao cancelar, excluindo os dados e emitindo mensagem
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvalor = (TextView) findViewById(R.id.eValor);
                TextView tdescricao = (TextView) findViewById(R.id.eDescricao);
                tvalor.setText("");
                tdescricao.setText("");
                alerta("Transação cancelada!");
            }
        });




    }
*/
    private void alerta(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}


