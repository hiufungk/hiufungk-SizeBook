package com.example.hiufungk_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private static final String FILENAME = "file.sav";

    private ListView oldInfoList;
    private ArrayList<PersonInfo> infoArrayList = new ArrayList<PersonInfo>();

    private ArrayAdapter<PersonInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldInfoList = (ListView) findViewById(R.id.InfoList);
        registerForContextMenu(oldInfoList);
        try {
            PersonInfo p1 = new PersonInfo("Pen");
            PersonInfo p2 = new PersonInfo("Pineapple");

            p2.setBust("20");

            PersonInfo p3 = new PersonInfo("Apple");
            PersonInfo p4 = new PersonInfo("Pen");
            p3.setInseam("12.345");
            p3.setWaist("2.5");
            p3.setBust("9.75");
            p3.setHip("11.12");
            infoArrayList.add(p1);
            infoArrayList.add(p2);
            infoArrayList.add(p3);
            infoArrayList.add(p4);

        } catch (InputNumberException e) {
            e.printStackTrace();
        }


    }

    //http://www.compiletimeerror.com/2013/09/context-menu-in-android-with-example.html#.WJJ2eVMrKpo
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
        menu.add(0, v.getId(), 0, "Action 3");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Action 1") {
            Toast.makeText(this, "Action 1 invoked", Toast.LENGTH_SHORT).show();
            //Log.d("myTag", "selected: "+ item.toString());
        } else if (item.getTitle() == "Action 2") {
            Toast.makeText(this, "Action 2 invoked", Toast.LENGTH_SHORT).show();
        } else if (item.getTitle() == "Action 3") {
            Toast.makeText(this, "Action 3 invoked", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return true;
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //loadFromFile();

        adapter = new ArrayAdapter<PersonInfo>(this, R.layout.list_item, infoArrayList); //view,dataArray
        oldInfoList.setAdapter(adapter);
    }

    /** Called when the user clicks the ADD button */
    public void addEntry(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AddDetail.class);
        startActivity(intent);
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput("file.sav");
            Log.d("myTag",fis.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Log.d("myTag",in.toString());
            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<PersonInfo>>(){}.getType();
            Log.d("myTag",listType.toString());
            infoArrayList = gson.fromJson(in, listType);
            Log.d("myTag","wtf");

        } catch (FileNotFoundException e) {
            infoArrayList = new ArrayList<PersonInfo>();
        }
    }

}
