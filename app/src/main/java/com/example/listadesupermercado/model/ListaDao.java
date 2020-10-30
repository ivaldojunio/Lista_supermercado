package com.example.listadesupermercado.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.listadesupermercado.recyclerview.adapter.Lista;

import java.util.List;

@Dao public interface  ListaDao {
    @Insert
    void insert(Lista lista);

    @Delete
    void delete(Lista lista);

    @Update
    void update (Lista lista);

    @Query("SELECT * FROM Lista")
    List<Lista> getAll();


}
