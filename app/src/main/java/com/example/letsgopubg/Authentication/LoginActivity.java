package com.example.letsgopubg.Authentication;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.letsgopubg.Database.DatabaseHelper;
import com.example.letsgopubg.MainActivity;
import com.example.letsgopubg.Models.User;
import com.example.letsgopubg.R;
import com.example.letsgopubg.Utils;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText edtTxtEmail, edtTxtPassword;
    private TextView txtWarning, txtRegister;
    private Button btnLogin;

    private DatabaseHelper databaseHelper;

    private LoginUser loginUser;
    private DoesEmailExist doesEmailExist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLogin();
            }
        });
    }

    private void initLogin() {
        Log.d(TAG, "initLogin:started");
        if (!edtTxtEmail.getText().toString().equals("")) {

            if (!edtTxtPassword.getText().toString().equals("")) {
                txtWarning.setVisibility(View.GONE);
                /**
                 *   EXECUTE ASYNC TASK HERE
                 */
                doesEmailExist = new DoesEmailExist();
                doesEmailExist.execute(edtTxtEmail.getText().toString());
            } else {
                txtWarning.setVisibility(View.VISIBLE);
                txtWarning.setText("Please enter your Password");
            }

        } else {
            txtWarning.setVisibility(View.VISIBLE);
            txtWarning.setText("Please enter your Email");
        }
    }

    private void initViews() {
        Log.d(TAG, "initViews: called");
        edtTxtEmail = (EditText) findViewById(R.id.edtTxtEmail);
        edtTxtPassword = (EditText) findViewById(R.id.edtTxtPassword);
        txtWarning = (TextView) findViewById(R.id.txtWarning);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private class DoesEmailExist extends AsyncTask<String, Void, Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            databaseHelper = new DatabaseHelper(LoginActivity.this);


        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Cursor cursor = db.query("users", new String[]{"email"}, "email=?",
                        new String[]{strings[0]}, null, null, null);
                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        cursor.close();
                        db.close();
                        return true;

                    } else {
                        cursor.close();
                        db.close();
                        return false;
                    }

                } else {
                    db.close();
                    return false;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                /**
                 * Executing async task here
                 */
                loginUser = new LoginUser();
                loginUser.execute();
            } else {
                txtWarning.setVisibility(View.VISIBLE);
                txtWarning.setText("User with this email address does not exist");
            }
        }
    }

    private class LoginUser extends AsyncTask<Void, Void, User> {
        private String email;
        private String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.email = edtTxtEmail.getText().toString();
            this.password = edtTxtPassword.getText().toString();
        }


        @Override
        protected User doInBackground(Void... voids) {
            try {
                SQLiteDatabase db = databaseHelper.getReadableDatabase();
                Cursor cursor = db.query("users", null, "email=? AND password=?",
                        new String[]{email, password}, null, null, null);

                if (null != cursor) {
                    if (cursor.moveToFirst()) {
                        User user = new User();
                        user.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                        user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                        user.setFirst_name(cursor.getString(cursor.getColumnIndex("first_name")));
                        user.setLast_name(cursor.getString(cursor.getColumnIndex("last_name")));
                        cursor.close();
                        db.close();
                        return user;


                    } else {
                        cursor.close();
                        db.close();
                        return null;
                    }

                } else {
                    db.close();
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (null != user){
                Utils utils = new Utils(LoginActivity.this);
                utils.addUserToSharedPreferences(user);
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else{
               txtWarning.setVisibility(View.VISIBLE);
               txtWarning.setText("Password is incorrect");
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(null!= doesEmailExist){
            if(!doesEmailExist.isCancelled()){
                doesEmailExist.cancel(true);
            }
        }
        if (null != loginUser) {
            if (!loginUser.isCancelled()) {
                loginUser.cancel(true);
            }
        }
    }
}



