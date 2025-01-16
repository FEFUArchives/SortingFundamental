package net.edwardcode.sorting;

import net.edwardcode.sorting.keys.KeyType1;
import net.edwardcode.sorting.keys.KeyType2;

public class Main {
    public static void main(String[] args) {
        KeyType1 testKey1_1 = new KeyType1("Б9125");
        KeyType1 testKey1_2 = new KeyType1("Б9124");
        KeyType2 testKey2_1 = new KeyType2("Ильин Эдуард Алексеевич");
        KeyType2 testKey2_2 = new KeyType2("Ильин Эдуард Алексеевич");

        /*
        -1 - 1 is less than 2
        0 equal
        1 - 1 is bigger then 2
         */
        System.out.println(testKey1_1.compare(testKey1_2));
        /*
        -1 - 1 is less than 2
        0 equal
        1 - 1 is bigger then 2
         */
        System.out.println(testKey2_1.compare(testKey2_2));
    }
}
