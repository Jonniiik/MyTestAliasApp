package com.eugene.mytestaliasapp.ModelAndTable;

import android.provider.BaseColumns;

public class TeamsTable {
    private TeamsTable(){}
    public static final class TeamsEntry implements BaseColumns{
        public static final String TABLE_NAME = "teams";
        public static final String COLUMN_NAME_TEAM = "nameTeam";
        public static final String COLUMN_NAME_Player_ONE = "namePlayerOne";
        public static final String COLUMN_NAME_Player_TWO = "namePlayerTwo";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_ATTEMPT = "attempt";
    }
}
