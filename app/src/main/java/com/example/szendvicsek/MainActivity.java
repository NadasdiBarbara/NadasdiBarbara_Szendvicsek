package com.example.szendvicsek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnKeres,btnUj;
    private EditText editAr;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnUj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnKeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ar = editAr.getText().toString().trim();
                if (ar.equals("")){
                    Toast.makeText(MainActivity.this, "NEM LEHET ÜRES!", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(ar) < 1){
                    Toast.makeText(MainActivity.this, "NEM LEHET EGYNÉL KISEBB!", Toast.LENGTH_SHORT).show();
                }else {
                    editor.putString("ar", ar);
                    Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void init(){
        btnKeres = findViewById(R.id.btnKeres);
        btnUj = findViewById(R.id.btnUj);
        editAr = findViewById(R.id.editAr);
        sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}