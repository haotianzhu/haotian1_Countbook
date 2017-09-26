package com.example.haotianzhu.countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class addActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private static Count count;
    private String name;
    private Integer value;
    private String text;
    private EditText addName;
    private EditText addValue;
    private EditText addText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addName = (EditText) findViewById(R.id.addName);
        addValue = (EditText) findViewById(R.id.addValue);
        addText = (EditText) findViewById(R.id.addText);
        Button addButton = (Button) findViewById(R.id.addCount);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent resultIntent = new Intent();
                try {
                    name = addName.getText().toString().replaceAll("^\\s+", "");
                }catch (Exception e) {
                    name = "";
                }
                try {
                    value = Integer.parseInt(addValue.getText().toString());
                }catch (Exception e) {
                    value = -1;
                }
                text = addText.getText().toString();

                count = new Count(name, value, text);
                Bundle result = new Bundle();
                result.putSerializable("count_here",count);
                resultIntent.putExtra("result",result);
                if (name ==""||value == -1 ){
                    setResult(RESULT_CANCELED,resultIntent);
                }else{
                    setResult(RESULT_OK,resultIntent);

                }

                finish();

            }
        });

    }

}
