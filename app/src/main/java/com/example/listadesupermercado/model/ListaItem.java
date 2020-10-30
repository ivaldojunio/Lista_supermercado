package com.example.listadesupermercado.model;

import androidx.room.ColumnInfo;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import java.io.Serializable;

     public class ListaItem implements Serializable {
     @PrimaryKey (autoGenerate = true)
     private int id;
     @ColumnInfo(name = "produto")
     private String produto;
     @ColumnInfo (name = "Valor")
     private double valor;

         public ListaItem (String produto, double valor) {
             this.produto = produto;
             this.valor = valor;
         }

         public int getId() {
             return id;
         }

         public String getProduto() {
             return produto;
         }

         public double getValor() {
             return valor;
         }

         public void setId(int id) {
             this.id = id;
         }

         public void setProduto(String produto) {
             this.produto = produto;
         }

         public void setValor(double valor) {
             this.valor = valor;
         }
}
