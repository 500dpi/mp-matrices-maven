package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * A variety of utilities for getting input.
 *
 * @author Samuel A. Rebelsky
 */
public class IOUtils {
  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Determine if an array contains a particular value.
   *
   * @param <T>
   *   The type of values in the array.
   * @param vals
   *   The array to search.
   * @param val
   *   The value to look for.
   *
   * @return true if the array contains an equal value and false otherwise.
   */
  private static <T> boolean arrayContains(T[] vals, T val) {
    for (T tmp : vals) {
      if (tmp.equals(val)) {
        return true;
      } // if
    } // for
    return false;
  } // arrayContains(T[], T)


  // +------------------+--------------------------------------------
  // | Provided methods |
  // +------------------+

  /**
   * Repeatedly prompt for a command until one is returned.
   *
   * @param pen
   *   Where to print the prompt.
   * @param eyes
   *   How to read input.
   * @param prompt
   *   The prompt to print.
   * @param commands
   *   The valid commands.
   *
   * @return the command entered.
   *
   * @throws IOException
   *   If an I/O exception occurs.
   */
  public static String readCommand(PrintWriter pen, BufferedReader eyes,
      String prompt, String[] commands) {
    return "";
  } // readCommand(PrintWrtier, BufferedReader, String, String[])
} // class IOUtils
