package com.pf.bogdan.pharmacy.repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pf.bogdan.pharmacy.R;
import com.pf.bogdan.pharmacy.model.Medicine;

import java.util.List;

/**
 * Created by Bogdan on 07.11.2017.
 */

public class MedicineListAdapter extends ArrayAdapter<Medicine> {

    private Activity activity;
    private List<Medicine> medicines;
    private static LayoutInflater inflater = null;

    public MedicineListAdapter(Activity activity, int resource, List<Medicine> medicineList){
        super(activity,resource,medicineList);
        try{
            this.activity = activity;
            this.medicines = medicineList;
            inflater = activity.getLayoutInflater();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder{
        TextView display_name;
        TextView display_price;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        View view = convertView;
        final ViewHolder holder;
        try{
            if(convertView == null) {
                view = inflater.inflate(R.layout.medicine_list_item, null);
                holder = new ViewHolder();

                holder.display_name = (TextView) view.findViewById(R.id.medicineName);
                holder.display_price = (TextView) view.findViewById(R.id.medicinePrice);

                view.setTag(holder);
            }else
                holder = (ViewHolder) view.getTag();

            holder.display_name.setText(medicines.get(position).getName());
            holder.display_price.setText(String.valueOf(medicines.get(position).getPrice()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return view;
    }

}
