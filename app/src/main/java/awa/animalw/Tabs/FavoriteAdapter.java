package awa.animalw.Tabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import awa.animalw.MainActivity;
import awa.animalw.R;

/**
 * Created by ROG on 27/06/2018.
 */

public class FavoriteAdapter extends BaseAdapter {

    //Variables
    Context mContext;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    private ArrayList<String> favlist;




    public FavoriteAdapter(Context context, ArrayList<String> catList) {
        mContext = context;
        this.favlist = catList;
        inflater = LayoutInflater.from(mContext);
         imageLoader= ImageLoader.getInstance();
    }

    public class ViewHolder {
        TextView name;
        ImageView image;
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
            view = inflater.inflate(R.layout.fav_view_item, viewGroup,false);
            holder.name = (TextView) view.findViewById(R.id.favName);
            holder.image= (ImageView) view.findViewById(R.id.thumbnail);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        if(!favlist.isEmpty()){
            holder.name.setText(favlist.get(position));
            String imageName = favlist.get(position).toLowerCase();
            imageName +=1;
            imageName = imageName.replaceAll("\\s+","");
            if(imageName.contains("-")){
                imageName= imageName.replaceAll("-","_");
            }
            if(imageName.contains("/")){
                imageName= imageName.replaceAll("/","_");
            }
            if(imageName.contains(",")){
                imageName= imageName.replaceAll(",","_");
            }
            //using Image Loader to load thumbnails image
            //As opposed to using traditional method, using Image Loader allowed caching.
            //And faster loading -> enhance user exp
            String imgURL= "drawable://"+ mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
            imageLoader.displayImage(imgURL,holder.image);
        }
        return view;
    }
}
