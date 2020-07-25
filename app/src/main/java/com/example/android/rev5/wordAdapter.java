package com.example.android.rev5;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class wordAdapter extends ArrayAdapter<word>{

    public wordAdapter(@NonNull Context context, @NonNull List objects) {
        super(context,0,objects);
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView=convertView;
        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word wo=getItem(position);
        TextView t1=(TextView)listView.findViewById(R.id.mag);
        String mh=formatMagnitude(wo.getMag());
        t1.setText(mh);
        Date dateobj=new Date(wo.getDat());
        TextView t2=(TextView)listView.findViewById(R.id.da);
        String formattedDate=formatDate(dateobj);
        t2.setText(formattedDate);
        TextView t3=(TextView)listView.findViewById(R.id.ti);
        String formattedTime=formatTime(dateobj);
        t3.setText(formattedTime);
        TextView t4=(TextView)listView.findViewById(R.id.part1);
        TextView t5=(TextView)listView.findViewById(R.id.part2);
        String pa=wo.getPlace();
//        String io="of";
//        String[] po =pa.split(io)
        int ind=pa.indexOf("of");
       String po[]=new String[10];
       po=pa.split("of");
       String part1=po[0];
//       String part2=po[1];
       t4.setText(part1+"of");
      // t5.setText(part2);
       String mo=pa.substring(ind+2,pa.length());
       t5.setText(mo);
        GradientDrawable magnitudeCircle = (GradientDrawable)t1.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(wo.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        Log.e("wordAdapter","strings"+part1+mo);
        return listView;
    }
    String formatDate(Date dateobj){
        SimpleDateFormat smp=new SimpleDateFormat("MMM DD,YYYY");
        String datew=smp.format(dateobj);
        return datew;
    }
   String formatTime(Date dateobj){
        SimpleDateFormat smp=new SimpleDateFormat("HH:MM a");
        String timew=smp.format(dateobj);
        return timew;
   }
   String formatMagnitude(Double k){
       DecimalFormat dd=new DecimalFormat("0.0");
       return dd.format(k);

   }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    }

