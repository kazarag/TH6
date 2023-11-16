package com.example.th6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openLayout(View view) {
        int id = view.getId();
        Intent intent;
        if (id == R.id.button1) {
            intent = new Intent(this, Cau1Activity.class);
            startActivity(intent);

        } else if (id == R.id.button2) {
            intent = new Intent(this, Cau2Activity.class);
            startActivity(intent);
        }


    }
}