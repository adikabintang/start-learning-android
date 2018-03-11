package com.mycompany.sqliteoi.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mycompany.sqliteoi.db.entity.User;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDaoInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);

    @Insert
    public void insertBothUsers(User user1, User user2);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);

    @Update
    public void updateUsers(User... users);

    @Delete
    public void deleteUsers(User... users);

    @Query("SELECT * FROM User")
    public User[] loadAllUsers();

//    @Query("SELECT * FROM User WHERE firstName LIKE :searchName" +
//            "OR lastName LIKE :searchName")
//    public List<User> findUserWithName(String searchName);

    @Query("SELECT * FROM User")
    public Flowable<List<User>> loadAllUsersWithRx();
}
