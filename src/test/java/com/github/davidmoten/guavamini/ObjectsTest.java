package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class ObjectsTest {

    @Test
    public void testHashCode() {
        assertEquals(4798, Objects.hashCode(123, 24));
    }

    @Test
    public void isUtilityClass() {
        Asserts.assertIsUtilityClass(Objects.class);
    }

    @Test
    public void nullEqualsNull() {
        assertTrue(Objects.equal(null, null));
    }

    @Test
    public void nullDoesNotEqualNotNull() {
        assertFalse(Objects.equal(null, 123));
    }

    @Test
    public void notNullDoesNotEqualNull() {
        assertFalse(Objects.equal(123, null));
    }

    @Test
    public void objectsAreEqual() {
        assertTrue(Objects.equal("a", "a"));
    }

    @Test
    public void objectsAreNotEqual() {
        assertFalse(Objects.equal("a", "b"));
    }

}
