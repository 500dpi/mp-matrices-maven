package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Sara Jaljaa
 * @author Samuel A. Rebelsky
 *
 * @course CSC-207-01
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The height of the matrix.
   */
  int height;

  /**
   * The width of the matrix.
   */
  int width;

  /**
   * The default value the matrix is set to.
   */
  T defaultVal;

  /**
   * The matrix array.
   */
  T[][] matrix;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width
   *    The width of the matrix.
   * @param height
   *    The height of the matrix.
   * @param def
   *    The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *    If either the width or height are negative.
   */
  @SuppressWarnings({"unchecked"})
  public MatrixV0(int width, int height, T def) {
    if (height < 0 || width < 0) {
      throw new NegativeArraySizeException();
    } else {
      this.width = width;
      this.height = height;
      this.matrix = (T[][]) new Object[height][width];
      this.defaultVal = def;

      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          this.matrix[row][col] = def;
        } // for (col)
      } // for (row)
    } // if
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width
   *    The width of the matrix.
   * @param height
   *    The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *    If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +----------------+----------------------------------------------
  // | Helper methods |
  // +----------------+

  /**
   * Determine the number of rows in the matrix.
   *
   * @return The number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return The number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Check if the row or column is within the desired exclusive bounds
   * of 0 < x < limit.
   *
   * @param x
   *    The value to check.
   * @param limit
   *    The upper bound.
   * @return
   *    True or false if the value is within range.
   */
  public static boolean exclusive(int x, int limit) {
    return (x < 0 || x > limit);
  } // exclusive(int, int)

  /**
   * Check if the row or column is within the desired inclusive bounds
   * of 0 < x <= limit.
   *
   * @param x
   *    The value to check.
   * @param limit
   *    The upper bound.
   * @return
   *    True or false if the value is within range.
   */
  public static boolean inclusive(int x, int limit) {
    return (x < 0 || x >= limit);
  } // inclusive(int, int)

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *    The row of the element.
   * @param col
   *    The column of the element.
   *
   * @return
   *    The value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *    If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    // Check if the row and colums are within bounds, then retrieve the values
    if (inclusive(row, this.height()) || inclusive(col, this.width())) {
      throw new IndexOutOfBoundsException();
    } else {
      return this.matrix[row][col];
    } // if
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *    The row of the element.
   * @param col
   *    The column of the element.
   * @param val
   *    The value to set.
   *
   * @throws IndexOutOfBoundsException
   *    If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    // Check if the row and column are within bounds, then set values
    if (inclusive(row, this.height()) || inclusive(col, this.width())) {
      throw new IndexOutOfBoundsException();
    } else {
      this.matrix[row][col] = val;
    } // if
  } // set(int, int, T)

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *    The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *    If the row is negative or greater than the height.
   */
  @SuppressWarnings({"unchecked"})
  public void insertRow(int row) throws IndexOutOfBoundsException {

    // Check if the row is within bounds
    if (exclusive(row, this.height())) {
      throw new IndexOutOfBoundsException();
    } // if

    // A new 2-D array to store the modified matrix
    T[][] copy = (T[][]) new Object[this.height + 1][this.width];

    // Loop through & copy the rows over until reaching the row to
    // modify; then, assign that row to the default value and move
    // all the remaining rows up one value
    for (int i = 0; i < this.height + 1; i++) {
      if (i < row) {
        copy[i] = this.matrix[i];
      } else if (i == row) {
        for (int j = 0; j < this.width; j++) {
          copy[i][j] = this.defaultVal;
        } // for
      } else {
        copy[i] = this.matrix[i - 1];
      } // if
    } // for
    this.matrix = copy;
    this.height++;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *    The number of the row to insert.
   * @param vals
   *    The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *    If the row is negative or greater than the height.
   * @throws ArraySizeException
   *    If the size of vals is not the same as the width of the matrix.
   */
  @SuppressWarnings({"unchecked"})
  public void insertRow(int row, T[] vals) throws ArraySizeException {

    // Check if the row is within bounds
    if (exclusive(row, this.height())) {
      throw new IndexOutOfBoundsException();
    } // if

    // Check if the values to insert are the appropriate length (= height)
    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if

    // New 2-D array to store the modified array
    T[][] copy = (T[][]) new Object[this.height + 1][this.width];

    // Loop through & copy the rows over until reaching the row to
    // modify; then, assign that row to vals and move all the
    // remaining rows up one value
    for (int i = 0; i < this.height + 1; i++) {
      if (i < row) {
        copy[i] = this.matrix[i];
      } else if (i == row) {
        for (int j = 0; j < this.width; j++) {
          copy[i][j] = vals[j];
        } // for
      } else {
        copy[i] = this.matrix[i - 1];
      } // if
    } // for
    this.matrix = copy;
    this.height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *    The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *    If the column is negative or greater than the width.
   */
  @SuppressWarnings({"unchecked"})
  public void insertCol(int col) {

    // Check if the column is within bounds
    if (exclusive(col, this.width())) {
      throw new IndexOutOfBoundsException();
    } // if

    // New 2-D array to store the modified array
    T[][] copy = (T[][]) new Object[this.height][this.width + 1];

    // Loop through the rows and columns of the original array;
    // when the column to modify is reached, assign the entire
    // column to the default value and move all columns over by one
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width + 1; j++) {
        if (j < col) {
          copy[i][j] = this.matrix[i][j];
        } else if (j == col) {
          copy[i][col] = this.defaultVal;
        } else if (j > col) {
          copy[i][j] = this.matrix[i][j - 1];
        } // if
      } // for
    } // for
    this.width++;
    this.matrix = copy;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *    The number of the column to insert.
   * @param vals
   *    The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *    If the column is negative or greater than the width.
   * @throws ArraySizeException
   *    If the size of vals is not the same as the height of the matrix.
   */
  @SuppressWarnings({"unchecked"})
  public void insertCol(int col, T[] vals) throws ArraySizeException {

    // Check if the column is within bounds
    if (exclusive(col, this.width)) {
      throw new IndexOutOfBoundsException();
    } // if

    // Check if the values to insert are the appropriate length (= width)
    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    // New 2-D array to store the modified array
    T[][] copy = (T[][]) new Object[this.height][this.width + 1];

    // Loop through the rows and columns of the original array;
    // when the column to modify is reached, assign the entire
    // column to the each value in val and move all the remaining columns
    // over by one
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width + 1; j++) {
        if (j < col) {
          copy[i][j] = this.matrix[i][j];
        } else if (j == col) {
          copy[i][col] = vals[i];
        } else if (j > col) {
          copy[i][j] = this.matrix[i][j - 1];
        } // if
      } // for
    } // for
    this.width++;
    this.matrix = copy;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *    The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *    If the row is negative or greater than or equal to the height.
   */
  @SuppressWarnings({"unchecked"})
  public void deleteRow(int row) {

    // Check if row is within bounds
    if (inclusive(row, this.height)) {
      throw new IndexOutOfBoundsException();
    } // if

    // A new 2-D array to store the modified matrix
    T[][] copy = (T[][]) new Object[this.height - 1][this.width];

    // Copy all the values in each row; when the row value is >= the
    // row to be deleted, all rows will be shifted up one
    for (int i = 0; i < this.height - 1; i++) {
      if (i < row) {
        copy[i] = this.matrix[i];
      } else if (i >= row) {
        copy[i] = this.matrix[i + 1];
      } // if
    } // for
    this.height--;
    this.matrix = copy;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *    The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *    If the column is negative or greater than or equal to the width.
   */
  @SuppressWarnings({"unchecked"})
  public void deleteCol(int col) {

    // Check if column is within bounds
    if (inclusive(col, this.width)) {
      throw new IndexOutOfBoundsException();
    } // if

    // A new 2-D array to store the modified matrix
    T[][] copy = (T[][]) new Object[this.height][this.width - 1];

    // Copy all the values in each column, overwriting the value of the
    // deleted column by shifting every column up by one at that index
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (j < col) {
          copy[i][j] = this.matrix[i][j];
        } else if (j > col) {
          copy[i][j - 1] = this.matrix[i][j];
        } // if
      } // for
    } // for
    this.matrix = copy;
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *    The top edge / row to start with (inclusive).
   * @param startCol
   *    The left edge / column to start with (inclusive).
   * @param endRow
   *    The bottom edge / row to stop with (exclusive).
   * @param endCol
   *    The right edge / column to stop with (exclusive).
   * @param val
   *    The value to store.
   *
   * @throws IndexOutOfBoundsException
   *    If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {

    // Check that the start & end values for row & column are within bounds
    if (!((startRow >= 0 && endRow <= this.height)
        && (startCol >= 0 && startCol <= this.width))) {
      throw new IndexOutOfBoundsException();
    } // if

    // Loop through and change values from the restricted row/col bounds
    for (int row = startRow; row < endRow; row++) {
      for (int col = startCol; col < endCol; col++) {
        this.matrix[row][col] = val;
      } // for (col)
    } // for (row)
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *    The row to start with (inclusive).
   * @param startCol
   *    The column to start with (inclusive).
   * @param deltaRow
   *    How much to change the row in each step.
   * @param deltaCol
   *    How much to change the column in each step.
   * @param endRow
   *    The row to stop with (exclusive).
   * @param endCol
   *    The column to stop with (exclusive).
   * @param val
   *    The value to store.
   *
   * @throws IndexOutOfBoundsException
   *    If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow,
      int endCol, T val) {

    // Check that the start & end values for row & column are within bounds
    if (!((startRow >= 0 && endRow <= this.height)
        && (startCol >= 0 && startCol <= this.width))) {
      throw new IndexOutOfBoundsException();
    } // if

    int row = startRow;
    int col = startCol;

    // Find the coordinate (row, col) and set the value as long as
    // col < endCol and row < endRow
    do {
      this.set(row, col, val);
      row += deltaRow;
      col += deltaCol;
    } // do

    while (col != endCol && row != endRow);
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return A copy of the matrix.
   */
  @SuppressWarnings({"rawtypes"})
  public Matrix clone() {

    // To store the cloned matrix
    MatrixV0<T> cloned = new MatrixV0<>(this.width, this.height, this.defaultVal);

    // Assign all the values of the original to the cloned matrix
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        cloned.set(i, j, this.get(i, j));
      } // for
    } // for
    return cloned;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return
   *    True if the other object is a matrix with the same width,
   *    height, and equal elements; false otherwise.
   */
  @SuppressWarnings({"rawtypes"})
  public boolean equals(Object other) {

    // Check if other is a Matrix object; if not, cast it as one
    if (!(other instanceof Matrix)) {
      return false;
    } else {
      Matrix compare = (Matrix) other;

      // Check if other and this matrix have the same height and width
      if (!((this.height() == compare.height())
          && (this.width() == compare.width()))) {
        return false;
      } // if

      // Loop through the rows and columns and check to see if all
      // the values in other and this matrix are the same
      for (int i = 0; i < this.height(); i++) {
        for (int j = 0; j < this.width(); j++) {
          if (!this.get(i, j).equals(compare.get(i, j))) {
            return false;
          } // if
        } // for
      } // for
      return true;
    } // if
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and ensure
   * that the hash codes for two equal objects are the same.
   *
   * @return The hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
