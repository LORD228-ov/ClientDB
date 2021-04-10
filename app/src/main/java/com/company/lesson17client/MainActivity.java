package com.company.lesson17client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Button btnSignOn,btnSignIn;
    private EditText etLogin,etPassword;

    private Socket socket;
    private PrintWriter printWriter;
    private Scanner scanner;

    private Gson gson;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignOn = findViewById(R.id.btnSignOn);


        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);

        gson = new Gson();

        connect();



        btnSignOn.setOnClickListener(v -> register());



        btnSignIn.setOnClickListener(v -> singIn());
    }

    private void connect() {
        new Thread(() -> {
            try {
                socket = new Socket("192.168.1.112",6667);
                printWriter = new PrintWriter(socket.getOutputStream());
                scanner = new Scanner(socket.getInputStream());
                runOnUiThread(() -> Toast.makeText(this, "Ты подключился", Toast.LENGTH_SHORT).show());
            } catch (IOException e) {
                e.printStackTrace();

            }
        }).start();

    }

    private void register() {
        new Thread(() ->{
            String login = etLogin.getText().toString();
            String password = etPassword.getText().toString();
            User user = new User(login,password);
            printWriter.println(RequestCode.KEY_REGISTER_USER);
            String jsonUser = gson.toJson(user);
            printWriter.println(jsonUser);
            printWriter.flush();
            runOnUiThread(() -> Toast.makeText(this, "All okey", Toast.LENGTH_SHORT).show());
        }).start();
    }

    private void singIn(){

        new Thread(() ->{
            String login = etLogin.getText().toString();
            String password = etPassword.getText().toString();
            User user = new User(login,password);
            printWriter.println(RequestCode.KEY_GET_USER);
            String jsonUser = gson.toJson(user);
            printWriter.println(jsonUser);
            printWriter.flush();

            User resultUser = gson.fromJson(scanner.nextLine(),User.class);
            runOnUiThread(() ->{
                intent = new Intent(this,WelcomeActivity.class);
                intent.putExtra("user",resultUser.getLogin());
                startActivity(intent);
            });
        }).start();
    }


}
