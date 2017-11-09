package com.pf.bogdan.pharmacy.repository;

import com.pf.bogdan.pharmacy.model.Medicine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class MedicineRepository implements Serializable{
    private List<Medicine> medicines;

    public MedicineRepository() {
        medicines = new ArrayList<>();
        populateRepository();
    }

    private void populateRepository(){
        Medicine medicine1 = new Medicine(1,"Asipirina","Solves Head Aches","Luforen",7.50);
        Medicine medicine2 = new Medicine(2,"Nurofen","Flu Fixer","Pull Pharma",10.50);
        Medicine medicine3 = new Medicine(3,"Coldrex","Flu Helper","Mararam",9.00);
        Medicine medicine4 = new Medicine(4,"C Vitamin","Imunity Help","Laro Pharm",2.50);
        Medicine medicine5 = new Medicine(5,"Strepsils","Neck Aches","Omega Pharma",5.00);
        medicines.add(medicine1);
        medicines.add(medicine2);
        medicines.add(medicine3);
        medicines.add(medicine4);
        medicines.add(medicine5);
    }

    public int findOneById(Integer id){
        for(int i=0; i< medicines.size();i++)
            if(medicines.get(i).getId().equals(id))
                return i;
        return -1;
    }

    public int findOneByName(String name){
        for(int i=0; i< medicines.size();i++)
            if(medicines.get(i).getName().equals(name))
                return i;
        return -1;
    }

    public void add(Medicine medicine){
        medicines.add(medicine);
    }

    public void remove(Medicine medicine){
        medicines.remove(medicine);
    }

    public void update(String name, String description,String producer, Double price){
        int index = findOneByName(name);
        medicines.get(index).setDescription(description);
        medicines.get(index).setProducer(producer);
        medicines.get(index).setPrice(price);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
