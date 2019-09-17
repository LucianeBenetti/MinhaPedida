package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.GerenciarProduto;

public class GerenciarProdutoActivity extends AppCompatActivity {
private GerenciarProduto control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciarproduto);
        control = new GerenciarProduto(this);
    }

    public void telaAddItemCategoria(View v){
        control.telaAddItemCategoriaAction();
    }


}
