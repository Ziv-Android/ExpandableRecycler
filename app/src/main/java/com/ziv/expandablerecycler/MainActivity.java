package com.ziv.expandablerecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        GridLayoutManager layout = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(layout);
        List<Entity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Entity group = new Entity(true, String.format("第%02d组", i));
            for (int j = 0; j < 10; j++) {
                group.getChildren().add(new Entity(false, String.format("第%02d组 第%02d个", i, j)));
            }
            list.add(group);
        }
        ExpandableAdapter adapter = new ExpandableAdapter(this, list);
        layout.setSpanSizeLookup(adapter.lookup);
        recycler.setAdapter(adapter);

    }

}
