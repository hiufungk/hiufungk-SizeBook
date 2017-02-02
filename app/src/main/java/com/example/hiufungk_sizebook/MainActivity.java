package com.example.hiufungk_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private static final String FILENAME = "file.sav";

    private ListView oldInfoList;
    private ArrayList<PersonInfo> infoArrayList;

    private ArrayAdapter<PersonInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldInfoList = (ListView) findViewById(R.id.InfoList);
        registerForContextMenu(oldInfoList);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    //https://www.youtube.com/watch?v=Pq9YQl0nfEk
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.delete_id:
                infoArrayList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile();
                //update count
                TextView countText = (TextView) findViewById(R.id.info_count);
                Integer size = infoArrayList.size();
                countText.setText(String.format ("%d", size));
                return true;
            case R.id.edit_id:
                //http://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                Log.d("myTag","edit case");
                Intent intent = new Intent(this, AddDetail.class);

                /*
                Bundle bundle = new Bundle();
                PersonInfo selected = infoArrayList.get(info.position);
                Log.d("myTag","selected: "+selected.getName());

                bundle.putInt("current_pos",info.position);
                bundle.putString("current_name",selected.getName());
                bundle.putString("current_date",selected.getDate());
                bundle.putString("current_neck",String.valueOf(selected.getNeck()));
                bundle.putString("current_bust",String.valueOf(selected.getBust()));
                bundle.putString("current_chest",String.valueOf(selected.getChest()));
                bundle.putString("current_waist",String.valueOf(selected.getWaist()));
                bundle.putString("current_hip",String.valueOf(selected.getHip()));
                bundle.putString("current_inseam",String.valueOf(selected.getInseam()));
                bundle.putString("current_comment",selected.getComment());
                */

                PersonInfo selected = infoArrayList.get(info.position);
                Bundle bundle = selected.toBundle();
                bundle.putInt("current_pos",info.position);

                intent.putExtras(bundle);
                startActivity(intent);
            default:
                return super.onContextItemSelected(item);
        }
        //return super.onContextItemSelected(item);
    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        //Log.d("myTag","before:"+adapter.toString());
        TextView countText = (TextView) findViewById(R.id.info_count);
        Integer size = infoArrayList.size();
        countText.setText(String.format ("%d", size));
        adapter = new ArrayAdapter<PersonInfo>(this, R.layout.list_item, infoArrayList); //view,dataArray
        //Log.d("myTag",adapter.toString());
        //Log.d("myTag",oldInfoList.toString());
        oldInfoList.setAdapter(adapter);
    }

    /** Called when the user clicks the ADD button */
    public void addEntry(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AddDetail.class);
        startActivity(intent);
    }

    public void clearAll(View view) {
        infoArrayList.clear();
        adapter = new ArrayAdapter<PersonInfo>(this, R.layout.list_item, infoArrayList);
        oldInfoList.setAdapter(adapter);
        TextView countText = (TextView) findViewById(R.id.info_count);
        Integer size = infoArrayList.size();
        countText.setText(String.format ("%d", size));
        saveInFile();
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput("file.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<PersonInfo>>(){}.getType();

            infoArrayList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            Log.d("myTag","FileNotFoundException here");
            infoArrayList = new ArrayList<PersonInfo>();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput("file.sav", Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(infoArrayList, out);
            out.flush();
            Log.d("myTag","saveInFile Done");
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
