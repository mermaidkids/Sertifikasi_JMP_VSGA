package com.example.sertifikasi2;



import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.example.sertifikasi2.helper.DbHelper;

public class InputActivity extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address, txt_email;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address, email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Toolbar toolbar = findViewById(R.id.toolbarput);
        setSupportActionBar(toolbar);


        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.edtNama);
        txt_address = (EditText) findViewById(R.id.edtAlamat);
        txt_email = (EditText) findViewById(R.id.edtEmail);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(LihatActivity.TAG_ID);
        name = getIntent().getStringExtra(LihatActivity.TAG_NAME);
        address = getIntent().getStringExtra(LihatActivity.TAG_ADDRESS);
        email = getIntent().getStringExtra(LihatActivity.TAG_EMAIL);

        if (id == null || id == ""){
            setTitle("Add Data");
        }else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(address);
            txt_email.setText(email);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (txt_id.getText().toString().equals("")){
                        save();
                    }else {

                    }
                }catch (Exception e){
                    Log.e("Submit", e.toString());
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                blank();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void blank(){
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_address.setText(null);
        txt_email.setText(null);
    }

    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("") ||
                String.valueOf(txt_email.getText()).equals(null) || String.valueOf(txt_email.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_address.getText().toString().trim(), txt_email.getText().toString().trim());
            blank();
            finish();
        }
    }



}
