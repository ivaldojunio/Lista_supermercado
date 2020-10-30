package com.example.listadesupermercado.recyclerview.adapter;

import android.widget.EditText;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Lista implements Serializable {

    @PrimaryKey (autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Produto")
    private String produto;
    @ColumnInfo (name = "Valor")
    private double valor;

    public Lista(int id, String produto, double valor) {
        this.id = id;
        this.produto = produto;
        this.valor = valor;
    }

    public Lista(String produto, String valor) {
    }

    public int getId() {
        return id;
    }

    public String getProduto() { return produto; }

    public double getValor() { return valor;}

    public void setId(int id) {
        this.id = id;
    }

    public void setProduto(EditText produto) {
        this.produto = produto;
    }

    public void setValor(EditText valor) { this.valor = valor;
    }

    public int getQnt() {
    }
}



