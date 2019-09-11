package com.example.minhapedida.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.minhapedida.R;
import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.model.Produto;
import com.example.minhapedida.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class ProdutoControl {

    private Activity activity;
    private Spinner spProduto;
    private List<Produto> listProduto;
    private ArrayAdapter<Produto> adapterItem;
    private NumberPicker npQtdade;

    //caso tenha um listView tbwm, deve ter outro arrayadpter para o listView

    public ProdutoControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {

        spProduto = activity.findViewById(R.id.spProduto);
        npQtdade = activity.findViewById(R.id.npQtdade);
        configSpinner();
        configurarNumberPicker();
    }

    private void configSpinner() {
        listProduto = new ArrayList<>();
        listProduto.add(new Produto("Refrigerante", 3.00));
        listProduto.add(new Produto("Cerveja", 5.00));
        listProduto.add(new Produto("Batata Frita", 10.00));
        listProduto.add(new Produto("Água", 2.50));
        listProduto.add(new Produto("Pastel", 3.50));
        listProduto.add(new Produto("Petiscos", 6.00));

        adapterItem = new  ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, listProduto);
       // adapterItem.addAll(listProduto);
        spProduto.setAdapter(adapterItem);
    }

    private void configurarNumberPicker() {
        npQtdade.setMinValue(0);
        npQtdade.setMaxValue(20);
       // npQtdade.setValue(10);//caso nao use o setValue por default mantém o valor mínimo
    }

    private Item getDadosFormItemProduto() {

        Item item = new Item();
        item.setProduto((Produto) spProduto.getSelectedItem());
        item.setQuantidade(npQtdade.getValue());
        return item;
    }


    public void enviarAction() {

        Item item  = getDadosFormItemProduto();

            Intent it = new Intent();
            it.putExtra(Constantes.Parametros.ITEM, item);
            activity.setResult(activity.RESULT_OK, it);
            activity.finish();

    }

}
