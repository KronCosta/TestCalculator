package converter;

import java.util.TreeMap;

public class NumConverter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>(); // ключ - римкие цифры
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>(); // ключ - арабские цифры


    public NumConverter() {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);


        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");
    }

    public boolean isRoman(String number) {
        //"V"->'V'
        return romanKeyMap.containsKey(number.charAt(0));
    }

    //X -> 10
    public Integer romanToArabian(String str) {

        int end = str.length() - 1; //
        char[] arr = str.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKeyMap.get(arr[i]);

            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;

    }


    // 10 -> X
    public String arabianToRoman(int num) {
        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(num); //метод возвращает ближайшее меньшее или равное значение
            roman += arabianKeyMap.get(arabianKey);
            num -= arabianKey; // вычитаем найденное значение до тех пор пока значение не станет равно нулю
        } while (num != 0);
        return roman;
    }
}
