package com.m1if10.app.classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStringTest {

    @Test
    public void nextString() {
        // Given
        RandomString rs = new RandomString(32);
        //When
        String rss1 = rs.nextString();
        String rss2 = rs.nextString();
        String rss3 = rs.nextString();
        //Then
        assertNotEquals(rss1,rss2);
        assertNotEquals(rss1,rss3);
        assertNotEquals(rss3,rss2);
    }
}