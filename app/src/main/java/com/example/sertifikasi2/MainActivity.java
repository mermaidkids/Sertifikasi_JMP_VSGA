package com.example.sertifikasi2;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import java.io.File;


public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbarma);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dashboard");

        Button inputButton =findViewById(R.id.btnInputData);
        Button lihatButton =findViewById(R.id.btnLihatData);
        Button infoButton =findViewById(R.id.btnInformasi);


        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent input =new Intent(MainActivity.this, InputActivity.class);
                startActivity(input);
            }
        });

        lihatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lihat = new Intent(MainActivity.this, LihatActivity.class);
                startActivity(lihat);
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent info = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(info);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            tampilkanDialogKonfirmasiLogout();
            return true; // Mengembalikan true karena event sudah ditangani
        }
        return super.onOptionsItemSelected(item);
    }


    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this).setTitle("Logout").setMessage("Apakah Anda yakin ingin Logout?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                hapusFile();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton(android.R.string.no, null).show();
    }


}