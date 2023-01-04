package com.example.draganddrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
        List<String> data = new ArrayList<>();
        data.addAll(Arrays.asList(
                "One","two", "Three",
                "Four","Five", "Sex",
                "Seven","Height", "Nine"
        ));

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