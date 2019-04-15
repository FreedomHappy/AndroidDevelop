package com.example.androidlab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /* Lab1 HelloWorld && ActivityLifecycle */
    public  void ToHelloWorld(View view){
        Intent intent = new Intent(this, HelloWorld.class);
        startActivity(intent);
    }
    public void ToActivityLifecycle(View view){
        Intent intent = new Intent(this, ActivityLifecycle.class);
        startActivity(intent);
    }
    /* Lab2 layout 布局实验 */
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

    /* Lab3 UI */
    // lab3_1 ListView
    public void ToListViewActivity(View view){
        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
    }
    // lab3_2 Alert Dialog
    // references (https://developer.android.com/guide/topics/ui/dialogs)
    public void BuildAlertDialog(View view){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_alert_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    // lab3_3 menu test
    // references (https://developer.android.com/guide/topics/ui/menus#options-menu)
    // (https://www.tutlane.com/tutorial/android/android-options-menu-with-examples)
    // (https://www.javatpoint.com/android-option-menu-example)
    public void ToMenuTest(View view){
        Intent intent = new Intent(this, MenuTest.class);
        startActivity(intent);
    }
    // lab3_4  contextual menu
    // references (https://developer.android.google.cn/guide/topics/ui/menus.html)
    public void ToContextualMenu(View view){
        Intent intent = new Intent(this, ContextualMenuActivity.class);
        startActivity(intent);
    }
    public void ToPreference(View view){
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);
    }
}
