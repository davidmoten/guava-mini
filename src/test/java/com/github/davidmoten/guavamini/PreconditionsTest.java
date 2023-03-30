package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class PreconditionsTest {

    @Test(expected = NullPointerException.class)
    public void testNullThrowsException() {
        Preconditions.checkNotNull(null);
    }

    @Test
    public void testNotNullDoesNotThrowException() {
        Preconditions.checkNotNull(new Object());
    }

    @Test
    public void testNotNullReturnsTypedObject() {
        Integer n = 1;
        Integer m = Preconditions.checkNotNull(n);
        assertEquals(n, m);
    }

    @Test
    public void testCheckArgumentThrowsIAE() {
        try {
            Preconditions.checkArgument(false, "hi");
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals("hi", e.getMessage());
        }
    }

    @Test
    public void testCheckArgumentDoesNotThrowIAE() {
        Preconditions.checkArgument(true, "hi");
    }

    @Test
    public void testCheckArgumentWithoutMessageThrowsIAE() {
        try {
            Preconditions.checkArgument(false);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testCheckArgumentWithoutMessageDoesNotThrowIAE() {
        Preconditions.checkArgument(true);
    }

    @Test
    public void testCoverage() {
        Asserts.assertIsUtilityClass(Preconditions.class);
    }

    @Test
    public void testCheckArgumentNotNullThrowsIAE() {
        try {
            Preconditions.checkArgumentNotNull(null);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            assertEquals("argument cannot be null", e.getMessage());
        }
    }

    @Test
    public void testCheckArgumentNotNullReturnsWhenNotNull() {
        Object o = new Object();
        assertTrue(o == Preconditions.checkArgumentNotNull(o));
    }

}
