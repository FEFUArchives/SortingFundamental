package net.edwardcode.sorting;

import java.util.Comparator;

public class RussianStringComparator implements Comparator<String> {

    // Определяем правильный порядок русского алфавита
    private static final String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    @Override
    public int compare(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Strings to compare cannot be null");
        }

        int minLength = Math.min(s1.length(), s2.length());

        for (int i = 0; i < minLength; i++) {
            char c1 = Character.toUpperCase(s1.charAt(i));
            char c2 = Character.toUpperCase(s2.charAt(i));

            int pos1 = RUSSIAN_ALPHABET.indexOf(c1);
            int pos2 = RUSSIAN_ALPHABET.indexOf(c2);

            // Если символ не найден, можно либо игнорировать, либо учитывать в особом порядке
            if (pos1 == -1 || pos2 == -1) {
                return 0;
            }

            if (pos1 != pos2) {
                return Integer.compare(pos1, pos2);
            }
        }

        // Если строки равны на общем префиксе, сравниваем их длины
        return Integer.compare(s1.length(), s2.length());
    }
}

