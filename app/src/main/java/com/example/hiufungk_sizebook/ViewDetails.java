package com.example.hiufungk_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewDetails extends AppCompatActivity {
    private TextView nameDisplay;
    private TextView dateDisplay;
    /*
    TextView neckDisplay = (TextView) findViewById(R.id.neckDisplay);
    TextView bustDisplay = (TextView) findViewById(R.id.nameDisplay);
    TextView chestDisplay = (TextView) findViewById(R.id.nameDisplay);
    TextView waistDisplay = (TextView) findViewById(R.id.nameDisplay);
    TextView hipDisplay = (TextView) findViewById(R.id.nameDisplay);
    TextView inseamDisplay = (TextView) findViewById(R.id.nameDisplay);
    TextView commentDisplay = (TextView) findViewById(R.id.nameDisplay);
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        nameDisplay = (TextView) findViewById(R.id.nameDisplay);
        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
    }

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
            dateDisplay.setText(date_string);
            /*
            commentText.setText(comment_string);


            if (Double.parseDouble(bundle.getString("current_neck")) > 0) {
                neckText.setText(bundle.getString("current_neck"));
            }

            if (Double.parseDouble(bundle.getString("current_bust")) > 0) {
                bustText.setText(bundle.getString("current_bust"));
            }

            if (Double.parseDouble(bundle.getString("current_chest")) > 0) {
                chestText.setText(bundle.getString("current_chest"));
            }

            if (Double.parseDouble(bundle.getString("current_waist")) > 0) {
                waistText.setText(bundle.getString("current_waist"));
            }

            if (Double.parseDouble(bundle.getString("current_hip")) > 0) {
                hipText.setText(bundle.getString("current_hip"));
            }

            if (Double.parseDouble(bundle.getString("current_inseam")) > 0) {
                inseamText.setText(bundle.getString("current_inseam"));
            }
            */
        }

    }
}
