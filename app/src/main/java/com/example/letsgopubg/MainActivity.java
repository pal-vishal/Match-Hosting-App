package com.example.letsgopubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.letsgopubg.Authentication.LoginActivity;
import com.example.letsgopubg.Authentication.RegisterActivity;
import com.example.letsgopubg.Database.DatabaseHelper;
import com.example.letsgopubg.Models.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Utils utils;

    private Button btn;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        utils = new Utils(this);
        User user = utils.isUserLoggedIn();

        if (null != user) {
            Toast.makeText(this, "User logged in", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        /**  btn = (Button) findViewById(R.id.button2);
         btn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        utils.signOutUser();
        }
        });
         **/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.closed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
