package StrFormer;

import java.io.IOException;

public class StrFormer {

/*
1. Есть строка вида: "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0"; (другими словами матрица 4x4)
10 3 1 2
2 3 2 2
5 6 7 1
300 3 1 0
Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];
2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
3. Ваши методы должны бросить исключения в случаях:
Если размер матрицы, полученной из строки, не равен 4x4;
Если в одной из ячеек полученной матрицы не число; (например символ или слово)
4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
*/

    public static void main(String[] args) throws IOException {

        //String source = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";  //правильная матрица
        String source = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0\n300 3 1 0\n2 3 2 2";  //матрица с превышением размера
        //String source = "10 3 1 2 4\n2 3 2 2\n5 6 7 1\n300 3 1 0";  //матрица с превышением размера
        //String source = "10 3 1 t\n2 3 2 2\n5 6 7 1\n300 3 1 0";    //матрица с символом
        String[] masS = {" "," "," "," "};
        int[][] quadrat = new int[4][4];
        int res = 0;


            cutLongString(source, masS);
            res = rewriteStringToInts(masS, quadrat);
            System.out.println(res);

    }
    //используемые методы
    //разбиваем строку на массив
    public static void cutLongString(String s1, String[] s2) throws IOException {
        int i, j = 0, start = 0;

        for (i = 0; i < s1.length(); i++) {  //перебираем строку
            if ((s1.charAt(i) != '\n')) {
            }  //операция '!=' быстрее
                else {
                    if(j > 3)  //Если размер матрицы, полученной из строки, не равен 4x4
                        throw new IOException("Matrix exceeds limit!");

                    s2[j] = s1.substring(start, i);  //выделяем строку с начального номера до конечного, не включая его
                    start = i + 1;                   //переставляем начало новой строки
                    j++;
                }
        }

        if(j > 3)  //Если размер матрицы, полученной из строки, не равен 4x4
            throw new IOException("Matrix exceeds limit!");

        s2[j] = s1.substring(start, i);  //дозабиваем последнюю строку, в конце которой нет '\n'

        //return;
        //throw new IOException("nothing");
    }
    //переводим массив символов в целые числа
    public static int rewriteStringToInts(String[] s, int[][] num) throws IOException {
        int i = 0, j = 0;
        int t, ns, nc = 0;
        char sim;
        int summ = 0;

        for(ns = 0; ns < 4; ns++){  //перебираем строки
            t = 0;  //формируемое число
            j = 0;  //номер элемента массива
            for (nc = 0; nc < s[ns].length(); nc++) {  //перебираем строку
                if(s[ns].charAt(nc) != ' '){  //набираем число, пока не встретим пробел
                    sim = s[ns].charAt(nc);

                    if(Character.getNumericValue(sim) > 9)  //Если размер матрицы, полученной из строки, не равен 4x4
                        throw new IOException("Matrix has symbol inside!");

                    t = t * 10 + Character.getNumericValue(sim);
                }
                else{  //нашли пробел
                    if(j > 3)  //Если размер матрицы, полученной из строки, не равен 4x4
                        throw new IOException("Matrix exceeds limit!");

                    num[ns][j] = t;
                    t = 0;
                    j++;
                }
            }

            if(j > 3)  //Если размер матрицы, полученной из строки, не равен 4x4
                throw new IOException("Matrix exceeds limit!");

            num[ns][j] = t;  //дозабиваем последнее число
        }
        for(i = 0; i < 4; i++)
            for(j = 0; j < 4; j++)
                summ += num[i][j];

        return summ / 2;
    }
}
