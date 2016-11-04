import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encryption
{
  private static int[] array;
  public static void main(String args[])
  {
    //Setting up the variables
    FileInputStream file = null;
    BufferedReader reader = null;
    String inputString = args[1];

    //Demonstrating string to be encoded
    System.out.println(inputstring);

    //Getting string for input file
    String inputFile = args[0];

    //Converting the string to a string of binary
    stringToBinary(string);
    BufferedImage image = null;
    image = encryptImage(inputFile);
    try {
    File outputfile = new File("output.png");
    ImageIO.write(image, "png", outputfile);
    } catch (IOException e) {
      }
  
  } // main


  /*
   *This method will take a string and convert it to a string of the 
   *binary representation
  */
  private static void stringToBinary(String string) {    
    array = new int[string.length()*8];
    String byteString;
    int currentElement = 0;
    for(int i=0; i < string.length(); i++) {
      byteString = Integer.toBinaryString(string.charAt(i));
      int length = byteString.length();
      for(int j = 0; j < 8 - length; j++)
        byteString = "0" + byteString;
      for(int k = 0; k < 8; k++)
      {
        if (byteString.charAt(k) == '1')
          array[currentElement] = 1;
        else 
          array[currentElement] = 0;
        currentElement ++;
      } // for
    
    } // for
  } // stringToBinary

  /*
   *This method will take a file for which the message should be encrypted into
  */
  private static BufferedImage encryptImage(String file) {
  BufferedImage image = null;
    try {
      image = ImageIO.read(new File(file));
    } catch (IOException e) {
    }

  try {
      image = ImageIO.read(new File(file));
  } catch (IOException e) {
    }
  int width = image.getWidth();
  int height = image.getHeight();
  int top = (int) (height*0.05);
  int bottom = (int) (height*0.95);
  int currentElement = 0;
  for (int r = 0; r < top; r++)
  {
    for (int c = 0; c < width - width%8; c ++)
    {     
        if (currentElement < array.length)
        {
          if (array[currentElement] == 1)
            image.setRGB(c, r, -16777215);
          else if(array[currentElement] == 0)
            image.setRGB(c, r, -16777214);
          else
            image.setRGB(c, r, -1);
          currentElement ++;
        } // if
        else
          image.setRGB(c, r, -16777213);
       if (c != width - width%8 - 1)
        for (int i = width - width%8; i < width; i++)
          image.setRGB(i, r, -16777213);

    } // for
  } // for

  for (int r = bottom; r < height; r++)
  {
    for (int c = 0; c < width; c ++)
    {
      if (currentElement < array.length)
        {
          if (array[currentElement] == 1)
            image.setRGB(c, r, -16777215);
          else if(array[currentElement] == 0)
            image.setRGB(c, r, -16777214);
          else
            image.setRGB(c, r, -1);
          currentElement ++;
        } // if
        else
          image.setRGB(c, r, -16777213);
       if (c != width - width%8 - 1)
        for (int i = width - width%8; i < width; i++)
          image.setRGB(i, r, -16777213);

    } // for
  } // for
  return image;

  } // encrypImage



} // Encryption
