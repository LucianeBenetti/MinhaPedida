package com.example.minhapedida.dao.db;

import android.content.Context;

import com.example.minhapedida.dao.helpers.DaoHelper;
import com.example.minhapedida.model.Produto;

public class ProdutoDao extends DaoHelper<Produto> {

    public ProdutoDao(Context c) {
        super(c, Produto.class);
    }
}
