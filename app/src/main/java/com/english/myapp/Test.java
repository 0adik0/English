package com.english.myapp;

public class Test {
    public String title;
    public int iconId;

    private Test(String title, int iconId){
        this.title = title;
        this.iconId = iconId;
    }

    public static final Test[] tests = {
            new Test("Задание 1: Найди своих друзей", R.drawable.test_icon),
            new Test("Задание 2: Каждому свое место" , R.drawable.test_icon),
            new Test("Задание 3: Можешь меня подменить?", R.drawable.test_icon),
            new Test("Задание 4: А ты понял, о чем идет речь?", R.drawable.test_icon)
    };
}
