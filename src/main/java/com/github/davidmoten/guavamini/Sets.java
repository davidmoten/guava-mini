package com.github.davidmoten.guavamini;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;

import com.github.davidmoten.guavamini.annotations.VisibleForTesting;

public final class Sets {

    private Sets() {
        // prevent instantiation
    }

    static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    /**
     * Creates a <i>mutable</i> {@code HashSet} instance containing the given
     * elements in unspecified order.
     *
     * <p>
     * <b>Note:</b> if mutability is not required and the elements are non-null,
     * use an overload of {@link ImmutableSet#of()} (for varargs) or
     * {@link ImmutableSet#copyOf(Object[])} (for an array) instead.
     *
     * <p>
     * <b>Note:</b> if {@code E} is an {@link Enum} type, use
     * {@link EnumSet#of(Enum, Enum[])} instead.
     *
     * @param elements
     *            the elements that the set should contain
     * @return a new {@code HashSet} containing those elements (minus
     *         duplicates)
     */
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
    @VisibleForTesting
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

    @VisibleForTesting
    static int checkNonnegative(int value, String name) {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
        }
        return value;
    }

}
