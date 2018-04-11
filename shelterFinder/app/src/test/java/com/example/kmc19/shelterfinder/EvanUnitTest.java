package com.example.kmc19.shelterfinder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by evanj on 4/11/2018.
 */

public class EvanUnitTest {
    private ShelterInfo shelter;

    @Before
    public void setup() {
        shelter = new ShelterInfo();
    }

    /*
     * Tests for the ShelterInfo setLongitude method
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNull() {
        shelter.setLongitude(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNull2() {
        String str = null;
        shelter.setLongitude(str);
    }

    @Test
    public void testEmptyString() {
        shelter.setLongitude("");
        Assert.assertTrue(shelter.getLongitude() == 0);
    }

    @Test
    public void testEmptyString2() {
        String str = "";
        shelter.setLongitude(str);
        Assert.assertTrue(shelter.getLongitude() == 0);
    }

    @Test
    public void testStrInteger() {
        shelter.setLongitude("123");
        Assert.assertTrue(shelter.getLongitude() == 123);
    }

    @Test
    public void testStrDouble() {
        shelter.setLongitude("100.2");
        Assert.assertTrue(shelter.getLongitude() == 100.2);
    }
}
