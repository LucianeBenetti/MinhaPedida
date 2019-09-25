package com.example.minhapedida.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

@DatabaseTable(tableName = "comanda")
public class Comanda implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false, columnName = "local")
    private String local;

    @DatabaseField(canBeNull = false, columnName = "mesa")
    private Integer mesa;

    @ForeignCollectionField(eager = true)
    private Collection<Item> listaItem;

    public Comanda() {
    }

    public Comanda(Integer id, String local, Integer mesa, Collection<Item> listaItem) {
        this.id = id;
        local = local;
        this.mesa = mesa;
        this.listaItem = listaItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        try {
            this.mesa = Integer.parseInt(mesa);
        } catch (Exception e) {
            this.mesa = null;
        }
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Collection<Item> getListaItem() {
        return listaItem;
    }

    public int getQuantidadeItens() {
        if(listaItem!=null){
            return listaItem.size();
        } else {
            return 0;
        }
    }

    public void setListaItem(Collection<Item> listaItem) {
        this.listaItem = listaItem;
    }


    @Override
    public String toString() {
            return id + " - Mesa: " + mesa + "\n\n" +
                    "Local: " + local + "\n\n" +
                    "Itens: " + getQuantidadeItens();

    }
}
