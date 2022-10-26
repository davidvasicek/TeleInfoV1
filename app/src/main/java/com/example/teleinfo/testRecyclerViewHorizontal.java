package com.example.teleinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class testRecyclerViewHorizontal extends AppCompatActivity {

    RecyclerView recyclerView;

    // Array list for recycler view data source
    ArrayList<String> source;

    // Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;

    // adapter class object
    Adapter adapter;

    // Linear Layout Manager
    LinearLayoutManager HorizontalLayout;

    View ChildView;
    int RecyclerViewItemPosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_test_recycler_view_horizontal);
        // initialisation with id's
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());

        // Set LayoutManager on Recycler View
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        // Adding items to RecyclerView.
        AddItemsToRecyclerViewArrayList();

        // calling constructor of adapter
        // with source list as a parameter
        adapter = new Adapter(source);

        // Set Horizontal Layout Manager
        // for Recycler view
        HorizontalLayout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(HorizontalLayout);

        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);
    }

    // Function to add items in RecyclerView.
    public void AddItemsToRecyclerViewArrayList()
    {
        // Adding items to ArrayList
        source = new ArrayList<>();
        source.add("11");
        source.add("25.10 - 2.11.");
        source.add("13");

    }

    public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

        // List with String type
        private List<String> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public class MyView extends RecyclerView.ViewHolder {

            // Text View
            TextView textView;

            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            public MyView(View view){
                super(view);

                // initialise TextView with id
                textView = (TextView)view.findViewById(R.id.textview);
            }
        }

        // Constructor for adapter class
        // which takes a list of String type
        public Adapter(List<String> horizontalList)
        {
            this.list = horizontalList;
        }

        // Override onCreateViewHolder which deals
        // with the inflation of the card layout
        // as an item for the RecyclerView.
        @Override
        public MyView onCreateViewHolder(ViewGroup parent, int viewType)
        {

            // Inflate item.xml using LayoutInflator
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_activity_test_recycler_view_horizontal_item, parent, false);

            // return itemView
            return new MyView(itemView);
        }



        // Override onBindViewHolder which deals
        // with the setting of different data
        // and methods related to clicks on
        // particular items of the RecyclerView.
        @Override
        public void onBindViewHolder(final MyView holder, final int position)
        {

            // Set the text of each item of
            // Recycler view with the list items
            holder.textView.setText(list.get(position));
        }

        // Override getItemCount which Returns
        // the length of the RecyclerView.
        @Override
        public int getItemCount()
        {
            return list.size();
        }
    }



}