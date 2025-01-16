package net.edwardcode.sorting.sorts;

import net.edwardcode.sorting.keys.MainKey;

import java.util.List;

public interface SortingAlgorithm {
    List<MainKey> sort(List<MainKey> input, int count);
}
