package com.example.qrscannergenerator.MainFragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.Generator.BusinessActivity;
import com.example.qrscannergenerator.Generator.ContactActivity;
import com.example.qrscannergenerator.Generator.EmailActivity;
import com.example.qrscannergenerator.Generator.MessageActivity;
import com.example.qrscannergenerator.Generator.ProfileActivity;
import com.example.qrscannergenerator.Generator.TextActivity;
import com.example.qrscannergenerator.R;
import com.example.qrscannergenerator.ScanCodeActivity;

public class HomeFragment extends Fragment {

    private View v;

    private GridLayout gridLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        gridLayout = (GridLayout) v.findViewById(R.id.mainGrid);
        setSingleEvent(gridLayout);

        getActivity().setTitle("Generator");
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(getContext(), R.color.bg_item));
//        }

//        getActivity().getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        return v;
    }

    private void setSingleEvent(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(getContext(), "Clicked at index " + finalI,
//                            Toast.LENGTH_SHORT).show();

                    if (finalI == 0){
                        startActivity(new Intent(getContext(), ProfileActivity.class));
                    }
                    if (finalI == 1){
                        startActivity(new Intent(getContext(), BusinessActivity.class));
                    }
                    if (finalI == 2){
                        startActivity(new Intent(getContext(), EmailActivity.class));
                    }
                    if (finalI == 3){
                        startActivity(new Intent(getContext(), MessageActivity.class));
                    }
                    if (finalI == 4){
                        startActivity(new Intent(getContext(), TextActivity.class));
                    }
                    if (finalI == 5){
                        startActivity(new Intent(getContext(), ContactActivity.class));
                    }
                }
            });
        }
    }

}
