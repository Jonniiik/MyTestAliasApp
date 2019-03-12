package com.eugene.mytestaliasapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.eugene.mytestaliasapp.ModelAndTable.Teams;
import com.eugene.mytestaliasapp.ModelAndTable.TeamsTable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "alias.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CTEATE_TEAM_TABLE = "CREATE TABLE " +
                TeamsTable.TeamsEntry.TABLE_NAME + " (" +
                TeamsTable.TeamsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TeamsTable.TeamsEntry.COLUMN_NAME_TEAM + " TEXT NOT NULL, " +
                TeamsTable.TeamsEntry.COLUMN_NAME_Player_ONE + " TEXT NOT NULL, " +
                TeamsTable.TeamsEntry.COLUMN_NAME_Player_TWO + " TEXT NOT NULL, " +
                TeamsTable.TeamsEntry.COLUMN_SCORE + " TEXT, " +
                TeamsTable.TeamsEntry.COLUMN_ATTEMPT + " TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CTEATE_TEAM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TeamsTable.TeamsEntry.TABLE_NAME);
        onCreate(db);
    }

    public DatabaseHelper open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public void addTeams(Teams teams) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesTeam = new ContentValues();
        valuesTeam.put(TeamsTable.TeamsEntry.COLUMN_NAME_TEAM, teams.getNameTeam());
        valuesTeam.put(TeamsTable.TeamsEntry.COLUMN_NAME_Player_ONE, teams.getNamePlayerOne());
        valuesTeam.put(TeamsTable.TeamsEntry.COLUMN_NAME_Player_TWO, teams.getNamePlayerTwo());
        valuesTeam.put(TeamsTable.TeamsEntry.COLUMN_SCORE, teams.getScore());
        valuesTeam.put(TeamsTable.TeamsEntry.COLUMN_ATTEMPT, teams.getAttempt());

        db.insert(TeamsTable.TeamsEntry.TABLE_NAME, null, valuesTeam);
        db.close();
    }

    public List<Teams> getAllTeams(){
        String[] columns = {
                TeamsTable.TeamsEntry.COLUMN_NAME_TEAM,
                TeamsTable.TeamsEntry.COLUMN_NAME_Player_ONE,
                TeamsTable.TeamsEntry.COLUMN_NAME_Player_TWO,
                TeamsTable.TeamsEntry.COLUMN_SCORE,
                TeamsTable.TeamsEntry.COLUMN_ATTEMPT
        };

        String sortOrder = TeamsTable.TeamsEntry._ID;
        List<Teams> teamsList = new ArrayList<Teams>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TeamsTable.TeamsEntry.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()){
            do {
                Teams teams = new Teams();
                teams.setNameTeam(cursor.getString(cursor.getColumnIndex(TeamsTable.TeamsEntry.COLUMN_NAME_TEAM)));
                teams.setNamePlayerOne(cursor.getString(cursor.getColumnIndex(TeamsTable.TeamsEntry.COLUMN_NAME_Player_ONE)));
                teams.setNamePlayerTwo(cursor.getString(cursor.getColumnIndex(TeamsTable.TeamsEntry.COLUMN_NAME_Player_TWO)));
                teams.setScore(cursor.getInt(cursor.getColumnIndex(TeamsTable.TeamsEntry.COLUMN_SCORE)));
                teams.setAttempt(cursor.getInt(cursor.getColumnIndex(TeamsTable.TeamsEntry.COLUMN_ATTEMPT)));

                teamsList.add(teams);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return teamsList;
    }
}
