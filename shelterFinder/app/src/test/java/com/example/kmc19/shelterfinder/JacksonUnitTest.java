package com.example.kmc19.shelterfinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JacksonUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private ShelterInfo shelter;

    @Before
    public void setup() {
        shelter = new ShelterInfo();
    }
    /*
     * Tests for the ShelterInfo setShelterName method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNull() {
        shelter.setShelterName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNull2() {
        shelter.setShelterName(null);
    }

    @Test
    public void testEmptyString() {
        shelter.setShelterName("");
        Assert.assertTrue("".equals(shelter.getShelterName()));
    }

    @Test
    public void testEmptyString2() {
        String str = "";
        shelter.setShelterName(str);
        Assert.assertTrue("".equals(shelter.getShelterName()));
    }

    @Test
    public void testNumbers() {
        shelter.setShelterName("123");
        Assert.assertTrue("123".equals(shelter.getShelterName()));
    }

    @Test
    public void testLetters() {
        shelter.setShelterName("One family");
        Assert.assertTrue("One family".equals(shelter.getShelterName()));
    }
}