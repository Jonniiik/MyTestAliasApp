package com.eugene.mytestaliasapp.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eugene.mytestaliasapp.R;
import com.github.florent37.androidslidr.Slidr;
import com.manojbhadane.QButton;
import com.rafaelbarbosatec.archivimentview.AchievementView;
import com.thekhaeng.pushdownanim.PushDownAnim;

public class FragmentSettingApp extends Fragment {
    int wordsResult = 50;
    int timeResult = 60;

    public FragmentSettingApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting_app, container, false);

        settingSeekBar(view);

        openFragmentTeams(view);

        achievementView(view);

        return view;
    }

    private void settingSeekBar(View view) {

        Slidr sliderWords = (Slidr) view.findViewById(R.id.sliderWords);
        sliderWords.setMin(10);
        sliderWords.setTextMin("10");
        sliderWords.setMax(100);
        sliderWords.setTextMax("100");
        sliderWords.setCurrentValue(50);
        sliderWords.setTextFormatter(new Slidr.TextFormatter() {
            @Override
            public String format(float value) {
                return String.format("%d " + getActivity().getString(R.string.pieced), (int) value);
            }
        });
        sliderWords.setRegionTextFormatter(new Slidr.RegionTextFormatter() {
            @Override
            public String format(int region, float value) {
                return String.format(" ", region, (int) value);
            }
        });
        sliderWords.setListener(new Slidr.Listener() {
            @Override
            public void valueChanged(Slidr slidr, float currentValue) {
                wordsResult = (int) currentValue;
            }

            @Override
            public void bubbleClicked(Slidr slidr) {

            }
        });


        Slidr sliderTimes = (Slidr) view.findViewById(R.id.sliderTimes);
        sliderTimes.setMin(10);
        sliderTimes.setTextMin("10");
        sliderTimes.setMax(120);
        sliderTimes.setTextMax("120");
        sliderTimes.setCurrentValue(60);
        sliderTimes.setTextFormatter(new Slidr.TextFormatter() {
            @Override
            public String format(float value) {
                return String.format("%d " + getActivity().getString(R.string.seconds), (int) value);
            }
        });

        sliderTimes.setRegionTextFormatter(new Slidr.RegionTextFormatter() {
            @Override
            public String format(int region, float value) {
                return String.format(" ", region, (int) value);
            }
        });
        sliderTimes.setListener(new Slidr.Listener() {
            @Override
            public void valueChanged(Slidr slidr, float currentValue) {
                timeResult = (int) currentValue;
            }

            @Override
            public void bubbleClicked(Slidr slidr) {

            }
        });
    }

    private void openFragmentTeams(View view) {
        QButton buttonTeamsFragment = (QButton) view.findViewById(R.id.buttonTeamsFragment);
        buttonTeamsFragment.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf"));
        buttonTeamsFragment.setText(R.string.Teams);
        buttonTeamsFragment.setTextSize(23);

        PushDownAnim.setPushDownAnimTo(buttonTeamsFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new FragmentTeams();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Bundle bundle = new Bundle();
                bundle.putInt("wordsResult", wordsResult);
                bundle.putInt("timeResult", timeResult);
                fragment.setArguments(bundle);
            }
        });
    }

    private void achievementView(View view) {
        final AchievementView achievementView = (AchievementView) view.findViewById(R.id.achievementViewTimeAndWords);
        achievementView
                .setTitle(String.valueOf(getActivity().getString(R.string.titleInfoFSettingApp)))
                .setMensage(String.valueOf(getActivity().getString(R.string.messageInfoFSettingApp)));
        achievementView.show();
    }

}
