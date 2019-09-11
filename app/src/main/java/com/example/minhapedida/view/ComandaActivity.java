package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.ComandaControl;

public class ComandaActivity extends AppCompatActivity {
private ComandaControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);
        control = new ComandaControl(this);
    }

    public void telaAddItemComanda(View v){
        control.telaAddItemComandaAction();
    }

    public void limparLista(View v){
        control.limparListaAction();
    }

}
