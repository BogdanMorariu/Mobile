package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pf.bogdan.pharmacy.model.Medicine;
import com.pf.bogdan.pharmacy.repository.MedicineListAdapter;
import com.pf.bogdan.pharmacy.repository.MedicineRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referenceDB;
    private MedicineRepository medicineRepository;
    private MedicineListAdapter medicineListAdapter;
    private ListView listView;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicineRepository = new MedicineRepository(getApplicationContext());
        executor = Executors.newFixedThreadPool(10);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.medicineListView);

        createList();
        medicineSelected();
        populateDB();

        Button orderButton = findViewById(R.id.orderMedicineButton);
        orderButton.setOnClickListener((v) ->{
            Intent intent = new Intent(MainActivity.this, OrderMedicineActivity.class);
            startActivity(intent);
        });

        Button addButton = findViewById(R.id.addMedicineButton);
        addButton.setOnClickListener((v) ->{
            Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class);
            intent.putExtra("medicine", new Medicine());
            startActivityForResult(intent,2);
        });
    }

    private void populateDB(){
        referenceDB.removeValue();
        medicineRepository.clear();
        Medicine medicine1 = new Medicine(1,"Asipirina","Solves Head Aches","Luforen",7.50);
        Medicine medicine2 = new Medicine(2,"Nurofen","Flu Fixer","Pull Pharma",10.50);
        Medicine medicine3 = new Medicine(3,"Coldrex","Flu Helper","Mararam",9.00);
        Medicine medicine4 = new Medicine(4,"C Vitamin","Imunity Help","Laro Pharm",2.50);
        Medicine medicine5 = new Medicine(5,"Strepsils","Neck Aches","Omega Pharma",5.00);
        referenceDB.push().setValue(medicine1);
        referenceDB.push().setValue(medicine2);
        referenceDB.push().setValue(medicine3);
        referenceDB.push().setValue(medicine4);
        referenceDB.push().setValue(medicine5);
    }

    private void createList(){
        medicineListAdapter = new MedicineListAdapter(this,R.layout.medicine_list_item,medicineRepository.getMedicines());
        ListView listView = findViewById(R.id.medicineListView);
        listView.setAdapter(medicineListAdapter);
        //for(Medicine medicine : medicineRepository.getMedicines())
        //    System.out.println(medicine.toString());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        referenceDB = FirebaseDatabase.getInstance().getReference().child("medicine");

        referenceDB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Medicine medicine = dataSnapshot.getValue(Medicine.class);
                medicineRepository.add(dataSnapshot.getKey(),medicine);
                medicineListAdapter.updateListView(medicineRepository.getMedicines());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Medicine medicine = dataSnapshot.getValue(Medicine.class);
                medicineRepository.update(medicine);
                medicineListAdapter.updateListView(medicineRepository.getMedicines());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Medicine medicine = dataSnapshot.getValue(Medicine.class);
                medicineRepository.remove(medicine.getId());
                medicineListAdapter.updateListView(medicineRepository.getMedicines());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void medicineSelected(){
        ListView listView = findViewById(R.id.medicineListView);
        AdapterView.OnItemClickListener listener = (parent, view, position, id) -> {
            //System.out.println("####################"+id+"##############"+position+"##############");
            //System.out.println("-----------"+Integer.toString(position));
            Intent intent = new Intent(MainActivity.this,MedicineDetailsActivity.class);
            intent.putExtra("medicine",medicineRepository.getMedicines().get(position));
            startActivityForResult(intent,1);
        };
        listView.setOnItemClickListener(listener);
    }

    /*
        requestCode = 1 -> Update
        requestCode = 2 -> Add
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case 1:{
                if(resultCode == -1){
                    Medicine medicine = (Medicine) data.getSerializableExtra("medicine");
                    executor.submit((Runnable) () ->
                            referenceDB.child(medicineRepository.getKey(medicine)).setValue(medicine));
                    break;
                }
            }
            case 2:{
                if(resultCode == -1){
                    Medicine medicine = (Medicine) data.getSerializableExtra("medicine");
                    medicine.setId(medicineRepository.getLastID());
                    executor.submit((Runnable) () -> referenceDB.push().setValue(medicine));
                    break;
                }
            }
        }
    }

    public void deleteMedicine(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to delete the selected Medicine ?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    final int position = listView.getPositionForView((View) view.getParent());
                    Medicine medicine = new Medicine();
                    int idd = medicineRepository.getMedicines().get(position).getId();
                    medicine.setId(idd);
                    executor.submit((Runnable) () -> referenceDB.child(medicineRepository.getKey(medicine)).removeValue());
                    //referenceDB.child(medicineRepository.getKey(medicine)).removeValue();
                    Toast.makeText(MainActivity.this,"Remove successful",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    Toast.makeText(MainActivity.this,"Remove canceled",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

        builder.show();
    }
}
