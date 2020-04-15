package com.bestpro.qrscannergenerator.MainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bestpro.qrscannergenerator.BuildConfig;
import com.bestpro.qrscannergenerator.R;

public class RateUsFragment extends Fragment {

    private View v;
    TextView permission, version;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_rate_us, container, false);
        getActivity().setTitle("Rate us");
        permission = (TextView) v.findViewById(R.id.permission);
        version = (TextView) v.findViewById(R.id.version);
        version.setText("Version " + String.valueOf(BuildConfig.VERSION_NAME));

        permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Permission");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("1. CAMERA :\n Scan qr code\n2. READ_EXTERNAL_STORAGE :\n Scan qr code from image in your gallery\n" +
                        "3. WRITE_EXTERNAL_STORAGE :\n Save your image qr code\n");
                AlertDialog alert1 = builder.create();
                alert1.setButton(DialogInterface.BUTTON_POSITIVE, "Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something
                    }
                });
                alert1.setCanceledOnTouchOutside(false);
                alert1.show();
            }
        });
        return v;
    }
}
