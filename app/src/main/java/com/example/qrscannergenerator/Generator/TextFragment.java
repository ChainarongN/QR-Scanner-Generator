package com.example.qrscannergenerator.Generator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.GeneratorActivity;
import com.example.qrscannergenerator.R;

public class TextFragment extends Fragment {

    View v;
    EditText text;
    Button btn_generate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_text, container, false);
        getActivity().setTitle("Text / Website");

        text = (EditText) v.findViewById(R.id.text);
        btn_generate = (Button) v.findViewById(R.id.btn_generate);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder();
                if (!text.getText().toString().equals("")) {
                    s.append("Text : " + text.getText().toString().trim() + "\n" + "\n");
                }

//                Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), GeneratorActivity.class);
                intent.putExtra("Value", s.toString());
                startActivity(intent);
            }
        });

        return v;
    }
}
