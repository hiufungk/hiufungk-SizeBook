package com.example.hiufungk_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddDetail extends AppCompatActivity {

    private EditText nameText, dateText, neckText, bustText, chestText, waistText, hipText, inseamText, commentText;
    private PersonInfo personInfo = null;
    private String currentInput = "not set";
    private ArrayList<PersonInfo> infoArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);
        nameText = (EditText) findViewById(R.id.inputName);
        dateText = (EditText) findViewById(R.id.inputDate);
        neckText = (EditText) findViewById(R.id.inputNeck);
        bustText = (EditText) findViewById(R.id.inputBust);
        chestText = (EditText) findViewById(R.id.inputChest);
        waistText = (EditText) findViewById(R.id.inputWaist );
        hipText = (EditText) findViewById(R.id.inputHip);
        inseamText = (EditText) findViewById(R.id.inputInseam);
        commentText = (EditText) findViewById(R.id.inputComment);
    }
/*
    protected void onStart(){
        super.onStart();
        //String test = "testing name";
        //nameText.setText(test);
        Log.d("myTag","add detail on start");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            Log.d("myTag",bundle.toString());
            String name_string = bundle.getString("current_name");
            String date_string = bundle.getString("current_date");
            nameText.setText(name_string);
            dateText.setText(date_string);

            String neck_string = bundle.getString("current_neck");
            Log.d("mytag","edit neck:" neck_string);

            //bustText.setText(bust_string);
        }


    }
*/
    public void submit(View view) {

        try {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_LONG;

            String name = nameText.getText().toString();

            if (name.length() == 0){ //no name
                Log.d("myTag", "empty string");
                Toast.makeText(context, "Create Entry fail. Must input name", duration).show();
            }else {
                personInfo = new PersonInfo(name);

                currentInput = "date ";
                personInfo.setDate(dateText.getText().toString());

                currentInput = "Neck";
                personInfo.setNeck(neckText.getText().toString());

                currentInput = "Bust";
                personInfo.setBust(bustText.getText().toString());

                currentInput = "Chest";
                personInfo.setChest(chestText.getText().toString());

                currentInput = "Waist";
                personInfo.setWaist(waistText.getText().toString());

                currentInput = "Hip";
                personInfo.setHip(hipText.getText().toString());

                currentInput = "Inseam";
                personInfo.setInseam(inseamText.getText().toString());

                String comment = commentText.getText().toString();
                personInfo.setComment(comment);

                Log.d("myTag", "name is: "+name);
                Log.d("myTag", "name from obj is: "+personInfo.getName());
                Log.d("myTag", "create obj success");
                Toast.makeText(context, "Create Entry success", duration).show();

                loadFromFile();
                infoArrayList.add(personInfo);
                saveInFile();
            }


        } catch (NullPointerException e) { //idk about this exception lol
            Log.d("myTag", "exception");
            e.printStackTrace();
        } catch (InputNumberException e) { //exception when parse string to double
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Log.d("myTag", currentInput+"exception");
            Toast.makeText(context, "Create Entry fail. "+currentInput+" value must be a positive number", duration).show();
            e.printStackTrace();
        }  catch (InputDateException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Log.d("myTag", "date exception");
            Toast.makeText(context, "Create Entry fail. Date is not valid", duration).show();
            e.printStackTrace();
        }
        finish();
    }

    public void delete(View view) {

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

    //store info from file to infoArrayList
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
            infoArrayList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            Log.d("myTag","FileNotFoundException here");
            infoArrayList = new ArrayList<PersonInfo>();
        }
    }

}
