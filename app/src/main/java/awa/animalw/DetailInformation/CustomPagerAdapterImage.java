package awa.animalw.DetailInformation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import awa.animalw.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by ROG on 15/05/2018.
 */

public class CustomPagerAdapterImage extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    private int id;
    String cName;

    public CustomPagerAdapterImage(Context context, int id, String name) {
        mContext = context;
        this.id = id;
        this.cName= name;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        ArrayList<Drawable> holder = this.getResourcesC(id,cName );
        imageView.setImageDrawable(holder.get(position));
        //Setting zoom feature on picture when click
        final Drawable inflate = holder.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                View zoomView = mLayoutInflater.inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = zoomView.findViewById(R.id.photoView1);
                photoView.setImageDrawable(inflate);
                dialogBuilder.setView(zoomView);
                AlertDialog imageDialog = dialogBuilder.create();
                imageDialog.setCanceledOnTouchOutside(true);
                imageDialog.show();

            }
        });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    //Get Resource according to ID/NAme
    //ID number might be redundant and might not be as precise as an index
    //Name in this case can be used as a better index-er
    //ID is still kept for future usage.
    public ArrayList<Drawable> getResourcesC(int id, String name) {
       String name1 = name.toLowerCase();
       ArrayList<Drawable> resources = new ArrayList<>();
       for(int hold = 1; hold<= 3; hold++){
           name1 += hold;
           name1 = name1.replaceAll("\\s+","");
           if(name1.contains("-")){
               name1= name1.replaceAll("-","_");
           }
           if(name1.contains("/")){
               name1= name1.replaceAll("/","_");
           }
           if(name1.contains(",")){
               name1= name1.replaceAll(",","_");
           }
           Log.d("convertedName",name1);
           Drawable holder = mContext.getResources().getDrawable(mContext.getResources().getIdentifier(name1, "drawable", mContext.getPackageName()));
           resources.add(holder);
           name1 = name.toLowerCase();
       }

       return resources;
   }

}
