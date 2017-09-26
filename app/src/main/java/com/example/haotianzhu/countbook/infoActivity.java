package com.example.haotianzhu.countbook;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;


public class infoActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private static Count thisCount;
    private TextView currentValue;
    private TextView currentDate;
    private EditText updateName;
    private EditText updateValue;
    private EditText updateText;
    private String name;
    private Integer initial;
    private String comment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button increase = (Button) findViewById(R.id.increase);
        Button decrease = (Button) findViewById(R.id.decrease);
        Button delete = (Button) findViewById(R.id.delete);
        Button reset = (Button) findViewById(R.id.reset);
        Button update = (Button) findViewById(R.id.update);
        updateName = (EditText) findViewById(R.id.editName);
        updateValue = (EditText) findViewById(R.id.editValue);
        updateText = (EditText) findViewById(R.id.editText);
        currentValue = (TextView) findViewById(R.id.currentValue);
        currentDate = (TextView) findViewById(R.id.date);

        try{
            thisCount = (Count) getIntent().getBundleExtra("info").getSerializable("info_here");
        }catch (Exception e){
            thisCount = new Count("",0);
        }

        updateName.setText(thisCount.getName());
        updateValue.setHint("Initial Value: "+Integer.toString(thisCount.getInitialValue()));
        updateText.setText(thisCount.getText());
        currentDate.setText("Date: "+thisCount.getDate());
        currentValue.setText("Current Value: "+Integer.toString(thisCount.getCurrentValue()));



        increase.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                thisCount.increaseValue();
                currentValue.setText("Current Value: "+Integer.toString(thisCount.getCurrentValue()));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                thisCount.decreaseValue();
                currentValue.setText("Current Value: "+Integer.toString(thisCount.getCurrentValue()));
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                thisCount.resetValue();
                currentValue.setText("Current Value: "+Integer.toString(thisCount.getCurrentValue()));
            }
        });

        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    name = updateName.getText().toString().replaceAll("^\\s+", "");
                    if (name ==""){
                        name = thisCount.getName();
                    }else {thisCount.updateName(name);}
                }catch (Exception e) {
                    name = thisCount.getName();
                }
                try {
                    initial = Integer.parseInt(updateValue.getText().toString());
                    thisCount.updateValue(initial);
                }catch (Exception e) {
                    initial = thisCount.getInitialValue();
                }
                try{
                    comment = updateText.getText().toString();
                    thisCount.updateComment(comment);
                }catch (Exception e){

                    comment = thisCount.getText();
                }
                Intent updateIntent = new Intent();
                Bundle result = new Bundle();
                result.putSerializable("update_here",thisCount);
                updateIntent.putExtra("update",result);;
                setResult(RESULT_OK,updateIntent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent deleteIntent = new Intent();
                setResult(RESULT_CANCELED,deleteIntent);
                finish();
            }
        });



    }

}
