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

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddDetail extends AppCompatActivity {

    //private ArrayList<PersonInfo> infoList;
    private EditText nameText, dateText, neckText, bustText, chestText, waistText, hipText, inseamText, commentText;
    private PersonInfo personInfo = null;
    private String currentInput = "not set";
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
                /*
                String date = (dateText.getText().toString());
                Log.d("myTag", "date is: "+date);
                if (date.length() != 0) {
                    currentInput = "date ";
                    String[] parts = date.split("-");
                    //yyyy-mm-dd
                    Integer year = Integer.parseInt(parts[0]);
                    Log.d("myTag", "year is: "+year);
                    Integer month = Integer.parseInt(parts[1]);
                    Integer day = Integer.parseInt(parts[2]);
                    if ((1900 < year) && (year < 9999)){
                        Log.d("myTag", "year ok");
                        if ((1 <= month) && (month <= 12)){
                            Log.d("myTag", "month ok");
                            if (month == 2){//feb
                                if ((1 <= day) && (day <= 29)){
                                    Log.d("myTag", "feb ok");
                                    personInfo.setDate(date);
                                }
                            }else if (month % 2 == 0) {//even
                                if ((1 <= day) && (day <= 30)){
                                    Log.d("myTag", "even ok");
                                    personInfo.setDate(date);
                                }
                            }else if (month % 2 == 1){
                                if ((1 <= day) && (day <= 31)){
                                    Log.d("myTag", "odd ok");
                                    personInfo.setDate(date);
                                }
                            }
                        }

                    }

                }
                Log.d("myTag", "date from obj is : "+personInfo.getDate());
                */
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
                saveInFile();
            }

            //infoList.add(personInfo);
        } catch (NullPointerException e) { //idk about this exception lol
            Log.d("myTag", "exception");
            e.printStackTrace();
        } catch (InputNumberException e) { //exception when parse string to double
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Log.d("myTag", currentInput+"exception");
            Toast.makeText(context, "Create Entry fail. "+currentInput+" value must be a positive number", duration).show();
            e.printStackTrace();
    }


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput("file.sav", Context.MODE_APPEND);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(personInfo, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
