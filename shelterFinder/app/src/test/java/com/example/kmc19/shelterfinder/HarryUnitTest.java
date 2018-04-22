package com.example.kmc19.shelterfinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests ShelterInfo class's special notes getter and setter methods.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HarryUnitTest {
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
     * Tests for the ShelterInfo setSpecialNotes(); method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNull() {
        shelter.setSpecialNotes(null);
    }

    @Test
    public void testNullMessage() {
        try {
            shelter.setSpecialNotes(null);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().equals("Special notes can not be null."));
        }
    }

    @Test
    public void testEmptyString() {
        shelter.setSpecialNotes("");
        Assert.assertTrue("N/A".equals(shelter.getSpecialNotes()));
    }

    @Test
    public void testNumbers() {
        shelter.setSpecialNotes("123");
        Assert.assertTrue("123".equals(shelter.getSpecialNotes()));
    }

    @Test
    public void testLetters() {
        shelter.setSpecialNotes("One family");
        Assert.assertTrue("One family".equals(shelter.getSpecialNotes()));
    }
}