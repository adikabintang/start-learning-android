package com.mycompany.sqliteoi.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

// PR: nanti jadiin private semua, trus ini Class implement Interface model (liat BasicSample
// dari google)
@Entity(tableName = "User")
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    public String lastName;

    @Ignore
    Bitmap picture;

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return "id: " + this.id + "\n"
                + "firstname: " + this.firstName + "\n"
                + "lastName: " + this.lastName + "\n";
    }
}
