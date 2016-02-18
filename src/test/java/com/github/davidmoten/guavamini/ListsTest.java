package com.github.davidmoten.guavamini;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ListsTest {

    @Test
    public void instantiateList() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        assertEquals(list, Lists.newArrayList(1, 2));
    }
}
