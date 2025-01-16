package net.edwardcode.sorting;

import net.edwardcode.sorting.keys.MainKey;

import java.util.*;

public class DebugUtils {
    public static void printListWithSlash(List<MainKey> list, int slash) {
        if (System.getenv("DEBUG") == null) return;
        for (int i = 0; i < list.size(); i++) {
            if (i == slash) System.out.print("|");
            else if (i != 0) System.out.print(" ");
            System.out.print(list.get(i).key1());
        }
        System.out.println();
    }
}
