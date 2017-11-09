package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.pf.bogdan.pharmacy.model.Medicine;
import com.pf.bogdan.pharmacy.repository.MedicineListAdapter;
import com.pf.bogdan.pharmacy.repository.MedicineRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MedicineRepository medicineRepository = new MedicineRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createList();
        medicineSelected();
        Button button = (Button) findViewById(R.id.orderMedicineButton);
        button.setOnClickListener((v) ->{
            Intent intent = new Intent(MainActivity.this, OrderMedicineActivity.class);
            startActivity(intent);
        });
    }

    public static MedicineRepository getRepository(){
        return medicineRepository;
    }

    private void createList(){
        //ListAdapter adapter = new ArrayAdapter<>(this, R.layout.medicine_list_item, medicineRepository.getMedicines());
        MedicineListAdapter adapter = new MedicineListAdapter(this,R.layout.medicine_list_item,medicineRepository.getMedicines());
        ListView listView = (ListView) findViewById(R.id.medicineListView);
        listView.setAdapter(adapter);
    }

    public void medicineSelected(){
        ListView listView = (ListView) findViewById(R.id.medicineListView);
        final List<Medicine> medicines = medicineRepository.getMedicines();
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,MedicineDetailsActivity.class);
                intent.putExtra("medicine",medicines.get(position));
                intent.putExtra("repository",medicineRepository);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(listener);

    }
}
