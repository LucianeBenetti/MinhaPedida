package com.example.minhapedida.dao.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.minhapedida.model.Item;
import com.example.minhapedida.model.Produto;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class MyORMLiteHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "minha_pedida.db";
    private static final int DATABASE_VERSION = 1;

    public MyORMLiteHelper (Context c){
        super (c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Produto.class);
            TableUtils.createTable(connectionSource, Item.class);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

        try{
            TableUtils.dropTable(connectionSource, Produto.class, true);
            TableUtils.dropTable(connectionSource, Item.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
