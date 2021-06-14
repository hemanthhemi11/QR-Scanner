package com.example.myscanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class} ,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDao dataDao();
    private static AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"DB_Name")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}
