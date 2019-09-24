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

    public void novaComanda(View v){
        control.novaComandaAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        control.configListView();
    }

}
