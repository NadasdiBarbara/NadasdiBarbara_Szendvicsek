package com.example.szendvicsek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {
    private TextView txtViewOsszes;
    private Button btnVisszaMain;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        init();
        SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        String arak = sharedPreferences.getString("ar", "0");
        Cursor cursor = dbHelper.cursor(arak);
        if (cursor.getCount()==0){
            txtViewOsszes.setText("NINCS ILYEN OLCSÓ SZENDVICS!");
        }else{
            StringBuilder stringBuilder = new StringBuilder();
            while (cursor.moveToNext()){
                stringBuilder.append(cursor.getInt(0));
                stringBuilder.append(System.lineSeparator());
            }
            txtViewOsszes.setText(stringBuilder.toString());
        }
        //ÖSSZEOMLIK :( :( :( :(

       btnVisszaMain.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SearchResultActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       });
    }
    private void init(){
        txtViewOsszes = findViewById(R.id.txtViewOsszes);
        btnVisszaMain = findViewById(R.id.btnVisszaMain);
        dbHelper = new DBHelper(this);
    }
}