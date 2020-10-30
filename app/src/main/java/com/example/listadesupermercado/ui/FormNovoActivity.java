package com.example.listadesupermercado.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadesupermercado.MainActivity;
import com.example.listadesupermercado.R;
import com.example.listadesupermercado.recyclerview.adapter.Lista;

public class FormNovoActivity extends AppCompatActivity {

    private EditText editTextProduto;
    private EditText editTextValor;
    private Button btnSalvar;
    private boolean eFormularioEdicao;
    private Lista lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_novo);

        carregaViews();
        cliqueBotao();

        Intent intent = getIntent();
        if (intent.hasExtra(Constantes.CHAVE_EDICAO_LISTA)){
            eFormularioEdicao = true;
            lista = (Lista) intent.getSerializableExtra(Constantes.CHAVE_EDICAO_ITEM);
            carregaDadosFormulario();
        }
    }

    private void carregaDadosFormulario() {
        editTextProduto.setText(lista.getProduto());
        editTextValor.setText((int) lista.getValor());
    }

    private void cliqueBotao() {
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eFormularioEdicao){
                    atualizaLista();

                    Intent intent = new Intent(FormNovoActivity.this, MainActivity.class);
                    intent.putExtra(Constantes.CHAVE_EDICAO_ITEM, lista);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }else{
                  //  lista = pegaListaDoFormulario();

                    Intent intent = new Intent(FormNovoActivity.this,MainActivity.class);
                    intent.putExtra(Constantes.CHAVE_NOVO_LISTA, lista);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }


        });
    }

    private void atualizaLista() {
        String modelo = editTextProduto.getText().toString();
        String placa = editTextValor.getText().toString();

        lista.setProduto(editTextProduto);
        lista.setValor(editTextValor);
    }

    private Lista pegaCarroDoFormulario() {
        String produto = editTextProduto.getText().toString();
        String valor = editTextValor.getText().toString();

        return new Lista(produto, valor);
    }

    private void carregaViews() {
        editTextValor = findViewById(R.id.editTextValor );
        editTextProduto = findViewById(R.id.editTextProduto);
    }
}
