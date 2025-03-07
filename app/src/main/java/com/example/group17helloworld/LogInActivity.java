package com.example.group17helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogInActivity extends AppCompatActivity {
    private static DBHandler database;
    public static final String KEY_VALUE = "LoginInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast toast = Toast.makeText(getApplicationContext(), "LogInActivity onCreate Called", Toast.LENGTH_LONG);
        toast.show();
    }

    public void submitLogIn(View view)
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
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra(KEY_VALUE, username);
            startActivity(intent);
        }
        else
        {
            TextView target = findViewById(R.id.loginPageText);
            target.setText("Username or password incorrect. Try again.");
        }

    }
}

