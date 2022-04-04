package com.example.miqueiascordeiro.intelligentbank;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class relatorio extends AppCompatActivity {
    DatabaseIntelligent MyDB;
    ArrayList<deposito> DepList;
    ListView listView;
    deposito deposito;
    saldo saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        MyDB = new DatabaseIntelligent(this);
        DepList = new ArrayList<>();

        Cursor data = MyDB.getListContents();
        int numRows = data.getCount();
        if(numRows==0){
            Toast.makeText(this,"Banco de dados vazio!",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                deposito = new deposito(data.getString(0),data.getString(1),data.getString(2),data.getString(3));
                DepList.add(i,deposito);
                //System.out.println(data.getString(0)+" "+data.getString(1)+" "+data.getString(2)+" "+data.getString(3));
                //System.out.println(DepList.get(i).getIdUsuario());
                i++;
            }
            ListViewAdapter adapter = new ListViewAdapter(this,R.layout.list_adapter_view,DepList);
            TextView SaldoUser = (TextView) findViewById(R.id.tValorRelatorio);
            String ValorSaldo =  String.valueOf(MyDB.ResgateSaldo(DepList));
            SaldoUser.setText(ValorSaldo);
            listView = (ListView) findViewById(R.id.listViewRelatorio);
            listView.setAdapter(adapter);
        }
    }
}
