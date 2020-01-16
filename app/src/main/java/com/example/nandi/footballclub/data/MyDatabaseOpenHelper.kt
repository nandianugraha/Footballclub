package com.example.nandi.footballclub.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.nandi.footballclub.model.FavoriteMatch
import com.example.nandi.footballclub.model.FavoriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.IDEVENT to TEXT + UNIQUE,
                FavoriteMatch.HOMETEAM to TEXT,
                FavoriteMatch.AWAYTEAM to TEXT,
                FavoriteMatch.HOMESCORE to TEXT,
                FavoriteMatch.AWAYSCORE to TEXT,
                FavoriteMatch.HOMERED to TEXT,
                FavoriteMatch.HOMEYELLOW to TEXT,
                FavoriteMatch.HOMEGK to TEXT,
                FavoriteMatch.HOMEDF to TEXT,
                FavoriteMatch.HOMEMF to TEXT,
                FavoriteMatch.HOMEFW to TEXT,
                FavoriteMatch.HOMESUB to TEXT,
                FavoriteMatch.HOMEFORMATION to TEXT,
                FavoriteMatch.AWAYRED to TEXT,
                FavoriteMatch.AWAYYELLOW to TEXT,
                FavoriteMatch.AWAYGK to TEXT,
                FavoriteMatch.AWAYDF to TEXT,
                FavoriteMatch.AWAYMF to TEXT,
                FavoriteMatch.AWAYFW to TEXT,
                FavoriteMatch.AWAYSUB to TEXT,
                FavoriteMatch.AWAYFORMATION to TEXT,
                FavoriteMatch.HOMESHOTS to TEXT,
                FavoriteMatch.AWAYSHOTS to TEXT,
                FavoriteMatch.DATEEVENT to TEXT,
                FavoriteMatch.TIME to TEXT,
                FavoriteMatch.HOMEID to TEXT,
                FavoriteMatch.AWAYID to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)