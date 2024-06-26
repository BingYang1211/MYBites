package com.example.mybites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginName, loginPassword;
    Button loginButton, signupRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.login_name);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirect = findViewById(R.id.signupRedirect);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateName() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validateName() {
        String val = loginName.getText().toString();
        if (val.isEmpty()) {
            loginName.setError("Name cannot be empty");
            return false;
        }
        else {
            loginName.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        }
        else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userName = loginName.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    loginName.setError(null);
                    String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userPassword)){
                        loginName.setError(null);

                        String nameFromDB = snapshot.child(userName).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);
                        String addressFromDB = snapshot.child(userName).child("address").getValue(String.class);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);
                        intent.putExtra("address", addressFromDB);

                        startActivity(intent);
                    }

                    else {
                        loginPassword.setError("Invalid Credentials");
                        loginPassword.requestFocus();
                    }
                }

                else {
                    loginName.setError("User does not exits");
                    loginName.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}