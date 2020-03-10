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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ContactFragment extends Fragment {

    View v;
    EditText name, nickname, phoneNumber, email, line_id, facebook;
    Button btn_generate;

    private AdView mAdView ,mAdView1;
    private InterstitialAd interstitialAd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_contact, container, false);
        getActivity().setTitle("Contact");

        mAdView = v.findViewById(R.id.adView);
        mAdView1 = v.findViewById(R.id.adView1);
        MobileAds.initialize(getContext(),"ca-app-pub-8182151086528694~1250153017");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView1.loadAd(adRequest);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-8182151086528694/6093864704");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        name = (EditText) v.findViewById(R.id.edt_name);
        nickname = (EditText) v.findViewById(R.id.edt_nickname);
        phoneNumber = (EditText) v.findViewById(R.id.edt_phoneNumber);
        email = (EditText) v.findViewById(R.id.edt_email);
        line_id = (EditText) v.findViewById(R.id.edt_lineID);
        facebook = (EditText) v.findViewById(R.id.edt_facebook);
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
                if (!phoneNumber.getText().toString().equals("")) {
                    s.append("Phone number : " + phoneNumber.getText().toString().trim() + "\n" + "\n");
                }
                if (!email.getText().toString().equals("")) {
                    s.append("Email : " + email.getText().toString().trim() + "\n" + "\n");
                }
                if (!line_id.getText().toString().equals("")) {
                    s.append("Line ID : " + line_id.getText().toString().trim() + "\n" + "\n");
                }
                if (!facebook.getText().toString().equals("")) {
                    s.append("Facebook : " + facebook.getText().toString().trim());
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
