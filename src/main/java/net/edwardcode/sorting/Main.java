package net.edwardcode.sorting;

import net.edwardcode.sorting.keys.KeyFIO;
import net.edwardcode.sorting.keys.KeyGroup;
import net.edwardcode.sorting.keys.MainKey;
import net.edwardcode.sorting.sorts.BinaryInsertionsSort;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<MainKey> keys = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("input.csv"), StandardCharsets.UTF_8);
        int i = 1;
        for (String line : lines) {
            if (line.isEmpty()) continue;
            String[] parts = line.split(",");
            keys.add(new MainKey(new KeyGroup(parts[0]), new KeyFIO(parts[1]), i));
            i++;
        }

        System.out.println("Loaded " + keys.size() + " keys to sort...");

        int lineCount = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input lines to work with: ");
            String line = scanner.nextLine();
            if (line.isEmpty()) continue;
            try {
                lineCount = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(line + " is not a number");
                continue;
            }
            if (lineCount < 10 || lineCount > 1_000_000) {
                System.out.println("Number must be between 10 and 1_000_000 (inclusive)");
                continue;
            }
            break;
        }

        System.out.println("Will be sorting only " + lineCount + " keys...");
        System.out.println("-".repeat(("Will be sorting only " + lineCount + " keys...").length()));

        // 1. Binary insertions sort
        BinaryInsertionsSort binaryInsertionsSort = new BinaryInsertionsSort(false, true);
        List<MainKey> sorted1 = binaryInsertionsSort.sort(keys, lineCount);

        for (MainKey key : sorted1) {
            System.out.println(key);
        }
    }
}
