package net.edwardcode.sorting.sorts;

import net.edwardcode.sorting.keys.BasicKey;
import java.util.LinkedList;

public interface SortingAlgorithm {
    LinkedList<BasicKey> sort(LinkedList<BasicKey> input, int count);
}
