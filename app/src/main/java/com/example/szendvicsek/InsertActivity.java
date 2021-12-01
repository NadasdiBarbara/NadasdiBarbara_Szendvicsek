package com.example.szendvicsek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText editNev,editLeiras,editPerc,editArInsert;
    private Button btnFelvetel, btnVissza;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nev = editNev.getText().toString().trim();
                String leiras = editLeiras.getText().toString().trim();
                String perc = editPerc.getText().toString().trim();
                String ar = editArInsert.getText().toString().trim();
                if (nev.isEmpty() || leiras.isEmpty()){
                    Toast.makeText(getApplicationContext(), "MINDEN MEZŐ KITÖLTÉSE KÖTELEZŐ", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        int percint = Integer.parseInt(perc);
                        int arint = Integer.parseInt(ar);
                        if (percint <1 || arint <1){
                            Toast.makeText(getApplicationContext(),"NEM LEHET 1-NÉL KISEBB", Toast.LENGTH_SHORT).show();
                        }else {
                            if (dbHelper.rogzit(nev,leiras,percint, arint)){
                                Toast.makeText(getApplicationContext(), "SIKERES RÖGZÍTÉS",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "SKERTELEN RÖGZÍTÉS",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    }catch (NumberFormatException ex){
                        Toast.makeText(getApplicationContext(), "SZÁMNAK KELL LENNIE",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init(){
        editNev = findViewById(R.id.editNev);
        editLeiras = findViewById(R.id.editLeiras);
        editPerc = findViewById(R.id.editPerc);
        editArInsert = findViewById(R.id.editArInsert);
        btnFelvetel = findViewById(R.id.btnFelvetel);
        btnVissza = findViewById(R.id.btnVissza);
        dbHelper = new DBHelper(this);
    }
}