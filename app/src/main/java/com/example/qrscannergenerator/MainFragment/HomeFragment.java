package com.example.qrscannergenerator.MainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.qrscannergenerator.Generator.BusinessFragment;
import com.example.qrscannergenerator.Generator.ContactFragment;
import com.example.qrscannergenerator.Generator.EmailFragment;
import com.example.qrscannergenerator.Generator.MessageFragment;
import com.example.qrscannergenerator.Generator.ProfileFragment;
import com.example.qrscannergenerator.Generator.TextFragment;
import com.example.qrscannergenerator.R;

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

                    if (finalI == 0) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileFragment()).addToBackStack(null).commit();
                    }
                    if (finalI == 1) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new BusinessFragment()).addToBackStack(null).commit();
                    }
                    if (finalI == 2) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new EmailFragment()).addToBackStack(null).commit();
                    }
                    if (finalI == 3) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new MessageFragment()).addToBackStack(null).commit();
                    }
                    if (finalI == 4) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new TextFragment()).addToBackStack(null).commit();
                    }
                    if (finalI == 5) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ContactFragment()).addToBackStack(null).commit();
                    }
                }
            });
        }
    }

}
