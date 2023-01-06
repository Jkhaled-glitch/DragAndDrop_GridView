package com.example.draganddrop;

import androidx.annotation.IntegerRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.example.draganddrop.Adapter.MyRecyclerAdapter;
import com.example.draganddrop.Helper.MyItemTouchHelperCallback;
import com.example.draganddrop.Helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

   @BindView(R.id.recycler_view) RecyclerView recyclerView;
   ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        generateItem();
    }
    private void init(){
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
    }



    private void generateItem(){
        //Generate a data images pieces rondomize
        List<Integer> datatmp = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        datatmp.addAll(Arrays.asList(
                (R.drawable.p1),(R.drawable.p2), (R.drawable.p3),
                (R.drawable.p4), (R.drawable.p5),(R.drawable.p6),
                (R.drawable.p7),(R.drawable.p8), (R.drawable.p9)
        ));
        int size = datatmp.size();
        int rand;
        for(int i = 0 ;i<size;i++){
             rand = (int)(  Math.random() * (datatmp.size()));
            data.add(  datatmp.get(rand)  );
            datatmp.remove(rand);
        }


        //Set Adapter
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(this, data, viewHolder ->  {
            itemTouchHelper.startDrag(viewHolder);

        });
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter);
        itemTouchHelper= new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


}