package Hwk3;

import java.util.HashMap;


public class PhoneBook {
    public HashMap<String, Person> humanList;

    PhoneBook(){
        this.humanList = new HashMap<>();
    }
    //добавить контакт
    public void addContactToBook(String family, String[] tel, String mail){
        Person p = new Person(tel, mail);
        this.humanList.put(family, p);
    }
    //удалить контакт
    public void delFromBook(String family){
        this.humanList.remove(family);
    }
    //узнать телефон
    public void searchTelInBook(String family){
        if(this.humanList.containsKey(family)){
            System.out.println(family);
            (this.humanList.get(family)).showTelList();
        }
        else System.out.println("Contact " + family + " is absent!");
    }
    //добавить телефон
    public void addTelInBook(String family, String tel){
        if(this.humanList.containsKey(family)){
            (this.humanList.get(family)).writeTel(tel);
        }
        else System.out.println("Contact " + family + " is absent!");
    }
    //узнать почту
    public void searchPostInBook(String family){
        if(this.humanList.containsKey(family)){
            System.out.println(family);
            (this.humanList.get(family)).showPost();
        }
        else System.out.println("Contact " + family + " is absent!");
    }
}
