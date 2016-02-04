package com.parse.starter.Login.Flashcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vamnoize on 3/9/2558.
 */
public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder> {

    private List<SingleItem> itemsFront;
    private List<SingleItem> itemsBack;

    private LayoutInflater inflater;
    private Context context;


    private Boolean haveText = false;
    private Boolean haveImage = false;
    List<setItem> data = Collections.emptyList();

    private int focusedItem ;

    public Adapters(Context context,List<setItem> datalist) {
        Log.d("check","OKEY!!");
        //super();
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data= datalist;

    //   final ParseHelp parseHelp = new ParseHelp();

        List<setItem> List = datalist;
        itemsFront = new ArrayList<>();
        itemsBack = new ArrayList<>();
        Log.d("check lise.size", String.valueOf(List.size()));
        for (int i = 0 ; i < List.size();i++ ) {
            Log.d("vamvamtest", "Loop Ok");
            setItem word = List.get(i);
            word.getItemID();
            String catName = word.getItemCatName();
            String wordFront =  word.getItemWordFront();
            String pathImgFront = word.getItemPathImgFront();
            ParseFile imgFileF = word.getItemImgFileF();

            String wordBack = word.getItemWordBack() ;
            String pathImgBack = word.getItemPathImgBack();
            ParseFile imgFileB = word.getItemImgFileB();

            itemsFront.add(new SingleItem(wordFront, imgFileF, pathImgFront));
            itemsBack.add(new SingleItem(wordBack,imgFileB,pathImgBack));

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        Log.d("check","OKEY!!");

        view = inflater.inflate(R.layout.activity_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final SingleItem itemFront = itemsFront.get(position);

       // holder.title.setText(itemFront.getTitle());
        holder.name.setVisibility(View.GONE);
        holder.Image.setVisibility(View.GONE);
        if (itemFront.getName() != null) {
            holder.name.setVisibility(View.VISIBLE);
            holder.name.setText(itemFront.getName());
        }
        if (itemFront.getPathImgFront() != null) {
            holder.Image.setVisibility(View.VISIBLE);
            Bitmap bm = BitmapFactory.decodeFile(itemFront.getPathImgFront());
            holder.Image.setImageBitmap(bm);
        }
//        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {

        return  itemsFront.size();
    }
    @Override
    public int getItemViewType(int position) {
        final SingleItem itemF = itemsFront.get(position);
        final SingleItem itemB = itemsBack.get(position);
        return 0;
    }


     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnLongClickListener {

        TextView name;
        ImageView Image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tvName);
            Image = (ImageView) itemView.findViewById(R.id.ivImg);
            Log.d("check have",haveImage+" "+haveText);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            focusedItem = 0;

            final Handler handler = new Handler();
            final SingleItem itemBack = itemsBack.get(position);
            if (itemBack.getName() != null) {
                name.setVisibility(View.VISIBLE);
                name.setText(itemBack.getName());
            }
            if (itemBack.getPathImgFront() != null) {
                Image.setVisibility(View.VISIBLE);
                Bitmap bm = BitmapFactory.decodeFile(itemBack.getPathImgFront());
                Image.setImageBitmap(bm);
            }

            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    notifyItemChanged(focusedItem);
                    Log.d("CLICK", "Item Run " + getPosition());

                }
            }, 500);
            focusedItem = getLayoutPosition();


            Log.d("check", "Item Run . . .  " + focusedItem);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    private void setHave(){
        haveImage = false;
        haveText = false;
    }
}
