package com.english.myapp;

public class Unit {
    public String title;
    public int imageId;

    private Unit(String title, int imageId){
        this.title = title;
        this.imageId = imageId;
    }

    public static final Unit[] units = {
            new Unit("Пролог", R.drawable.toolbg),
            new Unit("Путешествие во времени" , R.drawable.timetravel),
    };

}
