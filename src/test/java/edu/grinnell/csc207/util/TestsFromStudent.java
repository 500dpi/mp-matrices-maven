package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import java.lang.Float;
import java.lang.Math;

/**
 * Tests for MatrixV0 methods.
 *
 * @author Sara Jaljaa
 * @course CSC-207-01
 */
public class TestsFromStudent {

  // +-----------------+---------------------------------------------
  // | get() and set() |
  // +-----------------+

  /**
   * Tests that get() & set() work with different Matrix types.
   */
  @Test
  public void testGetSet() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String    */ Matrix<String> tester = new MatrixV0<String>(10, 10, "default");
    /* Integer   */ Matrix<Integer> tester2 = new MatrixV0<Integer>(9, 9, 999);
    /* Character */ Matrix<Character> tester3 = new MatrixV0<Character>(18, 4, '\b');
    /* String[]  */ Matrix<String[]> tester4 = new MatrixV0<String[]>(9, 9, new String[] {"help", "me"});

    // String Matrix
    assertEquals(
        "default",
        tester.get(5, 5),
        "Value at (5, 5) has been correctly set to 'default'.");
    tester.set(5, 5, "not default");
    assertEquals(
        "not default",
        tester.get(5, 5),
        "Value at (5, 5) has been correctly set to 'not default'.");

    // Integer Matrix
    tester2.set(3, 3, 450);
    assertEquals(
        450,
        tester2.get(3, 3),
        "Value at (3, 3) has been correctly set to 450.");

    // Character Matrix
    tester3.set(3, 6, ' ');
    assertEquals(
        ' ',
        tester3.get(3, 6),
        "Value at (3, 6) has been correctly set to the empty space character.");

