package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.ProdutoControl;
import com.example.minhapedida.model.Produto;

public class ProdutoActivity extends AppCompatActivity {
    private ProdutoControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        control = new ProdutoControl(this);

        }

    public void enviar(View v){
        control.enviarAction();


    }
}
