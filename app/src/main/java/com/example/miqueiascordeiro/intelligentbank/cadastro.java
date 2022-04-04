package com.example.miqueiascordeiro.intelligentbank;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class cadastro extends AppCompatActivity {
    DatabaseIntelligent database = new DatabaseIntelligent(this);
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void cadastroUser(View view) {
        if (view.getId() == R.id.bSalvar) {
            EditText tviewnome = (EditText) findViewById(R.id.enome);
            EditText tviewemail = (EditText) findViewById(R.id.eemail);
            EditText tviewsobrenome = (EditText) findViewById(R.id.esobrenome);
            EditText tviewsenha = (EditText) findViewById(R.id.esenha);
            EditText tviewconfirSenha = (EditText) findViewById(R.id.econfirmarsenha);

            String nome = tviewnome.getText().toString();
            String email = tviewemail.getText().toString();
            String sobrenome = tviewsobrenome.getText().toString();
            String senha = tviewsenha.getText().toString();
            String confirmSenha = tviewconfirSenha.getText().toString();

            if (senha.equals(confirmSenha)) {
                    Usuario user = new Usuario();
                    user.setNome(nome);
                    user.setEmail(email);
                    user.setSobrenome(sobrenome);
                    user.setSenha(senha);

                    if(database.ConsultaExistenciaUsuario() == true){
                        database.insertUsuario(user);
                        tviewnome.setText("");
                        tviewemail.setText("");
                        tviewconfirSenha.setText("");
                        tviewsenha.setText("");
                        tviewsobrenome.setText("");
                        alert("Cadastro realizado com sucesso!");
                        Intent intent = new Intent(cadastro.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        alert("Tabela NAO esta vazia!");
                        database.DeletarTabela();
                    }
            } else {
                alert("Senha NÃO confere. Favor verique!");
            }
        }else{
            alert("ID de botões diferentes.");
        }
    }

    public void CliqueBotaoSalvar( View v){
        //Button btSalvar = (Button) findViewById(R.id.bSalvar); //captura clique do botao salvar
        //Button btCancelar = (Button) findViewById(R.id.bCancelar);//captura clique do botao cancelar
        if(v.getId() == R.id.bSalvar) {

            //realizar operacoes ao ser clicado no botao cadastro
            //btSalvar.setOnClickListener(new View.OnClickListener() {
            //  @Override
            //public void onClick(View v) {
/*                TextView txtnome = (TextView) findViewById(R.id.enome);
                TextView txtsobrenome = (TextView) findViewById(R.id.esobrenome);
                TextView txtemail = (TextView) findViewById(R.id.eemail);
                TextView txtconfirmarSenha = (TextView) findViewById(R.id.econfirmarsenha);
                TextView txtsenha = (TextView) findViewById(R.id.esenha);
                txtnome.setText("");
                txtemail.setText("");
                txtconfirmarSenha.setText("");
                txtsenha.setText("");
                txtsobrenome.setText("");
                alerta("Usuário cadastrado com sucesso!");
            }else{
            alerta("ID DO BOTAO DIFERENTE!");
             }*/
          }
        }
    //);
public void CliqueBotaoCancelar(View view){
    if(view.getId()==R.id.bCancelar){
        //realizar operacoes ao ser clicado no botao cancelar
       // btCancelar.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
                TextView txtnome = (TextView) findViewById(R.id.enome);
                TextView txtsobrenome = (TextView) findViewById(R.id.esobrenome);
                TextView txtemail = (TextView) findViewById(R.id.eemail);
                TextView txtconfirmarSenha = (TextView) findViewById(R.id.econfirmarsenha);
                TextView txtsenha = (TextView) findViewById(R.id.esenha);
                txtnome.setText("");
                txtemail.setText("");
                txtconfirmarSenha.setText("");
                txtsenha.setText("");
                txtsobrenome.setText("");
                alert("Cadastrado NÃO concluído!");
            }else{
                alert("ID DO BOTAO DIFERENTE!");
            }
        //}
               // );
    }

    public void alert(String s){Toast.makeText(this,s,Toast.LENGTH_LONG).show();}
}
