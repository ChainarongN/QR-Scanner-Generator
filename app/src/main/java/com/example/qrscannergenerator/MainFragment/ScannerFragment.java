package com.example.qrscannergenerator.MainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.R;
import com.example.qrscannergenerator.ScanCodeActivity;

public class ScannerFragment extends Fragment {

    View v;
    Button btn_share, btn_copy, btn_scanNew;

    public static TextView result_qr;
    private boolean check;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_scanner, container, false);
        result_qr = (TextView) v.findViewById(R.id.result_qr);
        btn_scanNew = (Button) v.findViewById(R.id.btn_scanNew);

        scanNew_btn();
        check_btn();

        return v;
    }


    private void check_btn() {
        btn_share = (Button) v.findViewById(R.id.btn_share);
        btn_copy = (Button) v.findViewById(R.id.btn_copy);

        if (check == false) {
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
        }
    }

    private void scanNew_btn() {

        btn_scanNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), ScanCodeActivity.class));

                result_qr.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        check = true;
                        btn_share.setBackgroundResource(R.drawable.hover_bg3_bg2);
                        btn_copy.setBackgroundResource(R.drawable.hover_bg5_bg2);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });
    }
}
