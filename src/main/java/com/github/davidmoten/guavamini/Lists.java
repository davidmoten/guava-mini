package com.github.davidmoten.guavamini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.github.davidmoten.guavamini.annotations.VisibleForTesting;

public final class Lists {

    private Lists() {
        // cannot instantiate
    }

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

    @VisibleForTesting
    static int computeArrayListCapacity(int arraySize) {
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
    @VisibleForTesting
    static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    /**
     * Creates a <i>mutable</i>, empty {@code ArrayList} instance (for Java 6
     * and earlier).
     *
     * <p>
     * <b>Note:</b> if mutability is not required, use
     * {@link ImmutableList#of()} instead.
     *
     * <p>
     * <b>Note for Java 7 and later:</b> this method is now unnecessary and
     * should be treated as deprecated. Instead, use the {@code ArrayList}
     * {@linkplain ArrayList#ArrayList() constructor} directly, taking advantage
     * of the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements; a very thin shortcut for creating an empty list then calling
     * {@link Iterables#addAll}.
     *
     * <p>
     * <b>Note:</b> if mutability is not required and the elements are non-null,
     * use {@link ImmutableList#copyOf(Iterable)} instead. (Or, change
     * {@code elements} to be a {@link FluentIterable} and call
     * {@code elements.toList()}.)
     *
     * <p>
     * <b>Note for Java 7 and later:</b> if {@code elements} is a
     * {@link Collection}, you don't need this method. Use the {@code ArrayList}
     * {@linkplain ArrayList#ArrayList(Collection) constructor} directly, taking
     * advantage of the new <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
     */
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        Preconditions.checkNotNull(elements); // for GWT
        // Let ArrayList's sizing logic work, if possible
        return (elements instanceof Collection) ? new ArrayList<E>(Collections2.cast(elements))
                : newArrayList(elements.iterator());
    }

    /**
     * Creates a <i>mutable</i> {@code ArrayList} instance containing the given
     * elements; a very thin shortcut for creating an empty list and then
     * calling {@link Iterators#addAll}.
     *
     * <p>
     * <b>Note:</b> if mutability is not required and the elements are non-null,
     * use {@link ImmutableList#copyOf(Iterator)} instead.
     */
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = newArrayList();
        Iterators.addAll(list, elements);
        return list;
    }
}
