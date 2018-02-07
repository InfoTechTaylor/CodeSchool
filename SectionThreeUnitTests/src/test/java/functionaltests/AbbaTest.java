package functionaltests;

//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbbaTest {

    private Abba abba = new Abba();

    public AbbaTest() {

    }

//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }




    @Test
    public void testAbbaHiBye() {
        // abba("Hi", "Bye") -> "HiByeByeHi"
        assertEquals(abba.abba("Hi", "Bye"), "HiByeByeHi");
    }

    @Test
    public void testAbbaYoAlice() {
        // abba("Yo", "Alice") -> "YoAliceAliceYo"
        assertEquals(abba.abba("Yo", "Alice"), "YoAliceAliceYo");
    }

    @Test
    public void testAbbaWhatUp() {
        // abba("What", "Up") -> "WhatUpUpWhat"
        assertEquals(abba.abba("What", "Up"), "WhatUpUpWhat");
    }
}