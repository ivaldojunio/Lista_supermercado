package com.example.listadesupermercado;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.example.listadesupermercado.dataBase.AppDataBase;
import com.example.listadesupermercado.recyclerview.adapter.Lista;
import com.example.listadesupermercado.ui.Constantes;
import com.example.listadesupermercado.ui.FormNovoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private recyclerViewLista recyclerViewLista;
    private RecyclerView recyclerViewbntNovoItem;
    private FloatingActionButton bntNovoItem;

    static List<listas> listas;
    private ListaAdapter adapter;
    private int posicaoItemClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
     // geraListaItem();

     //configuraRecyclerView();

     // cliqueBotao();


    private void cliqueBotao() {
        //Recuperando a View do botão
        bntNovoItem = findViewById(R.id.bntNovoItem);
        bntNovoItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, com.example.listadesupermercado.ui.bntNovoItem.class);

                //Usamos o metodo abaixo para dizer ao android que a proxima tela deve devolver um objeto de lista
                startActivityForResult(intent, Constantes.SOLICITA_NOVO_ITEM);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Aqui verificamos se o request(codigo de requisição) code e o extra estão corretos
        if (requestCode == Constantes.SOLICITA_NOVO_ITEM && data.hasExtra(Constantes.CHAVE_NOVO_LISTA)) {

            //Se o result code vier como ok significa que podemos continuar com a processo de um nova lista
            if (resultCode == Activity.RESULT_OK) {

                //recuperando os dados(Objeto Lista) vindos da ItemNovoActivity
                Lista lista = (Lista) data.getSerializableExtra(Constantes.CHAVE_NOVO_LISTA);

                AppDataBase
                        .getInstance(getApplicationContext())
                        .ListaDao()
                        .insert(lista);
                //Adicionando o objeto(Lista) na lista
                lista.clear();
                lista.addAll(AppDataBase
                       .getInstance(getApplicationContext())
                        .listaDao()
                        .getAll());
                adapter.notifyDataSetChanged();

                //Adicionar objeto no banco
                AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "Lista_Item.db").
                        allowMainThreadQueries().fallbackToDestructiveMigration().build();

                db.ListaDao().update(lista);


                //Notificando o Adapter para que o recyclerview seja atualizado
            }
        }
        if (requestCode == Constantes.SOLICITA_EDICAO_ITEM && data.hasExtra(Constantes.CHAVE_EDICAO_ITEM)) {
            if (resultCode == Activity.RESULT_OK) {
                Lista lista = (Lista) data.getSerializableExtra(Constantes.CHAVE_EDICAO_ITEM);
                AppDataBase
                        .getInstance(getApplicationContext())
                        .ListaDao().update(lista);


                listas.set(posicaoItemClick, listas);
                adapter.notifyItemChanged(posicaoItemClick);

            }
        }
    }

    private void configuraRecyclerView() {
        recyclerViewbntNovoItem = findViewById(R.id.recyclerViewLista);
        //Aqui estamos configurando o LayoutManager, além no linearlayot temos outras como GridLayoutManager (depois dá uma pesquisada)
        recyclerViewbntNovoItem.setLayoutManager(new LinearLayoutManager(this));

        //o adapter serve para junta os daddos da lista com os item das View do recyclerView
        ListaAdapter adapter = new ListaAdapter(getApplicationContext(), listas);
        recyclerViewLista.setAdapter(adapter);
        adapter.notify(new listasItemClickListener() {


            @Override
            public void itemClick(Lista lista, int posicao) {
                // criar intent e manda pra outra tela
                posicaoItemClick = posicao;
                Intent intent = new Intent(MainActivity.this,
                        FormNovoActivity.class);
                intent.putExtra(Constantes.CHAVE_EDICAO_LISTA, (Parcelable) lista);
                startActivityForResult(intent, Constantes.SOLICITA_EDICAO_ITEM);

            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListaItemTouchhelper(adapter));
        ItemTouchHelper.attachToRecyclerView(recyclerViewLista);

    }

    void geraListaItem() {
        listas = AppDataBase.
                getInstance(getApplicationContext())
                .ListaDao.getAll();
    }
}