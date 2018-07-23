package awa.animalw.Tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import awa.animalw.R;

/**
 * Created by ROG on 27/06/2018.
 */

public class FavoriteAdapter extends BaseAdapter {

    //Variables
    Context mContext;
    LayoutInflater inflater;
    private ArrayList<String> favlist;

    public FavoriteAdapter(Context context, ArrayList<String> catList) {
        mContext = context;
        this.favlist = catList;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return favlist.size();
    }

    @Override
    public Object getItem(int id) {
        return favlist.get(id);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.fav_view_item, null);
            holder.name = (TextView) view.findViewById(R.id.favName);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        if(!favlist.isEmpty()){
            holder.name.setText(favlist.get(position));
        }
        return view;
    }
}
