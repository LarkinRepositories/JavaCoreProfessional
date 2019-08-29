package Lessons.Lesson_3;

/*
Дополнительно задание
1.Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль; //DONE
2.Последовательно сшить 5 файлов в один (файлы примерно 100 байт). //DONE
Может пригодиться следующая конструкция: List<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
3.Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson_3 {
    public static void main(String[] args) {
        try {
            readFile("123/3/test.txt");
            sewTogether("123/3/test.txt", "123/3/test2.txt", "123/3/test3.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //пункт1
    private static void readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String read;
        while ((read = reader.readLine()) != null) {
            System.out.println(read);
        }
        reader.close();
    }
    //пункт2
    private static void sewTogether(String...filePaths) throws IOException {
        List<InputStream> files = new ArrayList<>();
        for (int i = 0; i < filePaths.length; i++) {
            files.add(new FileInputStream(filePaths[i]));
        }
        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(files));
        int x;
        while ((x = sequenceInputStream.read()) != -1) {
            System.out.print((char) x);
        }
        sequenceInputStream.close();
    }

}
