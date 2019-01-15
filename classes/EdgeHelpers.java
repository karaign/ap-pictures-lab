/**
 * Contains some methods used for the implementation of Picture.edgeDetection2
 */
public class EdgeHelpers {
  /**
   * Returns one of the neighboring pixels of a given pixel in a matrix.
   * If that pixel doesn't exist, the closest existing pixel is taken instead
   * @param pixels the matrix to get the pixels from
   * @param row the row of the pixel that we're looking at
   * @param col the col of the pixel that we're looking at
   * @param neighbor which neighbor we should get, represented by any of the vectors
   *                 (-1, -1), (-1, 0), (-1, 1),
   *                 (0, -1),  _______, (0, 1),
   *                 (1, -1),  (1, 0),  (1, 1)
   * @return the neighboring pixel
   */
  public static Pixel getNeighbor(Pixel[][] pixels, int row, int col, IntVector neighbor)
  {
    int maxRow = pixels.length - 1;
    int maxCol = pixels[0].length - 1;
    int resultRow = row;
    int resultCol = col;

    int testRow = row + neighbor.x;
    if (testRow >= 0 && testRow <= maxRow) {
      resultRow = testRow;
    }

    int testCol = col + neighbor.y;
    if (testCol >= 0 && testCol <= maxCol) {
      resultCol = testCol;
    }

    return pixels[resultRow][resultCol];
  }

  /**
   * @return true if one of a and b is greater than edgeDist and the other one is smaller,
   *         false otherwise.
   */
  public static boolean edgePair (double a, double b, double edgeDist)
  {
    return (a > edgeDist && b < edgeDist || a < edgeDist && b > edgeDist);
  }
}
