package me.winter.mytrainingapp.examples.somes;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Title extends ExpandableGroup<SubTitle> {
//    private String imageUrl;
    private int imageUrl;

    public Title(String title, List<SubTitle> items, /*String imageUrl*/ int imageUrl) {
        super(title, items);
        this.imageUrl = imageUrl;
    }

//    public String getImageUrl() {
//        return imageUrl;
//    }

    public int getImageUrl() {
        return imageUrl;
    }
}
