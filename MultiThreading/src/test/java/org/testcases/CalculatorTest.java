package org.testcases;

import org.junit.*;

import java.util.Scanner;

import static org.junit.Assert.*;
import static org.testcases.Calculator.add;
import static org.testcases.Calculator.subtract;

public class CalculatorTest {
    int a, b;
    static Scanner sc;

    @BeforeClass
    public static void init() {
//        sc = new Scanner(System.in);
    }

    @AfterClass
    public static void close() {
//        sc.close();
    }

    @Before
    public void beforeEach() {
        a = 10;
        b = 20;
    }

    @After
    public void afterEach() {
        a = 0;
        b = 0;
    }

    @Test
    public void addPositiveTest1() {
        assertNotNull(add(a, b));
    }

    @Test
    public void addPositiveTest2() {
        assertEquals(20, add(a, b));
    }

    @Test
    public void addNegativeTest() {
        assertNotEquals(0, add(a, b));
    }

    @Test
    public void subtractPositiveTest() {
        assertNotNull(subtract(a, b));
    }
}
