package com.eugene.mytestaliasapp.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eugene.mytestaliasapp.R;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class FragmentStart extends Fragment {
    TextView nameApp;
    Typeface myFontAppName;

    Button buttonSettingFragment;
    Button buttonFAQ;

    public FragmentStart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        nameApp = view.findViewById(R.id.nameApp);
        myFontAppName = Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf");
        nameApp.setTypeface(myFontAppName);

        buttonSettingFragment = view.findViewById(R.id.buttonSettingAppFragment);
        buttonSettingFragment.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf"));
        buttonSettingFragment.setText(R.string.Play);
        buttonSettingFragment.setTextSize(23);

        buttonFAQ = view.findViewById(R.id.buttonFAQ);
        buttonFAQ.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf"));
        buttonFAQ.setText(R.string.FAQ);
        buttonFAQ.setTextSize(23);

        clickButton();

        return view;
    }

    private void clickButton() {
        PushDownAnim.setPushDownAnimTo(buttonSettingFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentSettingApp();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        PushDownAnim.setPushDownAnimTo(buttonFAQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentFAQ();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
