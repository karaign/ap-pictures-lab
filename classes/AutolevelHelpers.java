public class AutolevelHelpers {
  /**
   * Calculates the "lightness" of a given pixel
   * @param pix the pixel
   * @return the average value of the pixel's red, green and blue
   */
  public static int calculateLightness (Pixel pix)
  {
    return (pix.getRed() + pix.getBlue() + pix.getGreen()) / 3;
  }

  /**
   * Returns a value between min and max, which is proportional
   * to the "original"'s position between 0 and 255
   * @param original the original value
   * @param min the lower bound
   * @param max the higher bound
   * @return the value redistributed to be between min and max
   */
  public static int redistributeColor (int original, int min, int max)
  {
    if (max == min) // Edge case to avoid division by zero
    {
      return min;
    }
    return (255 * (original - min)) / (max - min);
  }

  /**
   * Redistributes a pixel's color values to be between the corresponding min and max
   * @param pix the pixel to be modified
   */
  public static void redistributePixel (Pixel pix,
                                        int minRed, int maxRed, int minGreen,
                                        int maxGreen, int minBlue, int maxBlue)
  {
    pix.setRed(redistributeColor(pix.getRed(), minRed, maxRed));
    pix.setGreen(redistributeColor(pix.getGreen(), minGreen, maxGreen));
    pix.setBlue(redistributeColor(pix.getBlue(), minBlue, maxBlue));
  }
}
