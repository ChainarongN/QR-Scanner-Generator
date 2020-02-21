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
    Button btn_share, btn_copy;
    boolean check = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scanner, container, false);

        check_btn();


        return v;
    }


    private void check_btn(){
        btn_share = (Button) v.findViewById(R.id.btn_share);
        btn_copy = (Button) v.findViewById(R.id.btn_copy);

        if (!check) {
            btn_share.setBackgroundResource(R.drawable.bg_black);
            btn_copy.setBackgroundResource(R.drawable.bg_black);

            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_DARK);
                    builder.setCancelable(true);
                    builder.setTitle("Can not share.");
                    builder.setMessage(R.string.alert_btn);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do something
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
            btn_copy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), AlertDialog.THEME_HOLO_DARK);
                    builder.setCancelable(true);
                    builder.setTitle("Can not copy.");
                    builder.setMessage(R.string.alert_btn);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Do something
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        } else {
            btn_share.setBackgroundResource(R.drawable.hover_bg3_bg2);
            btn_copy.setBackgroundResource(R.drawable.hover_bg5_bg2);
        }
    }
}
