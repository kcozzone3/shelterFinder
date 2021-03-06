package com.example.kmc19.shelterfinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
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
     * Tests for the ShelterInfo setCapacity method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNull() {
        shelter.setCapacity(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNull2() {
        shelter.setCapacity(null);
    }

    @Test
    public void testEmptyString() {
        shelter.setCapacity("");
        Assert.assertTrue("N/A".equals(shelter.getCapacity()));
    }

    @Test
    public void testEmptyString2() {
        String str = "";
        shelter.setCapacity(str);
        Assert.assertTrue("N/A".equals(shelter.getCapacity()));
    }

    @Test
    public void testNumbers() {
        shelter.setCapacity("123");
        Assert.assertTrue("123".equals(shelter.getCapacity()));
    }

    @Test
    public void testLetters() {
        shelter.setCapacity("One family");
        Assert.assertTrue("One family".equals(shelter.getCapacity()));
    }
}