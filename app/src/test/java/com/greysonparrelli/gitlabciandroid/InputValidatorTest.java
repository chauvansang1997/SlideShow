package com.greysonparrelli.gitlabciandroid;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Adds numbers together.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public class InputValidatorTest {

    private InputValidator mUnderTest;

    @Before
    public void setup() {
        mUnderTest = InputValidator.getInstance();
    }

    @Test
    public void isInt_valid() throws Exception {
        assertTrue(mUnderTest.isInt("1"));
        assertTrue(mUnderTest.isInt("0"));
        assertTrue(mUnderTest.isInt("-1"));
        assertTrue(mUnderTest.isInt("15"));
        assertTrue(mUnderTest.isInt("-15"));
    }

    @Test
    public void isInt_invalid() throws Exception {
        assertFalse(mUnderTest.isInt("spider-man"));
        assertFalse(mUnderTest.isInt("a"));
        assertFalse(mUnderTest.isInt("?"));
        assertFalse(mUnderTest.isInt("--1"));
        assertFalse(mUnderTest.isInt("Infinity"));
    }
}