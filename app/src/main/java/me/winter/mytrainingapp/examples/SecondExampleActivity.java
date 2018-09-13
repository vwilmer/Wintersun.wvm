package me.winter.mytrainingapp.examples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.winter.mytrainingapp.R;
import me.winter.mytrainingapp.examples.adapter.Constant;
import me.winter.mytrainingapp.examples.adapter.RecyclerAdapter;
import me.winter.mytrainingapp.examples.somes.SubTitle;
import me.winter.mytrainingapp.examples.somes.Title;

public class SecondExampleActivity extends AppCompatActivity implements RecyclerAdapter.ItemClickChild {

//    String imageUrl[] = Constant.image;
    String names[] = Constant.name;
    String subNames[] = Constant.subName;

    String subFechas[] = Constant.subFecha;
    String subDesc[] = Constant.subDesc;

//    int imageUrl[] = {1, 2};

    int[] imageUrl = new int[3];



    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_example);
//        Log.d("WILMER ", "- " + drawableResourceId);

        initializeResources();

        recyclerView = findViewById(R.id.recyclerViewSecond);

        List<Title> list = getList();
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

    }

    private void initializeResources() {
        int drawableResourceIdLaws = getResources().getIdentifier("ic_laws", "drawable", getPackageName());
        int drawableResourceIdDecretos = getResources().getIdentifier("ic_decretos", "drawable", getPackageName());
        int drawableResourceIdResolutions = getResources().getIdentifier("ic_resoluciones", "drawable", getPackageName());

        imageUrl[0] = drawableResourceIdLaws;
        imageUrl[1] = drawableResourceIdDecretos;
        imageUrl[2] = drawableResourceIdResolutions;
    }

    private List<Title> getList() {
        List<Title> list = new ArrayList<>();
        for (int i = 0; i < imageUrl.length; i++) {
            List<SubTitle> subTitles = new ArrayList<>();
            for (int j = 0; j < subNames.length; j++) {
                SubTitle subTitle = new SubTitle(subNames[j], subFechas[j], subDesc[j]);
                subTitles.add(subTitle);
            }
            Title model = new Title(names[i], subTitles, imageUrl[i]);
            list.add(model);
        }
        return list;
    }

    @Override
    public void onChildClick(int position) {
        String name = subNames[position];

        Toast.makeText(SecondExampleActivity.this, "pepe: " + position + " name: " + name, Toast.LENGTH_SHORT).show();
    }
}
