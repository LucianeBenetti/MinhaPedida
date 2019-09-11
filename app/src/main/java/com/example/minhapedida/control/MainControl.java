package com.example.minhapedida.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhapedida.Uteis.Constantes;
import com.example.minhapedida.R;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.view.ProdutoActivity;

import java.util.ArrayList;
import java.util.List;

public class MainControl {

    private Activity activity;
    private TextView tvTotal;
    private ListView lvItens;
    private List<Item> listItem;
    private ArrayAdapter<Item> adapterItem;
    private Item item;

    public MainControl(Activity activity) {
        this.activity = activity;
        item = new Item();
        initComponents();
    }

    private void initComponents() {

        tvTotal = activity.findViewById(R.id.tvTotal);
        lvItens = activity.findViewById(R.id.lvItens);
        configListView();
    }

    private void configListView() {
        listItem = new ArrayList<>();
        adapterItem = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listItem);
        //para spinner tem um R.layout.spinner.
        lvItens.setAdapter(adapterItem);
        cliqueCurto();
        cliqueLongo();
    }

    private void addItemLv(Item i) {
        adapterItem.add(i);

    }

    private void excluirItemLv(Item i) {
        adapterItem.remove(i);
    }

    private void atualizarItem (Item i) {
        item.setQtdade(i.getQtdade() + 1);
        adapterItem.notifyDataSetChanged();
        item = null;
    }

    private void cliqueLongo() {
        lvItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterItem.getItem(i);
                //return false = excuta o clique curto junto
                //  return false;
                dialogExcluirItem(item);
                //return false = excuta o clique curto junto
                //  return false;
                return true; //executa somente clique longo
            }
        });
    }

    private void cliqueCurto() {
        lvItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterItem.getItem(i);

                dialogEditarItem(item);
            }
        });
    }


    private void atualizarTotal() {
        Double total = 0.0;
        for (Item item:listItem) {
            total += item.getSubtotal();

        }
        tvTotal.setText("Total: " + total );
    }


    private void dialogExcluirItem(final Item i) {

        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Exclir Item.");
        alerta.setMessage(i.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                item = null;
            }
        });
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                excluirItemLv(i);
                item = null;
                atualizarTotal();
            }
        });
        alerta.show();
    }

    private void dialogEditarItem (final Item i){

        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle("Adicionar item.");
        alerta.setMessage(i.toString());
        alerta.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                item = null;
            }
        });
        alerta.setPositiveButton("+1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int pos) {
                atualizarItem(i);
                atualizarTotal();
            }
        });
        alerta.show();
    }

    public void adicionarProdutoAction(){
        Intent it = new Intent(activity, ProdutoActivity.class);
        activity.startActivityForResult(it, Constantes.Request.ITEM);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == activity.RESULT_OK) {
            if (requestCode == Constantes.Request.ITEM) {
                item = (Item) data.getSerializableExtra(Constantes.Parametros.ITEM);
                addItemLv(item);
                atualizarTotal();
                item = null;
            } else if (resultCode == activity.RESULT_CANCELED) {
                Toast.makeText(activity, R.string.acao_cancelada, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void limparListaAction(){

        adapterItem.clear();

        }
}
