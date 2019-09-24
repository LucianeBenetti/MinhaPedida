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
import android.widget.TextView;

import com.example.minhapedida.R;
import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.dao.db.ComandaDao;
import com.example.minhapedida.dao.db.ItemDao;
import com.example.minhapedida.model.Comanda;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.view.GerenciarCategoriaActivity;
import com.example.minhapedida.view.ItemActivity;

import java.sql.SQLException;
import java.util.List;

public class ComandaControl {

    private Activity activity;
    private EditText editMesa;
    private EditText editLocal;
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
        editLocal = activity.findViewById(R.id.editLocal);
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
        alerta.setTitle("Excluir Item");
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
                    if(comandaDao.getDao().delete(comanda)>0) {
                        excluirComandaLv(comanda);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                comandaDao = null;
            }
        });
        alerta.show();
    }

    private void excluirComandaLv(Comanda comanda) {
        adapterComanda.remove(comanda);
    }


    private void cliqueCurto() {
        lvComandas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                comanda = adapterComanda.getItem(i);

                dialogAdicionarComanda(comanda);
            }
        });
    }

    private void dialogAdicionarComanda(Comanda c) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Mostrando dados");
        alerta.setMessage(comanda.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                comanda = null;
            }
        });
        alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                carregarForm(comanda);
            }
        });
        alerta.show();
    }

    private void carregarForm(Comanda comanda) {
        editMesa.setText(comanda.getMesa());
        editLocal.setText(comanda.getLocal());
    }

    public void novaComandaAction() {
        Intent it = new Intent(activity, ItemActivity.class);
        activity.startActivity(it);
    }
}
