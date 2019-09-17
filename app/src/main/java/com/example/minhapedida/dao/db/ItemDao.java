package com.example.minhapedida.dao.db;

import android.content.Context;

import com.example.minhapedida.dao.helpers.DaoHelper;
import com.example.minhapedida.model.Item;

public class ItemDao extends DaoHelper<Item> {

    public ItemDao(Context c) {
        super(c, Item.class);
    }


}
