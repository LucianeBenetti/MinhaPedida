package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.GerenciarCategoria;

public class GerenciarCategoriaActivity extends AppCompatActivity {

    private GerenciarCategoria control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_categoria);
        control = new GerenciarCategoria(this);
    }

    public void salvarcategoria(View v){
        control.salvarcategoriaAction();
    }

}
