package com.example.hiufungk_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewDetails extends AppCompatActivity {
    private TextView nameDisplay;
    private TextView dateDisplay;
    private TextView neckDisplay;
    private TextView bustDisplay;
    private TextView chestDisplay;
    private TextView waistDisplay;
    private TextView hipDisplay;
    private TextView inseamDisplay;
    private TextView commentDisplay;

    //setup TextView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        nameDisplay = (TextView) findViewById(R.id.nameDisplay);
        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        neckDisplay = (TextView) findViewById(R.id.neckDisplay);
        bustDisplay = (TextView) findViewById(R.id.bustDisplay);
        chestDisplay = (TextView) findViewById(R.id.chestDisplay);
        waistDisplay = (TextView) findViewById(R.id.waistDisplay);
        hipDisplay = (TextView) findViewById(R.id.hipDisplay);
        inseamDisplay = (TextView) findViewById(R.id.inseamDisplay);
        commentDisplay = (TextView) findViewById(R.id.commentDisplay);
    }

    //get intent, load all info into corresponding field
    protected void onStart(){
        super.onStart();
        //String test = "testing name";
        //nameText.setText(test);
        Log.d("myTag","view detail on start");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){


            String name_string = bundle.getString("current_name");
            String date_string = bundle.getString("current_date");
            String comment_string = bundle.getString("current_comment");
            nameDisplay.setText(name_string);


            if (date_string != null){
                dateDisplay.setText(date_string);
            }

            if (!comment_string.isEmpty()) {
                Log.d("mytag","comment set :"+comment_string);
                commentDisplay.setText(comment_string);
            }

            if (Double.parseDouble(bundle.getString("current_neck")) > 0) {
                neckDisplay.setText(bundle.getString("current_neck"));
            }

            if (Double.parseDouble(bundle.getString("current_bust")) > 0) {
                bustDisplay.setText(bundle.getString("current_bust"));
            }

            if (Double.parseDouble(bundle.getString("current_chest")) > 0) {
                chestDisplay.setText(bundle.getString("current_chest"));
            }

            if (Double.parseDouble(bundle.getString("current_waist")) > 0) {
                waistDisplay.setText(bundle.getString("current_waist"));
            }

            if (Double.parseDouble(bundle.getString("current_hip")) > 0) {
                hipDisplay.setText(bundle.getString("current_hip"));
            }

            if (Double.parseDouble(bundle.getString("current_inseam")) > 0) {
                inseamDisplay.setText(bundle.getString("current_inseam"));
            }

        }

    }
}
