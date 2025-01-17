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

public class BusinessFragment extends Fragment {

    View v;
    EditText businessName, businessType, phoneNumber, faxNumber, email, address, website;
    Button btn_generate;

    private AdView mAdView;
    private InterstitialAd interstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_business, container, false);
        getActivity().setTitle("Business");

        mAdView = v.findViewById(R.id.adView);
        MobileAds.initialize(getContext(),"ca-app-pub-8182151086528694~1250153017");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-8182151086528694/6093864704");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        businessName = (EditText) v.findViewById(R.id.b_name);
        businessType = (EditText) v.findViewById(R.id.b_type);
        phoneNumber = (EditText) v.findViewById(R.id.phoneNumber);
        faxNumber = (EditText) v.findViewById(R.id.faxNumber);
        email = (EditText) v.findViewById(R.id.email);
        address = (EditText) v.findViewById(R.id.address);
        website = (EditText) v.findViewById(R.id.website);
        btn_generate = (Button) v.findViewById(R.id.btn_generate);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder();
                if (!businessName.getText().toString().equals("")) {
                    s.append("Business name : " + businessName.getText().toString().trim() + "\n" + "\n");
                }
                if (!businessType.getText().toString().equals("")) {
                    s.append("Business type : " + businessType.getText().toString().trim() + "\n" + "\n");
                }
                if (!phoneNumber.getText().toString().equals("")) {
                    s.append("Phone number : " + phoneNumber.getText().toString().trim() + "\n" + "\n");
                }
                if (!faxNumber.getText().toString().equals("")) {
                    s.append("Fax number : " + faxNumber.getText().toString().trim() + "\n" + "\n");
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
                    Toast.makeText(getContext(),"Please enter information",Toast.LENGTH_SHORT).show();
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
