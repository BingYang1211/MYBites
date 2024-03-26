package com.example.mybites.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mybites.R;

public class AboutUsFragment extends Fragment {

    private final String latitudeOne = "3.1578118318828072";
    private final String longitudeOne = "101.71229469665968";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        ImageButton directionOneBtn = view.findViewById(R.id.directionOneBtn);

        directionOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                directionFromCurrentMap(latitudeOne, longitudeOne);
            }
        });

        return view;
    }

    private void directionFromCurrentMap(String destinationLatitude, String destinationLongitude) {
        Uri mapUri = Uri.parse("https://www.google.com/maps?daddr=" + destinationLatitude + "," + destinationLongitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(intent);
    }
}