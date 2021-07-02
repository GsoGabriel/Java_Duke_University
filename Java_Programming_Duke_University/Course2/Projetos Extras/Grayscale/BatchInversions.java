
/**
 * Escreva a descrição da classe BatchInversions aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import java.io.*;

/**
 * Then create a new BlueJ class called BatchInversions. In this class create a method named makeInversion that has one
 * parameter, an image, and returns a new image that is the inverse of the original image.
 */
public class BatchInversions {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel p : outImage.pixels()){
            //look at the correspondent pixel in inImage
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            int redInverse = 255 - inPixel.getRed();
            int blueInverse = 255 - inPixel.getBlue();
            int greenInverse = 255 - inPixel.getGreen();
            //set pixel's red to average
            p.setRed(redInverse);
            //set pixel's green to average
            p.setGreen(greenInverse);
            //set pixel's blue to average
            p.setBlue(blueInverse);
        }
        //outImage is
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource inverse = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            inverse.setFileName(newName);
            inverse.draw();
            inImage.draw();
            inverse.save();
        }
    }
    
}
