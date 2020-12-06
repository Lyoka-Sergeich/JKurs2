package Hwk3;
//        1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
//        - Найти список слов, из которых состоит текст (дубликаты не считать);
//        - Посчитать сколько раз встречается каждое слово (использовать HashMap);
//        2. Написать простой класс PhoneBook(внутри использовать HashMap):
//        - В качестве ключа использовать фамилию
//        - В каждой записи всего два поля: phone, e-mail
//        - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
//        и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть
//        несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
import java.util.*;

public class MasWord {

    public static void main(String[] args){
        int i;
        int enterLength;
        String sTmp;
        int chPos;
        char t;

        int d1 = (int)'а';
        int d11 = (int)'А';
        int d22 = (int)'Я';
        int deltaChar = d1 - d11;
        int numChar;

        //ЗАДАЧА №1
        //текст для удобства введём в виде строки
        String sBegin =
        "И мы сидим над рекой, а луна, любящая ее не меньше, чем мы, склоняется к ней с нежным лобзанием и заключает" +
        " ее в свои серебристые объятия. И мы смотрим, как струятся неумолчные воды, несущиеся навстречу своему" +
        " повелителю-океану, – смотрим до тех пор, пока не замирает наша болтовня, не гаснут трубки, пока мы, в общем" +
        " довольно заурядные и прозаические молодые люди, не погружаемся в печальные и в то же время отрадные думы," +
        " которым не нужны слова, – пока, неожиданно рассмеявшись, мы не встаем, чтобы выколотить трубки, пожелать друг" +
        " другу доброй ночи, и не засыпаем под безмолвными звездами, убаюканные плеском волн и шелестом листвы. И нам" +
        " снится, что земля стала снова юной-юной и нежной, какой она была до того, как столетия забот и страданий" +
        " избороздили морщинами ее ясное чело, а грехи и безрассудства ее сынов состарили любящее сердце, – нежной, как" +
        " и в те далекие дни, когда она, молодая мать, баюкала нас, своих детей, на могучей груди, когда дешевые" +
        " побрякушки цивилизации не вырвали еще нас из ее объятий, когда человечество еще не было отравлено ядом" +
        " насмешливого скептицизма и не стыдилось простоты своей жизни, простоты и величия обители своей – матери-земли.";

        StringBuilder enter = new StringBuilder(sBegin);  //для изменения текста используем StringBuilder
        //уберём знаки препинания
        while(((chPos = enter.indexOf(".")) != -1)||
                ((chPos = enter.indexOf(",")) != -1)||
                ((chPos = enter.indexOf(":")) != -1)||
                ((chPos = enter.indexOf(";")) != -1)||
                ((chPos = enter.indexOf("!")) != -1)||
                ((chPos = enter.indexOf("?")) != -1)){

            enter.deleteCharAt(chPos);
        }
        while((chPos = enter.indexOf(" –")) != -1){
            enter.delete(chPos, chPos+2);
        }

        //заменим заглавные буквы на строчные
        enterLength = enter.length();
        for(i = 0;i < enterLength;i++){
            if(((numChar = (int)(enter.charAt(i))) >= d11)&&
                    ((numChar = (int)(enter.charAt(i))) <= d22)){

                numChar += deltaChar;
                enter.setCharAt(i,(char)(numChar));
            }
        }
        //переведём в String для лёгкого разбиения
        sTmp = enter.toString();
        //разобьём
        HashSet<String> wordList = new HashSet<>(Arrays.asList(sTmp.split(" ")));
        //получаем список слов без повторений - свойство HashSet
        System.out.println("List of words");
        System.out.println(wordList);


        //получим набор слов
        ArrayList<String> text = new ArrayList<>(Arrays.asList(sTmp.split(" ")));
        HashMap<String, Integer> wordCount = new HashMap<>();  //это будет список слов с указанием повторений
        //переберём набор, сверяясь со списком и заполняя его
        for(String st : text){
            if(wordCount.containsKey(st)){  //слово встречается не впервые
                i = wordCount.get(st);  //возьмём число повторов и увеличим на 1
                i++;
                wordCount.put(st,i);
            }
            else {  //слово встречается впервые
                wordCount.put(st,1);
            }
        }
        //получаем список слов с указанием повторений
        System.out.println("List of words with counts");
        System.out.println(wordCount);



        //ЗАДАЧА №2
        //создадим телефонную книгу
        PhoneBook myBook = new PhoneBook();
        //добавим контакты
        String[] stt = {"1234","567890"};
        myBook.addContactToBook("Roger",stt,"fbngj.gmail.com");
        stt = new String[]{"3748", "807499"};
        myBook.addContactToBook("Katy",stt,"fmurcej.gmail.com");
        stt = new String[]{"5708", "846295","845603"};
        myBook.addContactToBook("Katy",stt,"nfkrhaew.gmail.com");
        //выведем телефоны
        myBook.searchTelInBook("Roger");
        //выведем почту
        myBook.searchPostInBook("Roger");
        //выведем телефоны (при отсутствии контакта)
        myBook.searchTelInBook("Jack");
        myBook.searchPostInBook("Jill");

        //ГОТОВО

    }

}
