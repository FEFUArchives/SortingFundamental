package net.edwardcode.sorting.keys;

import java.util.regex.Pattern;

public class KeyGroup extends BasicKey {
    public GroupEducationType type;
    public int num;
    private static final Pattern GROUP_PATTERN = Pattern.compile("([БСМ])([0-9]{4})");

    public KeyGroup(String value) {
        if (!GROUP_PATTERN.matcher(value).matches()) {
            throw new InvalidKeyException("String is not a valid key: " + value);
        }
        GroupEducationType groupType = GroupEducationType.fromChar(value.charAt(0));
        int num = Integer.parseInt(value.substring(1));
        this.type = groupType;
        this.num = num;
    }

    public KeyGroup(GroupEducationType type, int num) {
        this.type = type;
        this.num = num;
    }

    @Override
    public int compare(BasicKey v1) {
        KeyGroup k1 = this;
        KeyGroup k2 = (KeyGroup) v1;
        if (k1.type.getType() < k2.type.getType()) {
            return -1;
        }
        if (k1.type.getType() > k2.type.getType()) {
            return 1;
        }

        return Integer.compare(k1.num, k2.num);
    }

    public String addLeadZeros() {
        return String.format("%04d", num);
    }

    public enum GroupEducationType {
        BACHELOR('Б'),
        SPECIALISATION('С'),
        MAGISTRACY('М');

        private final char type;

        GroupEducationType(char type) {
            this.type = type;
        }
        public char getType() {
            return this.type;
        }
        public static GroupEducationType fromChar(char type) {
            return switch (type) {
                case 'Б' -> BACHELOR;
                case 'С' -> SPECIALISATION;
                case 'М' -> MAGISTRACY;
                default -> null;
            };
        }
    }

    @Override
    public String toString() {
        return type.getType() + addLeadZeros();
    }
}
