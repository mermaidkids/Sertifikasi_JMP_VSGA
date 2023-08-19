package com.example.sertifikasi2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sertifikasi2.helper.DbHelper;

public class DetailActivity extends AppCompatActivity {

    TextView txt_id, txt_name, txt_address, txt_email;
    Button btn_cancel;
    String id, name, address, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbaril);
        setSupportActionBar(toolbar);


        txt_id = (TextView) findViewById(R.id.txt_idil);
        txt_name = (TextView) findViewById(R.id.txtNamail);
        txt_address = (TextView) findViewById(R.id.txtAlamatil);
        txt_email = (TextView) findViewById(R.id.txtEmailil);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(LihatActivity.TAG_ID);
        name = getIntent().getStringExtra(LihatActivity.TAG_NAME);
        address = getIntent().getStringExtra(LihatActivity.TAG_ADDRESS);
        email = getIntent().getStringExtra(LihatActivity.TAG_EMAIL);

        if (id == null || id == "") {
            setTitle("Lihat Data");
        } else {
            setTitle("Lihat Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(address);
            txt_email.setText(email);
        }

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

