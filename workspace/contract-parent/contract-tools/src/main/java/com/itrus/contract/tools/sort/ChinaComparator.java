package com.itrus.contract.tools.sort;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

public class ChinaComparator {

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Comparator comparator = Collator.getInstance(Locale.CHINA);
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
                (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }
    public static int compare(Object o1, Object o2) {
        Comparator comparator = Collator.getInstance(Locale.CHINA);
        return comparator.compare(o1,o2);
    }
}
