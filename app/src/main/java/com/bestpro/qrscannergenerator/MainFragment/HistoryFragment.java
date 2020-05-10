package com.bestpro.qrscannergenerator.MainFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bestpro.qrscannergenerator.Adapter.SectionsPageAdapter;
import com.bestpro.qrscannergenerator.BuildConfig;
import com.bestpro.qrscannergenerator.R;
import com.google.android.material.tabs.TabLayout;

public class HistoryFragment extends Fragment {

    private View v;

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_history, container, false);
        getActivity().setTitle("History");


        Toast.makeText(getContext(), "aaaaaaa", Toast.LENGTH_SHORT).show();

        mSectionsPageAdapter = new SectionsPageAdapter(getFragmentManager());

        mViewPager = (ViewPager) v.findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getFragmentManager());
        adapter.addFragment(new TabGenerator(), "Generator");
        adapter.addFragment(new TabScanner(), "Scanner");
        viewPager.setAdapter(adapter);
    }
}
