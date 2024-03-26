package com.example.mybites.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mybites.EditProfileActivity;
import com.example.mybites.LoginActivity;
import com.example.mybites.R;
import com.example.mybites.SignupActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileEmail, profilePassword, profileAddress;
    private Button editProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        profilePassword = view.findViewById(R.id.profilePassword);
        profileAddress = view.findViewById(R.id.profileAddress);
        editProfile = view.findViewById(R.id.editButton);
        Button logout = view.findViewById(R.id.logoutButton);

        showUserData();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        return view;
    }

    public void showUserData() {
        Intent intent = requireActivity().getIntent(); // Use requireActivity() to get the parent activity
        String nameUser = intent.getStringExtra("name");
        String emailUser = intent.getStringExtra("email");
        String passwordUser = intent.getStringExtra("password");
        String addressUser = intent.getStringExtra("address");

        profileName.setText(nameUser);
        profileEmail.setText(emailUser);
        profilePassword.setText(passwordUser);
        profileAddress.setText(addressUser);
    }

    public void passUserData() {
        String userName = profileName.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String nameFromDB = snapshot.child(userName).child("name").getValue(String.class);
                    String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);
                    String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);
                    String addressFromDB = snapshot.child(userName).child("address").getValue(String.class);

                    Intent intent = new Intent(requireActivity(), EditProfileActivity.class);

                    intent.putExtra("name", nameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("password", passwordFromDB);
                    intent.putExtra("address", addressFromDB);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}