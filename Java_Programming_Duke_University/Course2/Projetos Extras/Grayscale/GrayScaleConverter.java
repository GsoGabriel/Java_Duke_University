
/**
 * Escreva a descrição da classe GrayScaleConverter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    //I Started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage){
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel p : outImage.pixels()){
            //look at the correspondent pixel in inImage
            Pixel inPixel = inImage.getPixel(p.getX(), p.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            int sum = inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen();
            //divide that sum by 3 (call it average)
            int average = sum / 3;
            //set pixel's red to average
            p.setRed(average);
            //set pixel's green to average
            p.setGreen(average);
            //set pixel's blue to average
            p.setBlue(average);
        }
        //outImage is
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeGray(inImage);
            gray.draw();
        }
    }
    
    
    public void copyGray(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            ImageResource imageGray = makeGray(image);
            String fname = image.getFileName();
            String newName = "gray-" + fname;
            imageGray.setFileName(newName);
            imageGray.draw();
            image.draw();
            imageGray.save();
        }
    }
    
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
}
