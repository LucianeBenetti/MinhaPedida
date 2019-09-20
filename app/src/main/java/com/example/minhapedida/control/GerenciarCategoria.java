package com.example.minhapedida.control;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.minhapedida.R;
import com.example.minhapedida.dao.db.CategoriaDao;
import com.example.minhapedida.model.Categoria;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

public class GerenciarCategoria {

    private Activity activity;
    private EditText nomeCategoria;
    private ListView lvCategorias;
    private CategoriaDao categoriaDao;
    private List<Categoria> listCategoria;
    private ArrayAdapter<Categoria> adapterCategoria;
    private Categoria categoria;

    public GerenciarCategoria(Activity activity) {
        categoriaDao = new CategoriaDao(activity);
        categoria = new Categoria();
        this.activity = activity;
        configListView();
        initComponents();
}

    private void initComponents() {

        nomeCategoria = activity.findViewById(R.id.editNomeCategoria);
        lvCategorias = activity.findViewById(R.id.lvCategorias);
    }

    private void configListView() {

        try {
            listCategoria = categoriaDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterCategoria = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, listCategoria);
        //para spinner tem um R.layout.spinner.
        lvCategorias.setAdapter(adapterCategoria);
        cliqueCurto();
        cliqueLongo();
    }

    private void cliqueLongo() {

    }

    private void cliqueCurto() {
    }

    public void salvarCategoriaAction() {

        if(categoria==null){
            categoria = getDadosForm();
        }else {
            Categoria c = getDadosForm();
            categoria.setNome(c.getNome());

        }
        Dao.CreateOrUpdateStatus res;
        try {
            res = categoriaDao.getDao().createOrUpdate(categoria);

            if(res.isCreated()){
                addCategoriaLv(categoria);
            }else if(res.isUpdated()){

                atualizarCategoria(categoria);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        categoria = null;

    }

    private void addCategoriaLv(Categoria c) {
        adapterCategoria.add(c);
    }

    private void excluirCategoriaLv(Categoria c) {
        adapterCategoria.remove(c);
    }

    private void atualizarCategoria(Categoria e) {
        categoria.setNome(e.getNome());
        adapterCategoria.notifyDataSetChanged();
    }

    private Categoria getDadosForm() {
        Categoria c = new Categoria();
        c.setNome(nomeCategoria.getText().toString());
        return c;
    }

    private void carregarForm(Categoria e) {
        nomeCategoria.setText(e.getNome());

    }


}
