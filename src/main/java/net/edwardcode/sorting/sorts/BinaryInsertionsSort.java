package net.edwardcode.sorting.sorts;

import net.edwardcode.sorting.DebugUtils;
import net.edwardcode.sorting.keys.BasicKey;
import net.edwardcode.sorting.keys.MainKey;

import java.util.ArrayList;
import java.util.List;

public class BinaryInsertionsSort implements SortingAlgorithm {
    private final boolean isDescending;
    private final boolean useSecondKey;

    public BinaryInsertionsSort(boolean isDescending, boolean useSecondKey) {
        this.isDescending = isDescending;
        this.useSecondKey = useSecondKey;
    }

    private BasicKey getKey(MainKey key) {
        return (useSecondKey ? key.key2() : key.key1());
    }

    @Override
    public List<MainKey> sort(List<MainKey> input, int count) {
        List<MainKey> in = new ArrayList<>(input);

        for (int i = 1; i < count; i++) { // Start from 1 since the first element is already "sorted"
            MainKey key = in.get(i);
            int lo = 0, hi = i - 1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (!isDescending) {
                    if (getKey(key).compare(getKey(in.get(mid))) < 0) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    if (getKey(key).compare(getKey(in.get(mid))) > 0) {
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
            }

            for (int j = i; j > lo; j--) {
                in.set(j, in.get(j - 1));
            }
            in.set(lo, key);
            DebugUtils.printListWithSlash(in, i + 1);
        }

        return in;
    }
}
