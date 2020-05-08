package com.bestpro.qrscannergenerator.MainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bestpro.qrscannergenerator.BuildConfig;
import com.bestpro.qrscannergenerator.R;

import org.w3c.dom.Text;

public class RateUsFragment extends Fragment {

    private View v;
    final String appPackageName = BuildConfig.APPLICATION_ID;
    TextView permission, version, rate_us, share_url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_rate_us, container, false);
        getActivity().setTitle("Rate us");
        permission = (TextView) v.findViewById(R.id.permission);
        version = (TextView) v.findViewById(R.id.version);
        rate_us = (TextView) v.findViewById(R.id.rate_us);
        share_url = (TextView) v.findViewById(R.id.share_url);
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

        share_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.bestpro.qrscannergenerator");
                startActivity(Intent.createChooser(sharingIntent, "Share"));
            }
        });

        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        return v;
    }
}