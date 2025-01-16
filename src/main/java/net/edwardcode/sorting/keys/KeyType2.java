package net.edwardcode.sorting.keys;

import java.util.regex.Pattern;

public class KeyType2 extends BasicKey {
    public String firstName;
    public String lastName;
    public String middleName;
    private static final Pattern FIO_PATTERN = Pattern.compile("^[А-ЯЁ][а-яё]*(-[А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$");

    public KeyType2(String value) {
        if (!FIO_PATTERN.matcher(value).matches()) {
            throw new InvalidKeyException("String is not a valid key: " + value);
        }
        String[] parts = value.split( " ");
        lastName = parts[0];
        firstName = parts[1];
        middleName = parts[2];
    }
    public KeyType2(String lastName, String firstName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public int compare(BasicKey v1) {
        KeyType2 k1 = this;
        KeyType2 k2 = (KeyType2) v1;

        int res = k1.lastName.compareTo(k2.lastName);
        if (res == 0) res = k1.firstName.compareTo(k2.firstName);
        if (res == 0) res = k1.middleName.compareTo(k2.middleName);
        return Integer.compare(res, 0);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName;
    }
}
