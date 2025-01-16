package net.edwardcode.sorting.sorts;

import net.edwardcode.sorting.keys.BasicKey;
import net.edwardcode.sorting.keys.MainKey;

import java.util.ArrayList;
import java.util.List;

public class RecursiveMergeSort implements SortingAlgorithm {
    private final boolean isDescending;
    private final boolean useSecondKey;

    public RecursiveMergeSort(boolean isDescending, boolean useSecondKey) {
        this.isDescending = isDescending;
        this.useSecondKey = useSecondKey;
    }

    private BasicKey getKey(MainKey key) {
        return (useSecondKey ? key.key2() : key.key1());
    }

    @Override
    public List<MainKey> sort(List<MainKey> input, int count) {
        if (System.getenv("STABILITY") != null) {
            ArrayList<MainKey> sorted = new ArrayList<>(input);
            mergeSort(sorted, 0, count - 1);

            checkStability(input, sorted);

            return sorted;
        }
        return mergeSort(new ArrayList<>(input), 0, count - 1);
    }

    private List<MainKey> mergeSort(List<MainKey> list, int left, int right) {
        if (left >= right) {
            return List.of(list.get(left));
        }

        int mid = left + (right - left) / 2;
        List<MainKey> leftSorted = mergeSort(list, left, mid);
        List<MainKey> rightSorted = mergeSort(list, mid + 1, right);
        return merge(leftSorted, rightSorted);
    }

    private List<MainKey> merge(List<MainKey> left, List<MainKey> right) {
        List<MainKey> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (compareKeys(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }

    private int compareKeys(MainKey a, MainKey b) {
        int comparison = getKey(a).compare(getKey(b));
        return isDescending ? -comparison : comparison;
    }

    // Method to check stability
    private void checkStability(List<MainKey> original, List<MainKey> sorted) {
        for (int i = 1; i < sorted.size(); i++) {
            MainKey current = sorted.get(i - 1);
            MainKey next = sorted.get(i);

            // If primary keys are equal, check the order of secondary keys
            if (getKey(current).compare(getKey(next)) == 0) {
                int originalIndexCurrent = original.indexOf(current);
                int originalIndexNext = original.indexOf(next);
                if (originalIndexCurrent > originalIndexNext) {
                    System.out.println("[STABILITY CHECK] Sorting is not stable!");
                    return;
                }
            }
        }
        System.out.println("[STABILITY CHECK] Sorting is stable.");
    }
}
