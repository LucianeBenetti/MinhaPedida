package com.example.minhapedida.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.minhapedida.R;
import com.example.minhapedida.control.ItemControl;

public class ItemActivity extends AppCompatActivity {

    private ItemControl control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        control = new ItemControl(this);
    }

    public void adicionarProduto(View v) {
        control.adicionarProdutoAction();
    }

    public void limparLista(View v) {
        control.limparListaAction();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        control.onActivityResult(requestCode, resultCode, data);
    }

}
