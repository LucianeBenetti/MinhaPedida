package com.example.minhapedida.dao.db;

import android.content.Context;

import com.example.minhapedida.dao.helpers.DaoHelper;
import com.example.minhapedida.model.Categoria;

public class CategoriaDao extends DaoHelper<Categoria> {

    public CategoriaDao(Context c) {
        super(c, Categoria.class);
    }

}
