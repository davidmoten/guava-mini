package com.github.davidmoten.guavamini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class Lists {
    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements.
     *
     * <p>
     * <b>Note:</b> essentially the only reason to use this method is when you
     * will need to add or remove elements later. Otherwise, for non-null
     * elements use {@link ImmutableList#of()} (for varargs) or
     * {@link ImmutableList#copyOf(Object[])} (for an array) instead. If any
     * elements might be null, or you need support for
     * {@link List#set(int, Object)}, use {@link Arrays#asList}.
     *
     * <p>
     * Note that even when you do need the ability to add or remove, this method
     * provides only a tiny bit of syntactic sugar for {@code newArrayList(}
     * {@link Arrays#asList asList}{@code (...))}, or for creating an empty list
     * then calling {@link Collections#addAll}. This method is not actually very
     * useful and will likely be deprecated in the future.
     */
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
