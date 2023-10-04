package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class ListsTest {

    @Test
    public void instantiateList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        assertEquals(list, Lists.newArrayList(1, 2));
    }
    
    @Test
    public void testOf() {
        assertEquals(Lists.newArrayList(1, 2), Lists.of(1, 2));
    }

    @Test
    public void createListFromIterable() {
        ArrayList<Integer> list = Lists.newArrayList(Lists.newArrayList(1, 2));
        assertEquals(list, Lists.newArrayList(1, 2));
    }

    @Test
    public void createListFromNonCollectionIterable() {
        ArrayList<Integer> list = Lists.newArrayList(new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return Lists.newArrayList(1, 2).iterator();
            }
        });
        assertEquals(list, Lists.newArrayList(1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeCapacityThrowsIAE() {
        Lists.computeArrayListCapacity(-1);
    }

    @Test
    public void saturatedCastWhenLargeReturnsIntegerMaxValue() {
        assertEquals(Integer.MAX_VALUE, Lists.saturatedCast(Integer.MAX_VALUE + 1L));
    }

    @Test
    public void saturatedCastWhenLargeNegativeReturnsIntegerMinValue() {
        assertEquals(Integer.MIN_VALUE, Lists.saturatedCast(Integer.MIN_VALUE - 1L));
    }

    @Test
    public void isUtilityClass() {
        Asserts.assertIsUtilityClass(Lists.class);
    }

    @Test
    public void newEmptyList() {
        assertTrue(Lists.newArrayList().isEmpty());
    }
}
