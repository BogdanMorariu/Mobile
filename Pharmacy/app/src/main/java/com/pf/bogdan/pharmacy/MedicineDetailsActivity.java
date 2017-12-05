package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.pf.bogdan.pharmacy.model.Medicine;

import java.util.ArrayList;
import java.util.List;
public class MedicineDetailsActivity extends AppCompatActivity {

    private Medicine medicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_details);

        medicine = (Medicine) getIntent().getSerializableExtra("medicine");

        displayDetails(medicine);
        if(!medicine.isEmpty())
            createChart();
    }

    private void displayDetails(Medicine medicine){
        TextView nameView = findViewById(R.id.medicineName);
        TextView descriptionView = findViewById(R.id.medicineDescription);
        TextView producerView = findViewById(R.id.medicineProducer);
        TextView priceView = findViewById(R.id.medicinePrice);
        nameView.setText(medicine.getName());
        descriptionView.setText(medicine.getDescription());
        producerView.setText(medicine.getProducer());
        priceView.setText(String.valueOf(medicine.getPrice()));
    }

    private void createChart(){
        /*LineChart chart = new LineChart(getApplicationContext());
        RelativeLayout relativeLayout = findViewById(R.id.medicineChart);
        relativeLayout.addView(chart);*/
        LineChart chart = findViewById(R.id.medicineChart);
        Description description = new Description();
        description.setText("A Chart Presenting the evolution of the medicine's price");
        chart.setDescription(description);

        int x = 0;
        List<Double> data = medicine.getPrices();
        List<Entry> entries = new ArrayList<>();
        for(Double p : data)
            entries.add(new Entry(x++,p.floatValue()));
        LineDataSet dataSet = new LineDataSet(entries, medicine.getName());
        dataSet.setColor(R.color.colorPrimaryDark);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
    }

    public void save(View view){
        TextView nameView = findViewById(R.id.medicineName);
        TextView descriptionView = findViewById(R.id.medicineDescription);
        TextView producerView = findViewById(R.id.medicineProducer);
        TextView priceView = findViewById(R.id.medicinePrice);
        medicine.setName(nameView.getText().toString());
        medicine.setDescription(descriptionView.getText().toString());
        medicine.setProducer(producerView.getText().toString());
        medicine.setPrice(Double.parseDouble(priceView.getText().toString()));
        Intent intent = new Intent(MedicineDetailsActivity.this,MainActivity.class);
        intent.putExtra("medicine",medicine);
        setResult(RESULT_OK,intent);
        finish();
    }
}
