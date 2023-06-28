package com.example.matchupp.Herramientas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class},version = 1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {

    private static RoomDB databse;


    private static String DATABASE_NAME="tareas";

    public  synchronized static RoomDB getInstance(Context context){
        if(databse==null){
            databse= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return databse;
    }


    public abstract MainDao mainDao();

}
