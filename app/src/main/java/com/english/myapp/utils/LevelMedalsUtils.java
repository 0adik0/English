package com.english.myapp.utils;

import java.util.ArrayList;
import java.util.List;

public class LevelMedalsUtils {

    public static final int GOLD = 1;
    public static final int SILVER = 2;
    public static final int BRONZE = 3;
    public static final int NONE = 4;

    private static final List<Integer> subjectLevel1List = new ArrayList<Integer>() {{
        add(40);
        add(50);
        add(55);
    }};

    private static final List<Integer> subjectLevel2List = new ArrayList<Integer>() {{
        add(50);
        add(65);
        add(75);
    }};

    private static final List<Integer> subjectLevel3List = new ArrayList<Integer>() {{
        add(60);
        add(75);
        add(90);
    }};

    private static final List<Integer> subjectLevel4List = new ArrayList<Integer>() {{
        add(30);
        add(40);
        add(50);
    }};

    public static List<List<Integer>> allLevelsList = new ArrayList<List<Integer>>() {{
        add(subjectLevel1List);
        add(subjectLevel2List);
        add(subjectLevel3List);
        add(subjectLevel4List);
    }};


    public static int computeResult(int seconds, int level) {

        List<Integer> levelList = allLevelsList.get(level-1);

        if (seconds > 0 && seconds <= levelList.get(0)) {
            return GOLD;
        } else if (seconds > levelList.get(0) && seconds <= levelList.get(1)) {
            return SILVER;
        } else if (seconds > levelList.get(1) && seconds <= levelList.get(2)){
            return BRONZE;
        } else {
            return NONE;
        }
    }

    public static List<List<Integer>> getAllLevelsList() {
        return allLevelsList;
    }
}
