package com.udacity.examples.Testing;

import java.util.Arrays;

import org.junit.Test;

public class HelperTest {
    @Test
    public void verify_getCount() {
        List<String> empNames = Arrays.asList("John", "Jane", "Joe");
        final long actual = Helper.getCount(empNames);
        assertEquals(3, actual);
    }

    @Test
    public void verify_getStats() {
        List<Integer> expYears = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final IntSummaryStatistics actual = Helper.getStats(expYears);
        assertEquals(10, actual.getCount());
        assertEquals(55, actual.getSum());
        assertEquals(1, actual.getMin());
        assertEquals(10, actual.getMax());
        assertEquals(5.5, actual.getAverage(), 0.0);
    }

    @Test
    public void compare_arrays() {
        int[] yrs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] expectedYrs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        assertArrayEquals(expectedYrs, yrs);
    }

    @Test
    public void verify_getMergedList() {
        List<String> empNames = Arrays.asList("John", "Jane", "Joe");
        final String actual = Helper.getMergedList(empNames);
        assertEquals("John, Jane, Joe", actual);
    }
}
