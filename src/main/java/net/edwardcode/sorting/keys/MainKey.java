package net.edwardcode.sorting.keys;

public record MainKey(KeyGroup key1, KeyFIO key2, int lineNumber) {
    @Override
    public String toString() {
        return lineNumber + ". " + key1 + " -> " + key2;
    }
}
