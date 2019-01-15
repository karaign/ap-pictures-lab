public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }

  /**
   * count the number of times a certain value
   * is found in the matrix
   * @param i the value to look for
   * @return the number of occurrences
   */
  public int getCount(int i)
  {
    int count = 0;
    for (int[] row : matrix) {
      for (int number : row) {
        if (number == i) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * @return the largest value in the matrix
   */
  public int getLargest()
  {
    int largest = Integer.MIN_VALUE;
    for (int[] row : matrix) {
      for (int number : row) {
        if (number > largest) {
          largest = number;
        }
      }
    }
    return largest;
  }

  /**
   * calculates the total of all values in a column
   * @param col the column to get the values from
   * @return the sum of all values from column
   */
  public int getColTotal(int col)
  {
    int total = 0;
    for (int[] row : matrix) {
      total += row[col];
    }
    return total;
  }
 
}