package com.example.minhapedida.dao.db;

import android.content.Context;

import com.example.minhapedida.dao.helpers.DaoHelper;
import com.example.minhapedida.model.Comanda;

public class ComandaDao extends DaoHelper<Comanda> {

    public ComandaDao(Context c) {
        super(c, Comanda.class);
    }
}
