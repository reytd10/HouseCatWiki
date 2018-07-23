package awa.animalw.Tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import awa.animalw.R;

/**
 * Created by ROG on 16/05/2018.
 */

public class SearchListAdapter extends BaseAdapter {
// Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<String>catlist;
    private ArrayList<String>holdList = new ArrayList<>();

    public SearchListAdapter(Context context, ArrayList<String> catList) {
        mContext = context;
        this.catlist= catList;
        inflater = LayoutInflater.from(mContext);
        holdList.addAll(catList);
        this.catlist.clear();
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return catlist.size();
    }

    @Override
    public String getItem(int id){
        return catlist.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.cat_view_item, null);
            holder.name = (TextView) view.findViewById(R.id.catName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        if(!catlist.isEmpty()){
          holder.name.setText(catlist.get(position));
        }
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        catlist.clear();
        if (charText.length() == 0) {
           // catlist.addAll(holdList);
        } else {
            for (String wp : holdList) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    catlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


}
