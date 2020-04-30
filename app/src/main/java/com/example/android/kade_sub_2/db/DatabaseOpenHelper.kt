package com.example.android.kade_sub_2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.model.TeamModel
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,"FavoriteMatch.db",null,1){
    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseOpenHelper {
            if(instance == null){
                instance = DatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchModel.TABLE_FAVORITE,true,

                 MatchModel.ID_EVENT to TEXT + UNIQUE + PRIMARY_KEY,
                 MatchModel.HOME_TEAM to TEXT,
                 MatchModel.AWAY_TEAM to TEXT,
                 MatchModel.HOME_TEAM_ID to TEXT,
                 MatchModel.AWAY_TEAM_ID to TEXT,
                 MatchModel.DATE_EVENT to TEXT,
                 MatchModel.TIME_LOCAL to TEXT,
                 MatchModel.TYPE to TEXT,
                 MatchModel.HOME_SCORE to TEXT,
                 MatchModel.AWAY_SCORE to TEXT,
                 MatchModel.POSTPONED to TEXT)

        db.createTable(TeamModel.TEAM_FAVORITE,true,
        TeamModel.ID_TEAM to TEXT + UNIQUE + PRIMARY_KEY,
                TeamModel.STR_TEAM to TEXT,
                TeamModel.STR_LEAGUE to TEXT,
                TeamModel.STR_TEAM_BADGE to TEXT)
        }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(MatchModel.TABLE_FAVORITE, true)
        db.dropTable(TeamModel.TEAM_FAVORITE, true)
    }

}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)