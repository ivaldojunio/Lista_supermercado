package com.example.listadesupermercado.dataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.listadesupermercado.model.ListaDao;
import com.example.listadesupermercado.recyclerview.adapter.Lista;

@Database(entities = {Lista.class}, version = 2,exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public static AppDataBase getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    AppDataBase.class,
                    "Lista_Item.db").allowMainThreadQueries().
                    fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}