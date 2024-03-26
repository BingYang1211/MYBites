package com.example.mybites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mybites.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editEmail, editPassword, editAddress;
    Button saveButton;
    String nameUser, emailUser, passwordUser, addressUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editAddress = findViewById(R.id.editAddress);
        saveButton = findViewById(R.id.saveButton);

        showData();

        ImageView goBackButton = findViewById(R.id.go_back_home);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Finish the current activity when the back button is clicked
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAddressChanged() || isPasswordChanged() || isEmailChanged()) {
                    Toast.makeText(EditProfileActivity.this, "Saved, Back to Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditProfileActivity.this, "No changes found, Back to Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    public boolean isAddressChanged() {
        if (!addressUser.equals(editAddress.getText().toString())) {
            reference.child(nameUser).child("address").setValue(editName.getText().toString());
            addressUser = editAddress.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isEmailChanged() {
        if (!addressUser.equals(editEmail.getText().toString())) {
            reference.child(nameUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public boolean isPasswordChanged() {
        if (!passwordUser.equals(editPassword.getText().toString())) {
            reference.child(nameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else{
            return false;
        }
    }

    public void showData() {
        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        passwordUser = intent.getStringExtra("password");
        addressUser = intent.getStringExtra("address");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editPassword.setText(passwordUser);
        editAddress.setText(addressUser);
    }
}
