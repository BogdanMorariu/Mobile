package com.pf.bogdan.pharmacy.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.pf.bogdan.pharmacy.DataBaseConnection;
import com.pf.bogdan.pharmacy.model.Medicine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class MedicineRepository{
    private final DataBaseConnection con;

    public MedicineRepository(Context context) {
        con = Room.databaseBuilder(context, DataBaseConnection.class, "pharmacy-database")
                .allowMainThreadQueries().build();
        //populateRepository();
    }

    private void populateRepository(){
        Medicine medicine1 = new Medicine(1,"Asipirina","Solves Head Aches","Luforen",7.50);
        Medicine medicine2 = new Medicine(2,"Nurofen","Flu Fixer","Pull Pharma",10.50);
        Medicine medicine3 = new Medicine(3,"Coldrex","Flu Helper","Mararam",9.00);
        Medicine medicine4 = new Medicine(4,"C Vitamin","Imunity Help","Laro Pharm",2.50);
        Medicine medicine5 = new Medicine(5,"Strepsils","Neck Aches","Omega Pharma",5.00);
        con.medicineDAO().deleteAll();
        con.medicineDAO().insert(medicine1);
        con.medicineDAO().insert(medicine2);
        con.medicineDAO().insert(medicine3);
        con.medicineDAO().insert(medicine4);
        con.medicineDAO().insert(medicine5);

        /*medicines.add(medicine1);
        medicines.add(medicine2);
        medicines.add(medicine3);
        medicines.add(medicine4);
        medicines.add(medicine5);*/
    }

    public Medicine findOneById(Integer id){
        return con.medicineDAO().findById(id);
    }

    public void add(Medicine medicine){
        con.medicineDAO().insert(medicine);
    }

    /*public void add(String name, String description, String producer, Double price){
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setDescription(description);
        medicine.setProducer(producer);
        medicine.setPrice(price);
        con.medicineDAO().insert(medicine);
    }*/

    public void update(Medicine medicine){
        con.medicineDAO().update(medicine);
    }

    public List<Medicine> getMedicines() {
        return con.medicineDAO().getAll();
    }

    public void remove(Medicine medicine){
        con.medicineDAO().delete(medicine);
    }

}
