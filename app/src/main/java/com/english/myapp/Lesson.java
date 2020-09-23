package com.english.myapp;

public class Lesson {
    public String title;
    public int iconId;

    private Lesson(String title, int iconId){
        this.title = title;
        this.iconId = iconId;
    }

    public static final Lesson[] lessons = {
            new Lesson("Lesson 1: Кто все эти люди? Подлежащее. Какие части речи могут им быть", R.drawable.lessonicon),
            new Lesson("Lesson 2: Все мы чем-то занимаемся. Сказуемое. Какой глагол тут нужен?" , R.drawable.lessonicon),
            new Lesson("Lesson 3: Кто последний тот и … Порядок слов в английском языке ", R.drawable.lessonicon)
    };

}
