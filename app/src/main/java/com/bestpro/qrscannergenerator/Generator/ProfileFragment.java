package com.bestpro.qrscannergenerator.Generator;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bestpro.qrscannergenerator.GeneratorActivity;
import com.bestpro.qrscannergenerator.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ProfileFragment extends Fragment {

    View v;
    EditText name, nickname, job, phone, email, address, website;
    Button btn_generate;

    private AdView mAdView;
    private InterstitialAd interstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        getActivity().setTitle("Profile");

        mAdView = v.findViewById(R.id.adView);
        MobileAds.initialize(getContext(),"ca-app-pub-8182151086528694~1250153017");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-8182151086528694/6093864704");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        name = (EditText) v.findViewById(R.id.edt_name);
        nickname = (EditText) v.findViewById(R.id.edt_nickname);
        job = (EditText) v.findViewById(R.id.edt_jobTitle);
        phone = (EditText) v.findViewById(R.id.edt_phoneNumber);
        email = (EditText) v.findViewById(R.id.edt_email);
        address = (EditText) v.findViewById(R.id.edt_address);
        website = (EditText) v.findViewById(R.id.edt_website);
        btn_generate = (Button) v.findViewById(R.id.btn_generate);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder();
                if (!name.getText().toString().equals("")) {
                    s.append("Name : " + name.getText().toString().trim() + "\n" + "\n");
                }
                if (!nickname.getText().toString().equals("")) {
                    s.append("Nickname : " + nickname.getText().toString().trim() + "\n" + "\n");
                }
                if (!job.getText().toString().equals("")) {
                    s.append("Job title : " + job.getText().toString().trim() + "\n" + "\n");
                }
                if (!phone.getText().toString().equals("")) {
                    s.append("Phone number : " + phone.getText().toString().trim() + "\n" + "\n");
                }
                if (!email.getText().toString().equals("")) {
                    s.append("Email : " + email.getText().toString().trim() + "\n" + "\n");
                }
                if (!address.getText().toString().equals("")) {
                    s.append("Address : " + address.getText().toString().trim() + "\n" + "\n");
                }
                if (!website.getText().toString().equals("")) {
                    s.append("Website : " + website.getText().toString().trim());
                }

                if (s.toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter information", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getContext(), GeneratorActivity.class);
                    intent.putExtra("Value", s.toString());
                    startActivity(intent);

                    interstitialAd.show();
                }
            }
        });

        return v;
    }

}
