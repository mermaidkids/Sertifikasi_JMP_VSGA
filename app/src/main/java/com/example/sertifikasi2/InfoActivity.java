package com.example.sertifikasi2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbarfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informasi");

    }
}
