package com.example.sertifikasi2;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtEmail, edtAsalSekolah, edtAlamat, edtNamaLengkap;
    Button btnSimpan;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.edtUsernameEr);
        edtPassword = findViewById(R.id.edtPasswordEr);
        edtEmail = findViewById(R.id.edtEmailEr);
        edtNamaLengkap = findViewById(R.id.edtNamaEr);
        edtAsalSekolah = findViewById(R.id.edtSekolahEr);
        btnSimpan = findViewById(R.id.btnSimpanEr);
        btnSimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isValidation()){
                    saveFile();
                } else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi sleuruh data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveFile(){
        String isiFile = edtUsername.getText().toString() + ";" + edtPassword.getText().toString() + ";" + edtEmail.getText().toString() + ";" + edtNamaLengkap.getText().toString() + ";" + edtAsalSekolah.getText().toString();
        File file = new File(getFilesDir(), edtUsername.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream =  new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Register Berhasil",Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private boolean isValidation(){
        if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("") || edtEmail.getText().toString().equals("") || edtNamaLengkap.getText().toString().equals("") || edtAsalSekolah.getText().toString().equals("")){
            return false;
        }else {
            return true;
        }
    }
}