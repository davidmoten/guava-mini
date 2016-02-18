package com.github.davidmoten.guavamini;

import org.junit.Test;

import com.github.davidmoten.junit.Asserts;

public class IteratorsTest {

    @Test
    public void isUtilityClass() {
        Asserts.assertIsUtilityClass(Iterators.class);
    }

}
