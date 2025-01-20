package com.datastore;



import com.dto.Topic;

import java.util.ArrayList;
import java.util.Arrays;

public class LectureArrayList {
    public static ArrayList<Topic> getLectureList() {
        return lectureList;
    }

    private static ArrayList <Topic> lectureList=new ArrayList<Topic>(Arrays.asList(
            new Topic(0, "Введение в теорию игр", "lecture1.md"),
            new Topic(1, "Неравенсво Нэша", "lecture2.md"),
            new Topic(2, "Доминирующие стратегии", "lecture3.md")
    ));
}
