package Hwk3;

import java.util.*;

public class Person {
    //вместо ArrayList лучше применять HashSet для автоматического исключения дубликатов
    private HashSet<String> telList;  //phone
    private String post;              //e-mail

    Person(){
        this.telList = new HashSet<>();
    }
    Person(String[] tel, String mail){
        this.telList = new HashSet<>(Arrays.asList(tel));
        this.post = mail;
    }

    public void writeTel(String newTel) {
        telList.add(newTel);
    }

    public void showTelList() {
        System.out.println(this.telList);
    }

    public void showPost() {
        System.out.println(this.post);
    }
}
