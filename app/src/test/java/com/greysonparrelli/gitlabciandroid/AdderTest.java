package com.greysonparrelli.gitlabciandroid;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@link Adder} class.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public class AdderTest {

    private Adder mUnderTest;

    @Before
    public void setup() {
        mUnderTest = Adder.getInstance();
    }

    @Test
    public void add_default() throws Exception {
        assertEquals(3, mUnderTest.add(1, 2));
        assertEquals(3, mUnderTest.add(2, 1));
    }

    @Test
    public void add_zero() throws Exception {
        assertEquals(5, mUnderTest.add(5, 0));
    }

    @Test
    public void add_negative() throws Exception {
        assertEquals(0, mUnderTest.add(1, -1));
        assertEquals(-1, mUnderTest.add(1, -2));
    }

    @Test
    public void add_overflow() throws Exception {
        assertEquals(Integer.MIN_VALUE, mUnderTest.add(Integer.MAX_VALUE, 1));
    }

    @Test
    public void add_underflow() throws Exception {
        assertEquals(Integer.MAX_VALUE, mUnderTest.add(Integer.MIN_VALUE, -1));
    }
}