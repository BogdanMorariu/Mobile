package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pf.bogdan.pharmacy.model.Medicine;
import com.pf.bogdan.pharmacy.repository.MedicineRepository;

public class MedicineDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        Medicine medicine = (Medicine) getIntent().getSerializableExtra("medicine");
        displayDetails(medicine);
    }

    private void displayDetails(Medicine medicine){
        TextView nameView = (TextView) findViewById(R.id.medicineName);
        TextView descriptionView = (TextView) findViewById(R.id.medicineDescription);
        TextView producerView = (TextView) findViewById(R.id.medicineProducer);
        TextView priceView = (TextView) findViewById(R.id.medicinePrice);
        nameView.setText(medicine.getName());
        descriptionView.setText(medicine.getDescription());
        producerView.setText(medicine.getProducer());
        priceView.setText(String.valueOf(medicine.getPrice()));
    }

    public void save(View view){
        TextView nameView = (TextView) findViewById(R.id.medicineName);
        TextView descriptionView = (TextView) findViewById(R.id.medicineDescription);
        TextView producerView = (TextView) findViewById(R.id.medicineProducer);
        TextView priceView = (TextView) findViewById(R.id.medicinePrice);
        String name = nameView.getText().toString();
        String description = descriptionView.getText().toString();
        String producer = producerView.getText().toString();
        Double price = Double.parseDouble(priceView.getText().toString());
        MainActivity.getRepository().update(name,description,producer,price);
        Intent intent = new Intent(MedicineDetailsActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
