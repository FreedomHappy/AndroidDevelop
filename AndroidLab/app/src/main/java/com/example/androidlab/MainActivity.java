package com.example.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Lab1 HelloWorld && ActivityLifecycle
    public  void ToHelloWorld(View view){
        Intent intent = new Intent(this, HelloWorld.class);
        startActivity(intent);
    }
    public void ToActivityLifecycle(View view){
        Intent intent = new Intent(this, ActivityLifecycle.class);
        startActivity(intent);
    }
    // Lab2 layout 布局实验
    public void ToLinearActiviy(View view){
        Intent intent = new Intent(this, LinearActivity.class);
        startActivity(intent);
    }
    public void ToConstraintActiviy(View view){
        Intent intent = new Intent(this, ConstraintActivity.class);
        startActivity(intent);
    }
    public void ToTableActiviy(View view){
        Intent intent = new Intent(this, TableActivity.class);
        startActivity(intent);
    }
}
