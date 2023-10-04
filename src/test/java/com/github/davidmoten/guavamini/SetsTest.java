package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class SetsTest {

    @Test
    public void testCreate() {
        assertTrue(Sets.newHashSet(1, 2, 3).contains(2));
    }
    
    @Test
    public void testCreateUsingOf() {
        assertTrue(Sets.of(1, 2, 3).contains(2));
    }

    @Test
    public void testCreateFromIterator() {
        assertTrue(Sets.newHashSet(Sets.newHashSet(1, 2, 3).iterator()).contains(2));
    }

    @Test
    public void testCreateFromIterable() {
        assertTrue(Sets.newHashSet(Sets.newHashSet(1, 2, 3)).contains(2));
    }

    @Test
    public void testCreateFromNonCollectionIterable() {
        assertTrue(Sets.newHashSet(new Iterable<Integer>() {

            @Override
            public Iterator<Integer> iterator() {
                return Sets.newHashSet(1, 2).iterator();
            }
        }).contains(2));
    }

    @Test
    public void testIsUtilityClass() {
        Asserts.assertIsUtilityClass(Sets.class);
    }

    @Test
    public void testCheckNonNegativeZero() {
        Sets.checkNonnegative(0, "boo");
    }

    @Test
    public void testCheckNonNegativePositive() {
        Sets.checkNonnegative(1, "boo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNonNegativeNegative() {
        Sets.checkNonnegative(-1, "boo");
    }

    @Test
    public void testCapacityForOne() {
        assertEquals(2, Sets.capacity(1));
    }

    @Test
    public void testCapacityForLarge() {
        assertEquals(Integer.MAX_VALUE, Sets.capacity(Sets.MAX_POWER_OF_TWO + 1));
    }
}
