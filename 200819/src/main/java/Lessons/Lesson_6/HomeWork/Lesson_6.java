package Lessons.Lesson_6.HomeWork;

import java.util.Arrays;

/*
1.Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен
вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
Написать набор тестов для этого метода (по 3-4 варианта входных данных).
Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
2.Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то
метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
[ 1 1 1 4 4 1 4 4 ] -> true
[ 1 1 1 1 1 1 ] -> false
[ 4 4 4 4 ] -> false
[ 1 4 4 1 1 4 3 ] -> false

 */
public class Lesson_6 {

    public Integer[] task1(Integer[] array) {
        if (array.length == 0) System.out.println("Передаваемый массив не должен быть пустым");
        final int NUMBER = 4;
        if (!Arrays.asList(array).contains(NUMBER)) throw new RuntimeException(
                "Переданный массив не содержит цифру " + NUMBER);
        return Arrays.copyOfRange(array, Arrays.asList(array).lastIndexOf(4)+1, array.length);
    }

    public boolean task2(Integer[] array) {
        final int NUMBER = 1;
        final int NUMBER_2 = 4;
        return ( Arrays.asList(array).contains(NUMBER) && Arrays.asList(array).contains(NUMBER_2) );
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {1,1,1,1,4,4,4,7};
        Lesson_6 lesson = new Lesson_6();
        System.out.println(Arrays.toString(lesson.task1(array)));
    }
}
