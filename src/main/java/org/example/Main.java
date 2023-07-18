package org.example;
// TODO: 10.07.2023 проверка на соответствие условиям задания // проверка наличия операнда // проверка соответствия чисел //


import converter.NumConverter;

import java.util.Scanner;

public class Main {
    private static final String welcomeMessage = "Hello! This is a test calculator designed by QMIS";
    private static final String startMessage = "Введите выражение";
    private static final String exceptionMessage1 = "Отрицательное значение недопустимо в римских числах";
    private static final String exceptionMessage2 = "Строка не является математической операцией";
    private static final String exceptionMessage3 = "Недопустимый оператор";
    private static final String exceptionMessage5 = "Невозможно использовать разные системы счисления вместе";
    private static final String exceptionMessage4 = "Несоответсвие по ТЗ (ввод более 2 операнда / 1 оператор или значение операнда больше 10)";
    private static final String[] regexAct = {"\\+", "-", "\\*", "/"}; // для метода split
    private static final String[] act = {"+", "-", "*", "/"};
    private static final NumConverter converter = new NumConverter();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(welcomeMessage);
        System.out.println(startMessage);
        String exp = scan.next().replace(" ", ""); //Получаем выражение и для удобства убираем пробелы
        System.out.println(calc(exp));
    }

    private static String calc(String exp) {
        //проверка наличия оператора в стоке
        int result;
        int actionIndex = -1;
        for (int i = 0; i < act.length; i++) {
            if (exp.contains(act[i])) {
                int i1 = exp.indexOf(act[i]);
                int i2 = exp.lastIndexOf(act[i]);
                if (i1 >= 0) {
                    if (i1 != i2) {
                        throw new IllegalArgumentException(exceptionMessage4);
                    }
                }
                actionIndex = i;
                break;
            }
        }
        //является ли оператор допустимым
        if (actionIndex == -1) {
            throw new IllegalArgumentException(exceptionMessage3);
        }
        //разбиваем выражение на части
        String[] data = exp.split(regexAct[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToArabian(data[0]);
                b = converter.romanToArabian(data[1]);

            } else {

                //конвертируем арабские числа из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }

            if (a < 11 & b < 11) {// проверяем одно из условий
                // считаем
                switch (act[actionIndex]) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = a - b;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }

                if (isRoman) {
                    if (result >= 0) {
                        // если числа были арабские, возвращаем результат в арабском числе
                        return String.valueOf(converter.arabianToRoman(result));
                    } else {
                        throw new IllegalArgumentException(exceptionMessage1);
                    }
                }
            } else {
                throw new IllegalArgumentException(exceptionMessage4);
            }
        } else {
            throw new IllegalArgumentException(exceptionMessage5);
        }
        return String.valueOf(result);
    }
}
