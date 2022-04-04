package com.example.miqueiascordeiro.intelligentbank;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class apresentacaoApp extends AppCompatActivity {
    private static int TEMPO_DURACAO_TELA = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao_app);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Homeintent = new Intent(apresentacaoApp.this,MainActivity.class);
                startActivity(Homeintent);
                finish();
            }
        },TEMPO_DURACAO_TELA);
    }
}
