package com.m1if10.app.classes;

import org.junit.Test;

import static org.junit.Assert.*;

public class CryptageMdpTest {

    @Test
    public void encrypt() {
        // Given
        String toto = "toto";
        //When
        toto = CryptageMdp.encrypt(toto);
        //Then
        assertEquals(CryptageMdp.encrypt("toto"),toto);
    }
}