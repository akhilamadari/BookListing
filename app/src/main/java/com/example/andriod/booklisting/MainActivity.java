package com.example.andriod.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        TextView numbers = (TextView) findViewById(R.id.search_button);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                EditText simpleEditText = (EditText) findViewById(R.id.search_box);
                String strValue = simpleEditText.getText().toString().replaceAll(" ", "+");
                if (strValue != null && !strValue.equals("")) {

                    Intent searchIntent = new Intent(MainActivity.this, SearchMenu.class).putExtra("StringName", strValue);
                    ;

                    // Start the new activity
                    startActivity(searchIntent);
                }else Toast.makeText(getBaseContext(),"fill in the text", Toast.LENGTH_LONG).show();
            }
        });


    }



}
