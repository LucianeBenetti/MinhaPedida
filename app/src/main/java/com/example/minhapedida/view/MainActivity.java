package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.MainControl;

public class MainActivity extends AppCompatActivity {

    private MainControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new MainControl(this);
    }

    public void adicionarProduto (View v){
        control.adicionarProdutoAction();
    }
    public void limparLista (View v){
        control.limparListaAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.onActivityResult(requestCode, resultCode, data);
    }

}
