package com.rlzz.uwinmes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rlzz.uwinmes.R;
import com.rlzz.uwinmes.entity.TestTask;


public class InspectionActivity extends AppCompatActivity {

    private TestTask testTask;

    public static void GoToActivity(Context context, TestTask testTask) {
        Intent intent = new Intent(context, InspectionActivity.class);
        intent.putExtra("testTask", testTask);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection);
        testTask = getIntent().getParcelableExtra("testTask");


    }
}
