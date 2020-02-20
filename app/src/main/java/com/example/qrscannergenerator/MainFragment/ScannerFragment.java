package com.example.qrscannergenerator.MainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.R;

public class ScannerFragment extends Fragment {

    View v;
    Button btn_share;
    boolean check = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scanner, container, false);

        btn_share = (Button) v.findViewById(R.id.btn_share);

        if (!check){
            btn_share.setBackgroundResource(R.drawable.bg_black);
            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),AlertDialog.THEME_HOLO_DARK);
                    builder.setCancelable(true);
                    builder.setTitle("Alert");
                    builder.setMessage("test");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do something
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        else {
            btn_share.setBackgroundResource(R.drawable.hover_bg3_bg4);
        }

        return v;
    }
}
