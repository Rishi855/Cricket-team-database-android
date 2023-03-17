package com.example.dbe1.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dbe1.R;

import java.util.ArrayList;

public class CustomViewForPerson extends BaseAdapter {

    Context context;

    ArrayList<String> Listname;
    ArrayList<Integer> Listimage;
    ArrayList<Integer> Listid;
    LayoutInflater inflater;
    public CustomViewForPerson(Context ctx,  ArrayList<Integer> image,ArrayList<String> name,ArrayList<Integer>id)
    {
        this.context = ctx;
        this.Listimage = image;
        this.Listname = name;
        this.Listid = id;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return Listname.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.custom_list_view_person,null);
        ImageView Pimg = view.findViewById(R.id.person_image);
        TextView Pname = view.findViewById(R.id.person_name);
        TextView Pid = view.findViewById(R.id.person_id);
        Pname.setText("Name: "+Listname.get(i));
        Pimg.setImageResource(Listimage.get(i));
        Pid.setText("ID: "+Listid.get(i)+"");
        return view;
    }
}
