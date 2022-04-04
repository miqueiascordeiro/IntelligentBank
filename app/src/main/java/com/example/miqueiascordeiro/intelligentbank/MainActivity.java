package com.example.miqueiascordeiro.intelligentbank;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AutoText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{

    DatabaseIntelligent database = new DatabaseIntelligent(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btLogin = (Button) findViewById(R.id.blogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tlogin = (TextView) findViewById(R.id.eUsuario);
                TextView tsenha = (TextView) findViewById(R.id.eSenha);
                String Login = tlogin.getText().toString();
                String Senha = tsenha.getText().toString();
                if(database.ConsultaExistenciaUsuario() == true){
                    alert("Usuario não identificado, favor cadastre-se agora!");
                }else {
                    if ((!Login.equals("")) && (!Senha.equals(""))) {
                        if (database.AutenticaUsuario(Login, Senha) == true) {
                            alert("Login realizado com sucesso!");
                            Intent intent = new Intent(MainActivity.this, telaprincipal.class);
                            startActivity(intent);

                        } else {
                            alert("Error. Verifique se os dados estão corretos!");
                        }
                    } else {
                        alert("Necessário preenchimento dos dados!");
                    }
                }



                /*
                if (Login.equals("m") && Senha.equals("1")) {
                    alert("Login realizado com sucesso!");
                    Intent intent = new Intent(MainActivity.this,telaprincipal.class);
                    startActivity(intent);
                    intent.putExtra("Logado por: ",Login);
                } else {
                    alert("Error. Verifique se os dados estão corretos!");
                }*/
            }
        });



        Button btcadastro = (Button) findViewById(R.id.bcadastro);
        btcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent(MainActivity.this,cadastro.class);
                startActivity(intentCadastro);
            }
        });
    }
    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
 }

