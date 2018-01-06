package com.pf.bogdan.pharmacy.repository;

import android.content.Context;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pf.bogdan.pharmacy.model.Medicine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class MedicineRepository{
    private Map<String,Medicine> medicines;
    private int lastID;

    public MedicineRepository(Context context) {
        medicines = new HashMap<>();
        lastID = 1;
    }

    public Medicine findOneById(Integer id){
        for(Medicine medicine : medicines.values())
            if(medicine.getId().equals(id))
                return medicine;
        return null;
    }

    public void add(String key,Medicine medicine){
        if(medicine.getId()>lastID)
            lastID=medicine.getId();
        medicines.put(key,medicine);
    }

    public void update(Medicine medicine){
        Medicine oldMedicine = findOneById(medicine.getId());
        oldMedicine.setName(medicine.getName());
        oldMedicine.setDescription(medicine.getDescription());
        oldMedicine.setProducer(medicine.getProducer());
        oldMedicine.setPrice(medicine.getPrice());
    }

    public List<Medicine> getMedicines() {
        return new ArrayList<>(medicines.values());
    }

    public void remove(Medicine medicine){
        Iterator<Map.Entry<String,Medicine>> it;
        it = medicines.entrySet().iterator();
        Map.Entry<String,Medicine> entry;
        while (it.hasNext()){
            entry = it.next();
            if(entry.getValue().getId().equals(medicine.getId()))
                medicines.remove(entry.getKey());
        }
    }

    public void remove(Integer id){
        medicines.values().remove(findOneById(id));
    }

    public Integer getLastID(){
        return ++lastID;
    }

    public String getKey(Medicine medicine){
        for(Map.Entry<String,Medicine> entry : medicines.entrySet())
            if(entry.getValue().getId().equals(medicine.getId()))
                return entry.getKey();
        return null;
    }

    public void clear(){
        medicines.clear();
    }

}
