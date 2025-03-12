package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUpActivity extends AppCompatActivity {
    private static DBHandler database;
    public static final String KEY_VALUE = "LoginInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void submitSignUp(View view) throws Exception
    {
        Context context = getApplicationContext();
        database = new DBHandler(context);
        EditText usernameInput = findViewById(R.id.usernameLogIn);
        EditText passwordInput = findViewById(R.id.passwordLogIn);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        User user = new User(username,password);
        boolean doesUserExist = database.isLoginValid(user);
        if (doesUserExist)
        {
            TextView target = findViewById(R.id.signUpPageText);
            target.setText("Username or password already exists! Try again.");

        }
        else
        {
            database.addUser(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
}