package com.mycompany.sqliteoi.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.mycompany.sqliteoi.db.dao.UserDaoInterface;
import com.mycompany.sqliteoi.db.entity.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase dbInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "db_name";

    public abstract UserDaoInterface userDaoInterface();

    // singleton
    public static AppDatabase getDbInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME)
                    .build();
        }
        return dbInstance;
    }

    public static void destroyDbInstance() {
        dbInstance = null;
    }
}
