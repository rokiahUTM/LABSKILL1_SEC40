package com.example.labskill1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText newCourseET;
    private ImageButton addCourseImgBtn;
    private ListView courseLV;
    ArrayList <String> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newCourseET = (EditText) findViewById(R.id.newCourseET);
        addCourseImgBtn = (ImageButton) findViewById(R.id.addCourseImgBtn);
        courseLV = (ListView) findViewById(R.id.courseLV);

        courseList.add("C++");
        courseList.add("Operating System");

        ArrayAdapter <String> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,courseList);

        courseLV.setAdapter(adapter);

        addCourseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = newCourseET.getText().toString();
                if (!courseName.isEmpty()){
                    courseList.add(courseName);
                    adapter.notifyDataSetChanged();
                    newCourseET.setText("");
                }
            }
        });

    }
}