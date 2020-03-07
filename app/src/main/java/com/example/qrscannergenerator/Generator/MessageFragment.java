package com.example.qrscannergenerator.Generator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.GeneratorActivity;
import com.example.qrscannergenerator.R;

public class MessageFragment extends Fragment {

    View v;
    EditText number, textMessage;
    Button btn_generate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_message, container, false);
        getActivity().setTitle("Message");

        number = (EditText) v.findViewById(R.id.number);
        textMessage = (EditText) v.findViewById(R.id.textMessage);
        btn_generate = (Button) v.findViewById(R.id.btn_generate);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder();
                if (!number.getText().toString().equals("")) {
                    s.append("Number : " + number.getText().toString().trim() + "\n" + "\n");
                }
                if (!textMessage.getText().toString().equals("")) {
                    s.append("Message : " + textMessage.getText().toString().trim() + "\n" + "\n");
                }

                if (s.toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter information", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), GeneratorActivity.class);
                    intent.putExtra("Value", s.toString());
                    startActivity(intent);
                }
            }
        });

        return v;
    }
}
