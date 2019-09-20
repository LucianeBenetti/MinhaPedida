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

    @DatabaseField(canBeNull = false, columnName = "quantidade", defaultValue = "1")
    private String Local;

    @DatabaseField(canBeNull = false, columnName = "quantidade", defaultValue = "1")
    private Integer mesa;

    @ForeignCollectionField(eager = true)
    private Collection<Item> listaItem;

    public Comanda() {
    }

    public Comanda(Integer id, String local, Integer mesa, Collection<Item> listaItem) {
        this.id = id;
        Local = local;
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
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Collection<Item> getListaItem() {
        return listaItem;
    }

    public void setListaItem(Collection<Item> listaItem) {
        this.listaItem = listaItem;
    }



    @Override
    public String toString() {
        return "Comanda{" +
                "id=" + id +
                ", Local='" + Local + '\'' +
                ", mesa=" + mesa +
                ", listaItem=" + listaItem +
                '}';
    }
}
