package com.github.davidmoten.util;

import java.util.Collections;
import java.util.HashSet;

public class Sets {
    private static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    public static <E> HashSet<E> newHashSet(E... elements) {
        Preconditions.checkNotNull(elements);
        HashSet<E> set = newHashSetWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    /**
     * Creates a {@code HashSet} instance, with a high enough "initial capacity"
     * that it <i>should</i> hold {@code expectedSize} elements without growth.
     * This behavior cannot be broadly guaranteed, but it is observed to be true
     * for OpenJDK 1.6. It also can't be guaranteed that the method isn't
     * inadvertently <i>oversizing</i> the returned set.
     *
     * @param expectedSize
     *            the number of elements you expect to add to the returned set
     * @return a new, empty {@code HashSet} with enough capacity to hold {@code
     *         expectedSize} elements without resizing
     * @throws IllegalArgumentException
     *             if {@code expectedSize} is negative
     */
    public static <E> HashSet<E> newHashSetWithExpectedSize(int expectedSize) {
        return new HashSet<E>(capacity(expectedSize));
    }

    /**
     * Returns a capacity that is sufficient to keep the map from being resized
     * as long as it grows no larger than expectedSize and the load factor is >=
     * its default (0.75).
     */
    static int capacity(int expectedSize) {
        if (expectedSize < 3) {
            checkNonnegative(expectedSize, "expectedSize");
            return expectedSize + 1;
        }
        if (expectedSize < MAX_POWER_OF_TWO) {
            return expectedSize + expectedSize / 3;
        }
        return Integer.MAX_VALUE; // any large value
    }

    static int checkNonnegative(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
        }
        return value;
    }

}
