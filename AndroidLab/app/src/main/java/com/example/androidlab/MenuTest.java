package com.example.androidlab;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MenuTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_with_xml, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        EditText edt = findViewById(R.id.text_menutest);
        switch (item.getItemId()) {
            case R.id.font_big:
               edt.setTextSize(20);
                return true;
            case R.id.font_median:
                edt.setTextSize(16);
                return true;
            case R.id.font_small:
                edt.setTextSize(10);
                return true;
            case R.id.color_red:
                edt.setTextColor(Color.parseColor("#FF0033"));
                return true;
            case R.id.color_black:
                edt.setTextColor(Color.parseColor("#000000"));
                return true;
            case R.id.item_normal:
                Toast.makeText(getApplicationContext(), "选中普通菜单项", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
