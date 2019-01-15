import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

  /**
   * Sets all the colours to 0 except blue
   */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  /**
   * Negates all the colors
   */
  public void negate() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());

      }
    }
  }

  /**
   * Converts the whole image to grayscale
   */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pix : rowArray)
      {
        int averageShade = (pix.getRed() + pix.getBlue() + pix.getGreen()) / 3;
        pix.setRed(averageShade);
        pix.setGreen(averageShade);
        pix.setBlue(averageShade);

      }
    }
  }

  /**
   * Implements a version of the auto-level algorithm,
   * where the darkest and the brightest pixels in an image
   * are changed black and white, and the rest have their colors
   * redistributed to be proportionally between black and white.
   * This should improve the contrast of the image.
   */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    // first, we look for the darkest and the brightest pixels
    int minLightness = 127;
    int maxLightness = 127;
    int minRed = 127;
    int maxRed = 127;
    int minGreen = 127;
    int maxGreen = 127;
    int minBlue = 127;
    int maxBlue = 127;
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pix : rowArray)
      {
        int lightness = AutolevelHelpers.calculateLightness(pix);
        if (lightness > maxLightness)
        {
          maxLightness = lightness;
          maxRed = pix.getRed();
          maxGreen = pix.getGreen();
          maxBlue = pix.getBlue();
        }
        else if (lightness < minLightness)
        {
          minLightness = lightness;
          minRed = pix.getRed();
          minGreen = pix.getGreen();
          minBlue = pix.getBlue();
        }
      }
    }
    // now redistribute the colors
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pix : rowArray)
      {
        AutolevelHelpers.redistributePixel(pix, minRed, maxRed, minGreen, maxGreen, minBlue, maxBlue);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }

  /** Method that mirrors the picture around a
   * vertical mirror in the center of the picture
   * from right to left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = width - 1; col > width / 2; col--)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }

  /** Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel btmPixel = null;
    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < height / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        btmPixel = pixels[height - 1 - row][col];
        btmPixel.setColor(topPixel.getColor());
      }
    }
  }

  /** Method that mirrors the picture around a
   * horizontal mirror in the center of the picture
   * from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel btmPixel = null;
    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = height - 1; row > height / 2; row--)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        btmPixel = pixels[height - 1 - row][col];
        btmPixel.setColor(topPixel.getColor());
      }
    }
  }

  /**
   * Mirrors a square part of the picture around
   * a diagonal line.
   */
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftBtm = null;
    Pixel rightTop = null;
    int height = pixels.length;
    int width = pixels[0].length;
    int squareSide = Math.min(height, width);
    // Move down the diagonal line starting at (0, 0)
    // The square part of the picture is divided into two triangles,
    // left-bottom and right-top
    for (int diag = 0; diag < squareSide; diag++)
    {
      // For every pixel (diag, diag),
      // copy (diag, a) into (a, diag)
      // for every 0 < a < diag
      for (int a = 0; a < diag; a++) {
        rightTop = pixels[a][diag];
        leftBtm = pixels[diag][a];
        rightTop.setColor(leftBtm.getColor());
      }
    }
  }

  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }

    System.out.println(count);
  }

  /** Mirror just part of a picture of a snowman */
  public void mirrorArms()
  {
    int mirrorPoint = 199;
    Pixel topPixel = null;
    Pixel btmPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows up to the mirror point
    for (int row = 159; row < mirrorPoint; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 102; col < 300; col++)
      {
        count++;

        topPixel = pixels[row][col];
        btmPixel = pixels[mirrorPoint - row + mirrorPoint][col];
        btmPixel.setColor(topPixel.getColor());
      }
    }

    System.out.println(count);
  }

  /** Mirror just part of a picture of a seagull */
  public void mirrorGull()
  {
    int mirrorPoint = 349;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();

    // loop through the rows
    for (int row = 227; row < 332; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 233; col < mirrorPoint; col++)
      {
        count++;

        leftPixel = pixels[row][col];
        rightPixel = pixels[row]
                [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }

    System.out.println(count);
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /**
   * Copies a specified part of the source picture
   * into a specified point in this picture.
   * @param source the picture to copy from
   * @param fromStartRow at which row of the source picture to start copying
   * @param fromEndRow at which row of the source picture to end copying
   * @param fromStartCol at which column of the source picture to start copying
   * @param fromEndCol at which column of the source picture to end copying
   * @param toStartRow the start row to copy to
   * @param toStartCol the start column to copy to
   */
  public void copy (Picture source,
                    int fromStartRow, int fromEndRow,
                    int fromStartCol, int fromEndCol,
                    int toStartRow, int toStartCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = source.getPixels2D();
    for (int fromRow = fromStartRow, toRow = toStartRow;
         fromRow < fromEndRow &&
                 toRow < toPixels.length;
         fromRow++, toRow++)
    {
      for (int fromCol = fromStartCol, toCol = toStartCol;
           fromCol < fromEndCol &&
                   toCol < toPixels[0].length;
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }

  /**
   * Adds MaaM, a bird and a snowman into the picture.
   * Assumes the picture's resolution is 640*480 because I'm lazy.
   */
  public void myCollage()
  {
    // add MaaM
    Picture mark = new Picture("blue-mark.jpg");
    mark.mirrorVertical();
    mark.zeroBlue();
    this.copy(mark, 154, 480, 154, 480, 154, 147);
    // add bird
    Picture bird = new Picture("seagull.jpg");
    bird.negate(); // invert the bird
    this.copy(bird, 230, 330, 236, 346, 53, 31);
    // add snow dude
    Picture snowman = new Picture("snowman.jpg");
    this.copy(snowman, 73, 73 +151, 100, 297, 327, 111);
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel currentPixel = null;
    Pixel rightPixel = null;
    Pixel bottomPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color bottomColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        currentPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
                            /* fall back to the current pixel when on last row */
        bottomPixel = pixels[row == pixels.length - 1 ? row : row + 1][col];
        bottomColor = bottomPixel.getColor();
        if (currentPixel.colorDistance(rightColor) >
            edgeDist || currentPixel.colorDistance(bottomColor) > edgeDist)
          currentPixel.setColor(Color.BLACK);
        else
          currentPixel.setColor(Color.WHITE);
      }
    }
  }

  /**
   * An alternative version of edgeDetection, based on the idea that
   * a pixel should be considered an edge if it has a large color
   * distance on one side and a small distance on the other.
   * Works better for larger values of edgeDist than edgeDetection.
   * @param edgeDist the distance for finding edges
   */
  public void edgeDetection2(int edgeDist)
  {
    Pixel[][] pixels = this.getPixels2D();
    boolean[][] edges = new boolean[pixels.length][pixels[0].length];
    // Go through all the pixels to find edges
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        Pixel pix = pixels[row][col];
        Color color = pix.getColor();
        // Compare the pixel with its 8 neighbours.
        // Let the neighbors be numbered like this:
        // 0  1  2
        // 3 pix 5
        // 6  7  8
        double[] neighborColorDistances = new double[8];
        int count = 0;
        for (int x = -1; x <= 1; x++)
        {
          for (int y = -1; y <= 1; y++)
          {
            if (x == 0 && y == 0) continue;
            Pixel neighbor = EdgeHelpers.getNeighbor(pixels, row, col, new IntVector(x, y));
            double distance = Pixel.colorDistance(pix.getColor(), neighbor.getColor());
            neighborColorDistances[count++] = distance;
          }
        }
        // The pixel is considered an edge if it has a large color distance
        // from one side, but not the other.
        // Compare the distance pairs (0, 4), (1, 5), (2, 6) and (3, 7).
        // If at least one of the pairs has one value greater than edgeDist and another less than edgeDist,
        // this pixel is determined to be an edge one.
        for (int i = 0; i < 4; i++)
        {
          if (EdgeHelpers.edgePair(neighborColorDistances[i], neighborColorDistances[i + 4], edgeDist))
          {
            edges[row][col] = true;
            break;
          } else
          {
            edges[row][col] = false;
          }
        }
      }
    }
    // Now that we know which pixels are edges, replace the image with black and white pixels
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        pixels[row][col].setColor(edges[row][col] ? Color.BLACK : Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args)
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
