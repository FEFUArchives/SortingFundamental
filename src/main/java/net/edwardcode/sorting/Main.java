package net.edwardcode.sorting;

import net.edwardcode.sorting.keys.KeyFIO;
import net.edwardcode.sorting.keys.KeyGroup;
import net.edwardcode.sorting.keys.MainKey;
import net.edwardcode.sorting.sorts.BinaryInsertionsSort;
import net.edwardcode.sorting.sorts.RecursiveMergeSort;

import java.io.FileWriter;
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
        long time1 = System.currentTimeMillis();
        BinaryInsertionsSort binaryInsertionsSort = new BinaryInsertionsSort(false, false);
        List<MainKey> sorted1 = binaryInsertionsSort.sort(keys, lineCount);
        long time2 = System.currentTimeMillis();
        long timeInsertions = time2 - time1;
        System.out.println("Sorting with method 1 took " + timeInsertions + " ms");

        // 2. Recursive merge sort
        time1 = System.currentTimeMillis();
        RecursiveMergeSort recursiveMergeSort = new RecursiveMergeSort(false, true);
        List<MainKey> sorted2 = recursiveMergeSort.sort(keys, lineCount);
        time2 = System.currentTimeMillis();
        long timeMerge = time2 - time1;
        System.out.println("Sorting with method 2 took " + timeMerge + " ms");

        System.out.println("Writing resulting files...");
        saveInformation(lineCount, sorted1, timeInsertions, "output_insertions.csv");

        saveInformation(lineCount, sorted2, timeMerge, "output_merge.csv");

    }

    private static void saveInformation(int lineCount, List<MainKey> sorted2, long timeMerge, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (int j = 0; j < lineCount; j++) {
            MainKey key = sorted2.get(j);
            writer.write(key.key1() + "," + key.key2() + "," + key.lineNumber() + "\n");
        }
        writer.write(timeMerge + "\n");
        writer.flush();
        writer.close();
    }
}
