package com.eugene.mytestaliasapp.Fragments;


import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.eugene.mytestaliasapp.DataBase.DatabaseHelper;
import com.eugene.mytestaliasapp.ModelAndTable.Teams;
import com.eugene.mytestaliasapp.R;
import com.manojbhadane.QButton;
import com.rafaelbarbosatec.archivimentview.AchievementView;
import com.sdsmdg.tastytoast.TastyToast;
import com.thekhaeng.pushdownanim.PushDownAnim;


public class FragmentTeams extends Fragment {
    QButton buttonAddTeams;
    QButton buttonFragmentCategories;

    int attempt = 0;
    String teamName;
    String playerOneName;
    String playerTwoName;

    private SQLiteDatabase mDataBase;
    private DatabaseHelper databaseHelper;
    private Teams teams;


    public FragmentTeams() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        achievementView(view);
        openDialogTeam(view);
        openCategoriesFragment(view);
        initObjects();

        return view;
    }

    private void openDialogTeam(final View view) {
        buttonAddTeams = (QButton) view.findViewById(R.id.buttonAddTeams);
        buttonAddTeams.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf"));
        buttonAddTeams.setTextSize(23);
        PushDownAnim.setPushDownAnimTo(buttonAddTeams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogTeam = new Dialog(getActivity());
                dialogTeam.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogTeam.setContentView(R.layout.dialog_team);
                dialogTeam.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialogTeam.show();

                final ImageButton buttonTeamMignon = (ImageButton) dialogTeam.findViewById(R.id.buttonTeamMignon);
                final ImageButton buttonTeamFlash = (ImageButton) dialogTeam.findViewById(R.id.buttonTeamFlash);
                final ImageButton buttonTeamSuperman = (ImageButton) dialogTeam.findViewById(R.id.buttonTeamSuperman);
                final ImageButton buttonTeamBatman = (ImageButton) dialogTeam.findViewById(R.id.buttonTeamBatman);
                buttonTeamMignon.setPressed(false);


                final EditText editTextNameTeam = (EditText) dialogTeam.findViewById(R.id.editTextNameTeam);
                final EditText editTextNamePlayer1 = (EditText) dialogTeam.findViewById(R.id.editTextNamePlayer1);
                final EditText editTextNamePlayer2 = (EditText) dialogTeam.findViewById(R.id.editTextNamePlayer2);

                QButton buttonCancelDialogTeam = (QButton) dialogTeam.findViewById(R.id.buttonCancelDialogTeam);
                QButton buttonOkDialogTeam = (QButton) dialogTeam.findViewById(R.id.buttonOkDialogTeam);

                PushDownAnim.setPushDownAnimTo(buttonCancelDialogTeam).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogTeam.cancel();
                    }
                });

                teamName = editTextNameTeam.getText().toString();
                playerOneName = editTextNamePlayer1.getText().toString();
                playerTwoName = editTextNamePlayer2.getText().toString();

                PushDownAnim.setPushDownAnimTo(buttonOkDialogTeam).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (teamName.length() > 0 || playerOneName.length() > 0 || playerTwoName.length() > 0) {
                            TastyToast.makeText(getActivity(), "Не введены название команды или имена играков", TastyToast.LENGTH_LONG, TastyToast.WARNING).show();
                        } else {
                            attempt++;

                            teams.setNameTeam(editTextNameTeam.getText().toString().trim());
                            teams.setNamePlayerOne(editTextNamePlayer1.getText().toString().trim());
                            teams.setNamePlayerTwo(editTextNamePlayer2.getText().toString().trim());
                            teams.setAttempt(attempt);

                            TastyToast.makeText(getActivity(), "Команда: " + teams.getNameTeam() + " добавлена", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);

                            databaseHelper.addTeams(teams);


                            editTextNameTeam.setText(null);
                            editTextNamePlayer1.setText(null);
                            editTextNamePlayer2.setText(null);
                        }

                        if (attempt == 4) {
                            dialogTeam.cancel();
                            buttonAddTeams.setVisibility(View.GONE);
                            TastyToast.makeText(getActivity(), "Достигнуто максимальное количество команда: 4", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).setGravity(Gravity.CENTER, 0, 0);
                        }
                    }
                });

                PushDownAnim.setPushDownAnimTo(buttonTeamMignon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });

    }
    private void initObjects() {
        databaseHelper = new DatabaseHelper(getActivity());
        teams = new Teams();
    }

    private void openCategoriesFragment(View view) {
        buttonFragmentCategories = (QButton) view.findViewById(R.id.buttonFragmentCategories);
        buttonFragmentCategories.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/" +
                "MailRayStuff.ttf"));
        buttonFragmentCategories.setTextSize(23);

        PushDownAnim.setPushDownAnimTo(buttonFragmentCategories).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void achievementView(View view) {
        final AchievementView achievementView = (AchievementView) view.findViewById(R.id.achievementViewInformationTeams);
        achievementView
                .setTitle(String.valueOf(getActivity().getString(R.string.titleInfoTeams)))
                .setMensage(getActivity().getString(R.string.messageInfoTeams));
        achievementView.show();
    }

}
