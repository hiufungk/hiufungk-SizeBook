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

        //http://stackoverflow.com/questions/20922036/android-cant-call-setonitemclicklistener-from-a-listview
        //2017-02-02
        //when click an item in list
        oldInfoList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(view.getContext(), ViewDetails.class);
                        PersonInfo selected = infoArrayList.get(position);
                        Bundle bundle = selected.toBundle();

                        intent.putExtras(bundle);
                        startActivity(intent);

                    }


                });
    }

    //http://stackoverflow.com/questions/20922036/android-cant-call-setonitemclicklistener-from-a-listview
    //2017-02-02
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    //https://www.youtube.com/watch?v=Pq9YQl0nfEk
    //2017-02-02
    //edit or delete selected person
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            //store selected person info, store to bundle and pass to add detail activity
            case R.id.edit_id:
                //http://stackoverflow.com/questions/8452526/android-can-i-use-putextra-to-pass-multiple-values
                Log.d("myTag","edit case");
                Intent intent = new Intent(this, AddDetail.class);

                PersonInfo selected = infoArrayList.get(info.position);
                Bundle bundle = selected.toBundle();
                bundle.putInt("current_pos",info.position);

                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            //remove selected person
            case R.id.delete_id:
                infoArrayList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile();
                //update count
                TextView countText = (TextView) findViewById(R.id.info_count);
                Integer size = infoArrayList.size();
                countText.setText(String.format ("%d", size));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
        //return super.onContextItemSelected(item);
    }


    //update count and list view
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
        oldInfoList.setAdapter(adapter);
    }

    /** Called when the user clicks the ADD button */
    public void addEntry(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AddDetail.class);
        startActivity(intent);
    }

    /** Called when the user clicks the clear button, removes all entries*/
    public void clearAll(View view) {
        infoArrayList.clear();
        adapter = new ArrayAdapter<PersonInfo>(this, R.layout.list_item, infoArrayList);
        oldInfoList.setAdapter(adapter);
        TextView countText = (TextView) findViewById(R.id.info_count);
        Integer size = infoArrayList.size();
        countText.setText(String.format ("%d", size));
        saveInFile();
    }

    //load data from file using gson,json
    //if file not found, create new ArrayList<PersonInfo>
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

    //save data to file and load to infoArrayList
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
