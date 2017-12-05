package com.pf.bogdan.pharmacy.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by bogdan on 12/5/2017.
 */

@Dao
public interface MedicineDAO {
    @Query("Select * from medicine")
    List<Medicine> getAll();

    @Query("Select Count(*) from medicine")
    int count();

    @Query("Select * From medicine where id=:id")
    Medicine findById(int id);

    @Update
    void update(Medicine medicine);

    @Insert
    void insert(Medicine medicine);

    @Delete
    void delete(Medicine medicine);

    @Query("Delete From medicine")
    void deleteAll();

}
