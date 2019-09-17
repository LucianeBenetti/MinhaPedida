package com.example.minhapedida.control;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.minhapedida.R;
import com.example.minhapedida.dao.db.CategoriaDao;
import com.example.minhapedida.dao.db.ItemDao;
import com.example.minhapedida.dao.db.ProdutoDao;
import com.example.minhapedida.model.Categoria;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.model.Produto;

import java.sql.SQLException;
import java.util.List;

public class GerenciarProduto {

    private Activity activity;
    private Spinner spCategoria;
    private EditText nomeProduto;
    private EditText valorProduto;
    private ListView lvItens;
    private List<Item> listItem;
    private ArrayAdapter<Item> adapterItem;
    private List<Categoria> listCategoria;
    private ArrayAdapter<Categoria> adapterCategoria;

    private ItemDao itemDao;
    private CategoriaDao categoriaDao;
    //caso tenha um listView tbwm, deve ter outro arrayadpter para o listView

    public GerenciarProduto(Activity activity) {
        this.activity = activity;
        itemDao = new ItemDao(activity);
        categoriaDao = new CategoriaDao(activity);
        configListView();
        initComponents();
    }

    private void initComponents() {

        spCategoria = activity.findViewById(R.id.spCategoria);
        nomeProduto = activity.findViewById(R.id.editNomeProduto);
        valorProduto = activity.findViewById(R.id.editValor);
        configSpinner();
    }

    private void configSpinner() {

        try {
            categoriaDao.getDao().createIfNotExists(new Categoria());
            listCategoria = categoriaDao.getDao().queryForAll();
            adapterCategoria = new  ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, listCategoria);
            spCategoria.setAdapter(adapterCategoria);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configListView() {

        try {
            listItem = itemDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterItem = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listItem);
        //para spinner tem um R.layout.spinner.
        lvItens.setAdapter(adapterItem);
        cliqueCurto();
        cliqueLongo();
    }

    private void cliqueLongo() {
    }

    private void cliqueCurto() {
    }

    public void telaAddItemCategoriaAction() {
    }
}
