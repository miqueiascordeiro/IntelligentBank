package com.example.miqueiascordeiro.intelligentbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class telaprincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telaprincipal);
        Button buttonDep = (Button) findViewById(R.id.bDeposito);
        Button buttonSaq = (Button) findViewById(R.id.bSaque);
        Button buttonRel = (Button) findViewById(R.id.bRelatorio);
        Button buttonSair = (Button) findViewById(R.id.bSair);



        //chama tela do deposito ao clicar no botao
        buttonDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),deposito.class);
                startActivity(intent);
            }
        });

        //chama tela do saque ao clicar no botao
        buttonSaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSaq = new Intent(getBaseContext(),saque.class);
                startActivity(intentSaq);
            }
        });
        //chama tela do relatorio ao clicar no botao
        buttonRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentExt = new Intent(getBaseContext(),relatorio.class);
                startActivity(intentExt);
            }
        });
        //chama tela do sair ao clicar no botao
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSair = new Intent(getBaseContext(),MainActivity.class);
                startActivity(intentSair);
            }
        });


    }



    //Emite mensagem de alerta na tela
    private void alert(String alerta){
        Toast.makeText(this, alerta, Toast.LENGTH_LONG).show();
    }
}
