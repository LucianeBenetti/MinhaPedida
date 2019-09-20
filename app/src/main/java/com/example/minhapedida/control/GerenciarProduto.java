package com.example.minhapedida.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.minhapedida.R;
import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.dao.db.CategoriaDao;
import com.example.minhapedida.dao.db.ItemDao;
import com.example.minhapedida.dao.db.ProdutoDao;
import com.example.minhapedida.model.Categoria;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.model.Produto;
import com.example.minhapedida.view.GerenciarCategoriaActivity;
import com.example.minhapedida.view.ProdutoActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class GerenciarProduto {

    private Activity activity;
    private Spinner spCategoria;
    private EditText nomeProduto;
    private EditText valorProduto;
    private ListView lvProdutos;
    private List<Produto> listProdutos;
    private ArrayAdapter<Produto> adapterProduto;
    private List<Categoria> listCategoria;
    private ArrayAdapter<Categoria> adapterCategoria;
    private Produto produto;

    private ProdutoDao produtoDao;
    private CategoriaDao categoriaDao;
    //caso tenha um listView tbwm, deve ter outro arrayadpter para o listView

    public GerenciarProduto(Activity activity) {
        this.activity = activity;
        produtoDao = new ProdutoDao(activity);
        categoriaDao = new CategoriaDao(activity);
        produto = new Produto();
    //    configListView();
        initComponents();
    }

    private void initComponents() {
        lvProdutos = activity.findViewById(R.id.lvProdutos);
        spCategoria = activity.findViewById(R.id.spCategoria);
        nomeProduto = activity.findViewById(R.id.editNomeProduto);
        valorProduto = activity.findViewById(R.id.editValor);
        configSpinner();
    }

    public void configSpinner() {

        try {
            listCategoria = categoriaDao.getDao().queryForAll();
            adapterCategoria = new  ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, listCategoria);
            spCategoria.setAdapter(adapterCategoria);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configListView() {

        try {
            listProdutos = produtoDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterProduto = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listProdutos);
        lvProdutos.setAdapter(adapterProduto);
        cliqueCurto();
        cliqueLongo();
    }

    private void cliqueLongo() {
        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                produto = adapterProduto.getItem(i);
                dialogExcluirProduto(produto);
                return true; //executa somente clique longo
            }
        });
    }


    private void addProdutoLv(Produto p) {
        adapterProduto.add(p);

    }

    private void cliqueCurto() {
        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                produto = adapterProduto.getItem(i);

                dialogAdicionarProduto(produto);
            }
        });
    }

    private void dialogAdicionarProduto(Produto p) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Mostrando dados");
        alerta.setMessage(produto.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                produto = null;
            }
        });
        alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carregarForm(produto);
            }
        });
        alerta.show();
    }

    private void carregarForm(Produto produto) {
        nomeProduto.setText(produto.getNome());
        valorProduto.setText(produto.getValor().toString());

    }

    private void dialogExcluirProduto(final Produto p) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Excluir Item");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setMessage(p.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                produto = null;
            }
        });
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                try {
                    if(produtoDao.getDao().delete(produto)>0) {
                        excluirProdutoLv(produto);

                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                produto = null;
            }
        });
        alerta.show();

    }

    private void excluirProdutoLv(Produto p) {
        adapterProduto.remove(p);
    }
    public void telaAddItemCategoriaAction() {
        Intent it = new Intent(activity, GerenciarCategoriaActivity.class);
        activity.startActivityForResult(it, Constantes.Request.ITEM);

    }

    private Produto getDadosForm() {
        Produto p = new Produto();
        p.setNome(nomeProduto.getText().toString());
        p.setValor(valorProduto.getText().toString());
        return p;
    }

    public void salvarProdutoAction() {
        if(produto==null){
            produto = getDadosForm();
        }else {
            Produto p = getDadosForm();
            produto.setNome(p.getNome());
            produto.setValor(p.getValor());
        }
        Dao.CreateOrUpdateStatus res;
        try {
            res = produtoDao.getDao().createOrUpdate(produto);

            if(res.isCreated()){
                addProdutoLv(produto);
            }else if(res.isUpdated()){

                atualizarProduto(produto);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        produto = null;


    }

    private void atualizarProduto(Produto p) {
        produto.setNome(p.getNome());
        produto.setValor(p.getValor());
        adapterProduto.notifyDataSetChanged();
    }
}
