package com.github.davidmoten.util;

import java.util.ArrayList;
import java.util.Collections;

public final class Lists {

    public static <E> ArrayList<E> newArrayList(E... elements) {
        Preconditions.checkNotNull(elements);
        // Avoid integer overflow when a large array is passed in
        int capacity = computeArrayListCapacity(elements.length);
        ArrayList<E> list = new ArrayList<E>(capacity);
        Collections.addAll(list, elements);
        return list;
    }

    private static int computeArrayListCapacity(int arraySize) {
        Preconditions.checkArgument(arraySize >= 0, "arraySize must be non-negative");

        // TODO(kevinb): Figure out the right behavior, and document it
        return saturatedCast(5L + arraySize + (arraySize / 10));
    }

    /**
     * Returns the {@code int} nearest in value to {@code value}.
     *
     * @param value
     *            any {@code long} value
     * @return the same value cast to {@code int} if it is in the range of the
     *         {@code int} type, {@link Integer#MAX_VALUE} if it is too large,
     *         or {@link Integer#MIN_VALUE} if it is too small
     */
    private static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

}
