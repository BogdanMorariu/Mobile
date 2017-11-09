package com.pf.bogdan.pharmacy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OrderMedicineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_medicine);
    }

    public void orderMedicine(View view){
        EditText nameText = (EditText) findViewById(R.id.orderMedicineName);
        EditText producerText = (EditText) findViewById(R.id.orderMedicineProducer);
        if(nameText.getText().toString().equals("")){
            Toast.makeText(this,"Plsease enter a Medicine Name",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tipitza@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Medicine");
        intent.putExtra(Intent.EXTRA_TEXT,"Your order for " + nameText.getText().toString() + " by " + producerText.getText().toString() + " has been placed");
        startActivity(Intent.createChooser(intent, "Send mail with"));
    }
}
