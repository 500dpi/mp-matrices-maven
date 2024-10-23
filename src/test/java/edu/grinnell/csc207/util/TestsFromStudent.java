package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests for MatrixV0 methods.
 *
 * @author Sara Jaljaa
 * @course CSC-207-01
 */
public class TestsFromStudent {

  /**
   * Tests that get() & set() work with multiple calls to each.
   */
  @Test
  public void getAndSetTest() {
    Matrix<String> tester = new MatrixV0<String>(10, 10, "default");
    assertEquals("default", tester.get(5, 5),
        "At (5, 5), the value is \"default.\"");
    tester.set(5, 5, "not default");
    assertEquals("not default", tester.get(5, 5),
        "At (5, 5), the value is now \"not default.\"");

    Matrix<Integer> tester2 = new MatrixV0<Integer>(9, 9, 999);
    tester2.set(3, 3, 450);
    assertEquals(450, tester2.get(3, 3),
        "At (3, 3), the value is correctly set to 450.");
  } // getAndSetTest()

  /**
   * Tests that the correct height & width is returned.
   */
  @Test
  public void dimensionTest() {
    Matrix<Integer> tester = new MatrixV0<Integer>(100, 45, 6);
    assertEquals(45, tester.height(), "The height is 45.");
    assertEquals(100, tester.width(), "The width is 100.");
  } // dimensionTest()

  /**
   * Checks that an inserted row has the correct value.
   */
  @Test
  public void insertRowTest() {
    Matrix<String> tester = new MatrixV0<String>(100, 45, "no");
    tester.set(2, 2, "yes");
    assertEquals("yes", tester.get(2, 2), "Row 2 has value \"yes.\"");
    tester.insertRow(2);
    assertEquals("no", tester.get(2, 2), "Row 2 has value \"no.\"");

    Matrix<String> tester2 = new MatrixV0<String>(5, 20, "hello");
    tester2.set(4, 2, "world");
    assertEquals("world", tester2.get(4, 2),
        "At (4, 2) the value is \"world.\"");

    try {
      String[] typos = {"worlde", "worlb", "wolrd", "woirld", "wourld"};
      tester2.insertRow(4, typos);
      assertEquals("wolrd", tester2.get(4, 2),
          "At (4, 2) the value has updated correctly.");
    } catch (ArraySizeException e) {
      fail("Error: insertRow() call failed.");
    } // try
  } // insertRowTest()

  /**
   * Checks that an inserted column has the correct value.
   */
  @Test
  public void insertColTest() {
  } // insertColTest()

  /**
   * Checks that a row has been deleted from the matrix.
   */
  @Test
  public void deleteRowTest() {
  } // deleteRowTest()

  /**
   * Checks that a column has been deleted from the matrix.
   */
  @Test
  public void deleteColTest() {
  } // deleteColTest()
} // class TestsFromStudent