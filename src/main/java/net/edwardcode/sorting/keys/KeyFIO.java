package net.edwardcode.sorting.keys;

import net.edwardcode.sorting.RussianStringComparator;

import java.text.Collator;
import java.util.Locale;
import java.util.regex.Pattern;

public class KeyFIO extends BasicKey {
    public String firstName;
    public String lastName;
    public String middleName;
    private static final Pattern FIO_PATTERN = Pattern.compile("([-А-ЯЁа-яё]+) ([-А-ЯЁа-яё]+) ([-А-ЯЁа-яё]+)");

    public KeyFIO(String value) {
        if (!FIO_PATTERN.matcher(value).matches()) {
            throw new InvalidKeyException("String is not a valid key: " + value);
        }
        String[] parts = value.split( " ");
        lastName = parts[0];
        firstName = parts[1];
        middleName = parts[2];
    }
    public KeyFIO(String lastName, String firstName, String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    @Override
    public int compare(BasicKey v1) {
        KeyFIO k1 = this;
        KeyFIO k2 = (KeyFIO) v1;

        if (System.getenv("USECOMPARATOR") != null) {
            // Получаем Collator для русского языка
            RussianStringComparator comparator = new RussianStringComparator();
            // Сравниваем фамилии
            int res = comparator.compare(k1.lastName, k2.lastName);
            if (res == 0) {
                // Сравниваем имена
                res = comparator.compare(k1.firstName, k2.firstName);
            }
            if (res == 0) {
                // Сравниваем отчества
                res = comparator.compare(k1.middleName, k2.middleName);
            }
            return res;
        }

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
