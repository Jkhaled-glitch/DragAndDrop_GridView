package com.example.draganddrop.Adapter;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.draganddrop.Helper.ItemTouchHelperAdapter;
import com.example.draganddrop.Helper.OnStartDragListener;
import com.example.draganddrop.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
implements ItemTouchHelperAdapter {

    Context context;
    List<Integer> imagesList;
    OnStartDragListener listener;

    public MyRecyclerAdapter(Context context, List<Integer> imagesList, OnStartDragListener listener) {
        this.context = context;
        this.imagesList = imagesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageCell.setImageResource(imagesList.get(position)) ;

        holder.item.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                if(action==MotionEvent.ACTION_DOWN)
                    listener.onStartDrag(holder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(imagesList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);

        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        imagesList.remove(position);
     notifyItemRemoved(position);
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

    @BindView(R.id.imageCell)   ImageView imageCell;
    @BindView(R.id.item) CardView item;
    Unbinder unbinder;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);
        unbinder = ButterKnife.bind(this,itemView);
    }

}
}
