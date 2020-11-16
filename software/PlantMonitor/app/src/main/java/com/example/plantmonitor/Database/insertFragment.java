package com.example.plantmonitor.Database;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.plantmonitor.R;

public class insertFragment extends DialogFragment {

    //declaration of objects
    protected EditText nameEditText;
    protected EditText growthEditText;
    protected EditText expoEditText;
    protected EditText tempEditText;
    protected EditText intervalEditText;
    protected Button saveButton;
    protected Button cancelButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment, container, false);


        //initializing objects
        nameEditText = view.findViewById(R.id.nameEditText);
        growthEditText = view.findViewById(R.id.growthEditText);
        expoEditText = view.findViewById(R.id.expoEditText);
        tempEditText = view.findViewById(R.id.tempEditText);
        intervalEditText = view.findViewById(R.id.intervalEditText);
        saveButton = view.findViewById(R.id.saveButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        //saving inputted text and loading them unto the ListView
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String growth = growthEditText.getText().toString();
                String expo = expoEditText.getText().toString();
                String temp = tempEditText.getText().toString();
                String interval = intervalEditText.getText().toString();
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

                if(!(name.equals("") || growth.equals("") || expo.equals("") || temp.equals("") || interval.equals(""))) {
                    dbHelper.insertPlant(new PlantProfile(name, interval, expo, temp, growth));
                    //((MainActivity)getActivity()).loadListView();
                    ((ListOfPlants)getActivity()).loadListView();
                    getDialog().dismiss();
                }
            }
        });

        //close fragment
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().dismiss();
            }
        });

        return view;
    }
}
