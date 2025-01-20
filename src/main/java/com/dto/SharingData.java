package com.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public  class SharingData {
    public static Calculator calculator=new Calculator("",-1);

    public static int getIdLecture() {
        return idLecture;
    }
    public static void setIdLecture(int idLecture) {
        SharingData.idLecture = idLecture;
    }
    private static int idLecture=0;

    private static StringProperty userNameProperty = new SimpleStringProperty();
    public static void setUserName(String userName) {
        userNameProperty.set(userName);
    }
    public static String getUserName() {
        return userNameProperty.get();
    }
    public static StringProperty userNameProperty() {
        return userNameProperty;
    }

    public static Example example=new Example(-1,"","empty.md");
    public static Topic topic=new Topic(-1,"lecture","empty.md");
    public static Task task=new Task(-1,"lecture","empty.md");

    public static String  topicName = new String("");
    public static void setTopic(Topic topic) {
        topic = topic;
    }

    public static Test test=new Test(-1,"","empty.md");


    public static boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        SharingData.isAdmin = isAdmin;
    }

    private static boolean  isAdmin = false;

}
