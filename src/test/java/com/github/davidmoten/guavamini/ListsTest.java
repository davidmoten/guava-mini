package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
}
