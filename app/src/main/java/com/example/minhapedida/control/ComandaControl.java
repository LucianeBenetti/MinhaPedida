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


import com.example.minhapedida.R;

import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.dao.db.ComandaDao;
import com.example.minhapedida.model.Comanda;
import com.example.minhapedida.view.ItemActivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class ComandaControl {

    private Activity activity;
    private EditText editMesa;
    private ListView lvComandas;
    private List<Comanda> listComanda;
    private ArrayAdapter<Comanda> adapterComanda;
    private Comanda comanda;
    private ComandaDao comandaDao;

    public ComandaControl(Activity activity) {
        this.activity = activity;
        comanda = new Comanda();
        comandaDao = new ComandaDao(activity);
        initComponents();
    }

    private void initComponents() {

        editMesa = activity.findViewById(R.id.editMesa);
        lvComandas = activity.findViewById(R.id.lvComandas);
        configListView();
    }


    public void configListView() {
        try {
            listComanda = comandaDao.getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterComanda = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listComanda);
        lvComandas.setAdapter(adapterComanda);
        cliqueCurto();
        cliqueLongo();
    }

    private void cliqueCurto() {
        lvComandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                comanda = adapterComanda.getItem(i);
                chamarActivityItem(comanda);

            }
        });
    }

    private void chamarActivityItem(Comanda comanda) {

        Intent it = new Intent(activity, ItemActivity.class);
        it.putExtra(Constantes.Parametros.ITEM, comanda);
        activity.startActivity(it);
    }

    private void cliqueLongo() {
        lvComandas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                comanda = adapterComanda.getItem(i);
                dialogExcluirComanda(comanda);
                return true;
            }
        });
    }

    private void dialogExcluirComanda(Comanda c) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Fechar Conta");
        alerta.setIcon(android.R.drawable.ic_menu_delete);
        alerta.setMessage(c.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                comanda = null;
            }
        });
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                try {
                    if (comandaDao.getDao().delete(comanda) > 0) {
                        excluirComandaLv(comanda);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                comanda = null;
            }
        });
        alerta.show();
    }

    private void excluirComandaLv(Comanda comanda) {
        adapterComanda.remove(comanda);
    }


    private void carregarForm(Comanda comanda) {
        editMesa.setText(comanda.getMesa());

    }

    public void novaComandaAction() {

        if (comanda == null) {
            comanda = getDadosForm();
        } else {
            Comanda c = getDadosForm();
            comanda.setMesa(c.getMesa());
            comanda.setLocal(c.getLocal());
        }
        Dao.CreateOrUpdateStatus res;
        try {
            res = comandaDao.getDao().createOrUpdate(comanda);

            if (res.isCreated()) {
                addComandaLv(comanda);
                chamarActivityItem(comanda);
            } else if (res.isUpdated()) {

                atualizarComanda(comanda);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        comanda = null;

    }

    private void atualizarComanda(Comanda c) {
        comanda.setMesa(c.getMesa());
        adapterComanda.notifyDataSetChanged();
    }

    private void addComandaLv(Comanda c) {
        adapterComanda.add(c);
    }

    private Comanda getDadosForm() {
        Comanda c = new Comanda();
        c.setMesa(editMesa.getText().toString());
        c.setLocal("Bar da Esquina");
        return c;

    }

}

