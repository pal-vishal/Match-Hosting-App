package com.example.letsgopubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";
    private Utils utils;

    private Button btn;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


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
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setupDrawerContent(navigationView);


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




    private void setupDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);

                        return true;

                    }

                });

    }

    public void selectDrawerItem(MenuItem menuItem) {


        Fragment fragment = null;
        Class fragmentClass;

        switch (menuItem.getItemId()) {

            case R.id.Matches:

                fragmentClass = MatchFragment.class;

                break;

            case R.id.Account:

                fragmentClass = AccountFragment.class;

                break;

            case R.id.HowItWorks:

                fragmentClass = HowItWorksFragment.class;

                break;

            case R.id.SignOut:

                fragmentClass = LogoutFragment.class;

                break;
            default:

                fragmentClass = MatchFragment.class;

        }


        try {

            fragment = (Fragment) fragmentClass.newInstance();

        } catch (Exception e) {

            e.printStackTrace();

        }


        // Insert the fragment by replacing any existing fragment

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();


        // Highlight the selected item has been done by NavigationView

        menuItem.setChecked(true);

        // Set action bar title

        setTitle(menuItem.getTitle());

        // Close the navigation drawer

        drawerLayout.closeDrawers();

    }


}