    // String Array Matrix (3-D?)
    tester4.set(5, 1, new String[] {"help", "hlp", "hlep", "hepl"});
    assertEquals(
        "hlep",
        tester4.get(5, 1)[2],
        "Value at (5, 1) has been correctly set to new String array.");
  } // testGetSet()

  /**
   * Tests for handling edge cases in get() and set().
   */
  @Test
  public void testEdgeGetSet() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* Character */ Matrix<Character> tester = new MatrixV0<Character>(6, 12, 'f');
    /* Integer   */ Matrix<Integer> tester2 = new MatrixV0<Integer>(6, 12, 0);
    /* Double    */ Matrix<Double> tester3 = new MatrixV0<Double>(6, 12, 44.9);

    // What happens when we get/set an index above the bounds?

    // What happens when we get/set an index to some 0-value?

    // What happens when we get/set an index below 0?

  } // testEdgeGetSet()

  // +----------------------+----------------------------------------
  // | height() and width() |
  // +----------------------+

  /**
   * Tests that the correct height & width is returned.
   */
  @Test
  public void testDimensions() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* Integer */ Matrix<Integer> tester = new MatrixV0<Integer>(100, 45, 6);

    // Setting standard dimensions (100x45)
    assertEquals(
        45,
        tester.height(),
        "The height is 45.");
    assertEquals(
        100,
        tester.width(),
        "The width is 100.");

    // What happens when we enter negative dimensions (-96x73)?
    assertThrows(NegativeArraySizeException.class,
        () -> new MatrixV0<Boolean>(-96, 73, false));
  } // testDimensions()

  // +-----------------------------+---------------------------------
  // | insertRow() and insertCol() |
  // +-----------------------------+

  /**
   * Checks that an inserted row has the correct value.
   */
  @Test
  public void testInsertRow() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String  */ Matrix<String> tester = new MatrixV0<String>(100, 45, "no");
    /* String  */ Matrix<String> tester2 = new MatrixV0<String>(5, 20, "hello");
    /* Boolean */ Matrix<Boolean> tester3 = new MatrixV0<Boolean>(6, 12, false);

    // Inserting a new default row
    tester.set(2, 2, "yes");
    assertEquals(
        "yes",
        tester.get(2, 2),
        "Row 2 has value 'yes'.");
    tester.insertRow(2);
    assertEquals(
        "no",
        tester.get(2, 2),
        "Row 2 has value 'no'.");

    // Inserting a new row with some value
    tester2.set(4, 2, "world");
    assertEquals(
        "world",
        tester2.get(4, 2),
        "At (4, 2) the value is \"world.\"");
    try {
      String[] typos = {"worlde", "worlb", "wolrd", "woirld", "wourld"};
      tester2.insertRow(4, typos);
      assertEquals("wolrd", tester2.get(4, 2),
          "At (4, 2) the value has updated correctly.");
    } catch (ArraySizeException e) {
      fail("Error: insertRow() call failed.");
    } // try/catch
  } // testInsertRow()

  /**
   * Tests for edge cases when inserting a row with values.
   */
  @Test
  public void testEdgeInsertRow() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* Character */ Matrix<Character> char2D = new MatrixV0<Character>(100, 45, 'n');
    /* Float     */ Matrix<Float> float2D = new MatrixV0<Float>(100, 45, (float) 44.9);

    // NaN = Float.intBitsToFloat(0x7fc00000)
    // -101.0/0 = - infinity
    // '\n'
    // null or ""

    // What happens when we enter control characters into the matrix?

    // What happens when we insert a new NaN row into the matrix?

    // What happens when we insert into a negative row?

    // What happens when we insert into a row greater than the height?

  } // testEdgeInsertRow()

  /**
   * Checks that an inserted column has the correct value.
   */
  @Test
  public void testInsertCol() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String  */ Matrix<String> string2D = new MatrixV0<String>(100, 45, "fhoesj fsefoger ejpij");
    /* Integer */ Matrix<Integer> integer2D = new MatrixV0<Integer>(100, 45, 40);

    // Inserting a new default column

    // Inserting a new column with some value

  } // testInsertCol()

  /**
   * Tests for edge cases when inserting a column with defaults and values.
   */
  @Test
  public void testEdgeInsertCol() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* Byte   */ Matrix<Byte> byte2D = new MatrixV0<Byte>(100, 45, Byte.MIN_VALUE);
    /* Double */ Matrix<Double> double2D = new MatrixV0<Double>(100, 45, Math.E);

    // What happens when we insert 2^7 bytes into the matrix?
    
    // What happens when we enter an infintely negative value into the matrix?

    // What happens when we insert into a negative column?

    // What happens when we insert into a column greater than the width?

  } // testEdgeInsertCol()

  // +-----------------------------+---------------------------------
  // | deleteRow() and deleteCol() |
  // +-----------------------------+

  /**
   * Checks that a row has been deleted from the matrix.
   */
  @Test
  public void testDeleteRow() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String  */ Matrix<String> string2D = new MatrixV0<String>(32, 12, "weewoo");
    /* Integer */ Matrix<Integer> integer2D = new MatrixV0<Integer>(32, 12, 200);

    // Deleting rows at random

  } // testDeleteRow()

  /**
   * Tests for edge cases when deleting a row.
   */
  @Test
  public void testEdgeDeleteRow() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String  */ Matrix<String> string2D = new MatrixV0<String>(5, 15, "woowee");
    /* Integer */ Matrix<Integer> integer2D = new MatrixV0<Integer>(3, 6, 2);

    // What happens if we delete a row that is less than zero?

    // What happens if we delete a row that is greater than the height?

  } // testEdgeDeleteRow()

  /**
   * Checks that a column has been deleted from the matrix.
   */
  @Test
  public void testDeleteCol() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* String  */ Matrix<String> string2D = new MatrixV0<String>(32, 12, "weewoo");
    /* Integer */ Matrix<Integer> integer2D = new MatrixV0<Integer>(32, 12, 200);

    // Deleting rows at random

  } // testDeleteCol()

  /**
   * Tests for edge cases when deleting a column.
   */
  @Test
  public void testEdgeDeleteCol() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    /* Character */ Matrix<Character> char2D = new MatrixV0<Character>(5, 15, 't');
    /* Integer   */ Matrix<Integer> integer2D = new MatrixV0<Integer>(86, 93, 4);

    // What happens if we delete a column that is less than zero?

    // What happens if we delete a column that is greater than the height?

  } // testEdgeDeleteCol()

  // +-----------------------------+---------------------------------
  // | fillLine() and fillRegion() |
  // +-----------------------------+

  @Test
  public void testFillLine() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    // @FIX

  } // testFillLine()

  @Test
  public void testEdgeFillLine() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    // @FIX

  } // testEdgeFillLine()

  @Test
  public void testFillRegion() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    // @FIX

  } // testFillRegion()

  @Test
  public void testEdgeFillRegion() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    // @FIX

  } // testEdgeFillRegion()

  // +----------+----------------------------------------------------
  // | equals() |
  // +----------+

  @Test
  public void testEquals() {

    // +-------------+
    // | > Matrices  |
    // +-------------+

    // @FIX

  } // testEquals()
} // class TestsFromStudent