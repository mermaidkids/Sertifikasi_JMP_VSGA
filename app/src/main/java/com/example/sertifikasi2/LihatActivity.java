package com.example.sertifikasi2;




import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.sertifikasi2.adapter.Adapter;
import com.example.sertifikasi2.helper.DbHelper;
import com.example.sertifikasi2.model.Data;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LihatActivity extends AppCompatActivity {

    ListView listview;
    AlertDialog.Builder dialog;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    DbHelper SQLite = new DbHelper(this);

    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_ADDRESS = "address";

    public static final String TAG_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);


        SQLite = new DbHelper(getApplicationContext());


        ListView listView = (ListView) findViewById(R.id.list_view);

        adapter = new Adapter(LihatActivity.this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id){
                final String idx = itemList.get(position).getId();
                final String name = itemList.get(position).getName();
                final String address = itemList.get(position).getAddress();
                final String email = itemList.get(position).getEmail();

                final CharSequence[] dialogitem = {"View", "Edit", "Delete"};
                dialog = new AlertDialog.Builder(LihatActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                        switch(which){
                            case 0:
                                Intent lihat = new Intent(LihatActivity.this, DetailActivity.class);
                                lihat.putExtra(TAG_ID,idx);
                                lihat.putExtra(TAG_NAME, name);
                                lihat.putExtra(TAG_ADDRESS, address);
                                lihat.putExtra(TAG_EMAIL, email);
                                startActivity(lihat);
                                break;
                            case 1:
                                Intent intent = new Intent(LihatActivity.this, EditActivity.class);
                                intent.putExtra(TAG_ID,idx);
                                intent.putExtra(TAG_NAME, name);
                                intent.putExtra(TAG_ADDRESS, address);
                                intent.putExtra(TAG_EMAIL, email);
                                startActivity(intent);
                                break;
                            case 2:
                                SQLite.delete(Integer.parseInt(idx));
                                itemList.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getAllData();
    }

    private void getAllData(){
        ArrayList<HashMap<String, String>> row =  SQLite.getAllData();

        for (int i= 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String name = row.get(i).get(TAG_NAME);
            String title = row.get(i).get(TAG_ADDRESS);
            String gmail = row.get(i).get(TAG_EMAIL);

            Data data = new Data();

            data.setId(id);
            data.setName(name);
            data.setAddress(title);
            data.setEmail(gmail);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
}