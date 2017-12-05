package com.pf.bogdan.pharmacy;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.pf.bogdan.pharmacy.model.Medicine;
import com.pf.bogdan.pharmacy.model.MedicineDAO;

/**
 * Created by bogdan on 12/5/2017.
 */

@Database(entities = {Medicine.class}, version = 1)
public abstract class DataBaseConnection extends RoomDatabase {

    public abstract MedicineDAO medicineDAO();
}
