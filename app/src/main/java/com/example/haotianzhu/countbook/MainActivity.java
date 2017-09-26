package com.example.haotianzhu.countbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

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
    private static final String FILENAME = "file.sav";
    private ListView countsView;
    private ArrayAdapter<Count> adapter;
    private ArrayList<Count> counts;
    private TextView showtotal;
    private int p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.addCount);
        counts = new ArrayList<Count>();
        countsView = (ListView) findViewById(R.id.countsList);
        showtotal = (TextView) findViewById(R.id.mycounts);
        loadFromFile();
        showtotal.setText("MY COUNT: "+Integer.toString(counts.size()));

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent addIntent = new Intent(MainActivity.this,addActivity.class);
                startActivityForResult(addIntent,1);

            }
        });


        countsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                p = position;
                Count chosenCount = (Count) adapter.getItem(position);
                Intent infoIntent = new Intent(MainActivity.this,infoActivity.class);
                Bundle info = new Bundle();
                info.putSerializable("info_here",chosenCount);
                infoIntent.putExtra("info",info);
                startActivityForResult(infoIntent,2);
            }
        });







    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        adapter = new ArrayAdapter<Count>(this, R.layout.list_item,counts);
        countsView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Context context = getApplicationContext();
                CharSequence text = "add a count....";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Count oneCount = (Count) data.getBundleExtra("result").getSerializable("count_here");
                counts.add(oneCount);
                saveInFile();
                showtotal.setText("MY COUNT: " + Integer.toString(counts.size()));
                adapter.notifyDataSetChanged();
            }else if(resultCode == RESULT_CANCELED){
                Context context = getApplicationContext();
                CharSequence text = "user input is error please add again....";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        else if(requestCode == 2){
            if(resultCode == RESULT_OK) {
                Context context = getApplicationContext();
                CharSequence text = "update a count....";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Count updatedCount = (Count) data.getBundleExtra("update").getSerializable("update_here");
                counts.set(p, updatedCount);
                saveInFile();
                adapter.notifyDataSetChanged();
            }else if(resultCode == RESULT_CANCELED){

                Context context = getApplicationContext();
                CharSequence text = "delete a count....";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                counts.remove(p);
                saveInFile();
                showtotal.setText("MY COUNT: "+Integer.toString(counts.size()));
                adapter.notifyDataSetChanged();
            }
        }
    };


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Count>>() {
            }.getType();
            counts = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counts = new ArrayList<Count>();
        }
    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counts, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
    }
    }

}



