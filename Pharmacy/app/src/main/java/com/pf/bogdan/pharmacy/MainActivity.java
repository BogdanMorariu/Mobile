package com.pf.bogdan.pharmacy;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.pf.bogdan.pharmacy.model.Medicine;
import com.pf.bogdan.pharmacy.repository.MedicineListAdapter;
import com.pf.bogdan.pharmacy.repository.MedicineRepository;

public class MainActivity extends AppCompatActivity {

    private MedicineRepository medicineRepository;
    private MedicineListAdapter medicineListAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        medicineRepository = new MedicineRepository(getApplicationContext());
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.medicineListView);

        createList();
        medicineSelected();


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

    private void createList(){
        //ListAdapter adapter = new ArrayAdapter<>(this, R.layout.medicine_list_item, medicineRepository.getMedicines());
        medicineListAdapter = new MedicineListAdapter(this,R.layout.medicine_list_item,medicineRepository.getMedicines());
        ListView listView = findViewById(R.id.medicineListView);
        listView.setAdapter(medicineListAdapter);
        //for(Medicine medicine : medicineRepository.getMedicines())
        //    System.out.println(medicine.toString());
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
                    medicineRepository.update(medicine);
                    medicineListAdapter.updateListView(medicineRepository.getMedicines());
                    break;
                }
            }
            case 2:{
                if(resultCode == -1){
                    Medicine medicine = (Medicine) data.getSerializableExtra("medicine");
                    medicineRepository.add(medicine);
                    medicineListAdapter.updateListView(medicineRepository.getMedicines());
                    break;
                }
            }
        }
    }

    /*@SuppressLint("ShowToast")
    public void deleteMedicine(View view){
        AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
        View myView = getLayoutInflater().inflate(R.layout.remove_dialog,null);
        Button okButton = myView.findViewById(R.id.removeOkButton);
        Button cancelButton = myView.findViewById(R.id.removeCancelButton);

        okButton.setOnClickListener((v) ->{
            final int position = listView.getPositionForView((View) view.getParent());
            Medicine medicine = new Medicine();
            int id = medicineRepository.getMedicines().get(position).getId();
            medicine.setId(id);
            medicineRepository.remove(medicine);
            medicineListAdapter.updateListView(medicineRepository.getMedicines());
        });
        cancelButton.setOnClickListener((v) -> Toast.makeText(MainActivity.this,"Remove canceled",Toast.LENGTH_SHORT).show());

        myBuilder.setView(myView);
        AlertDialog dialog = myBuilder.create();
        dialog.show();
    }*/
    public void deleteMedicine(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to delete the selected Medicine ?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    final int position = listView.getPositionForView((View) view.getParent());
                    Medicine medicine = new Medicine();
                    int idd = medicineRepository.getMedicines().get(position).getId();
                    medicine.setId(idd);
                    medicineRepository.remove(medicine);
                    medicineListAdapter.updateListView(medicineRepository.getMedicines());
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
