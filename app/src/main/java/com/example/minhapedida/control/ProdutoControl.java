package com.example.minhapedida.control;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.example.minhapedida.R;
import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.dao.db.ProdutoDao;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.model.Produto;
import com.example.minhapedida.view.GerenciarProdutoActivity;

import java.sql.SQLException;
import java.util.List;

public class ProdutoControl {

    private Activity activity;
    private Spinner spProduto;
    private List<Produto> listProduto;
    private ArrayAdapter<Produto> adapterProduto;
    private NumberPicker npQtdade;

    private ProdutoDao produtoDao;
    //caso tenha um listView tbwm, deve ter outro arrayadpter para o listView

    public ProdutoControl(Activity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(activity);

        initComponents();
    }

    private void initComponents() {

        spProduto = activity.findViewById(R.id.spProduto);
        npQtdade = activity.findViewById(R.id.npQtdade);
        configSpinner();
        configurarNumberPicker();
    }

    public void configSpinner() {

        try {

            listProduto = produtoDao.getDao().queryForAll();
            adapterProduto = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, listProduto);
            spProduto.setAdapter(adapterProduto);

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        Item item = getDadosFormItemProduto();

        Intent it = new Intent();
        it.putExtra(Constantes.Parametros.ITEM, item);
        activity.setResult(activity.RESULT_OK, it);
        activity.finish();

    }

    public void cancelarAction() {
        activity.setResult(activity.RESULT_CANCELED);
        activity.finish();
    }

    public void gerenciarProdutoAction() {
        Intent it = new Intent(activity, GerenciarProdutoActivity.class);
        activity.startActivity(it);

    }
}
