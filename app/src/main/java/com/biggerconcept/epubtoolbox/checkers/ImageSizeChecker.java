package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

/**
 * Action for checking the size of all images.
 * 
 * @author Andrew Bigger
 */
public class ImageSizeChecker extends Checker implements IChecker {
    /**
     * Maximum image size in mega pixels
     */
    public final double IMAGE_SIZE_THRESHOLD = 4.2;
    
    /**
     * String array of file extensions of files to check
     */
    private final String[] TARGET_EXTENSIONS = new String[4];
    
    /**
     * Constructor for creating a new ImageSizeChecker.
     * 
     * Calls Checker constructor and then adds each supported image type file
     * extension to the TARGET_EXTENSIONS instance variable.
     * 
     * @param inEpub 
     */
    public ImageSizeChecker(File inEpub) {
        super(inEpub);
        TARGET_EXTENSIONS[0] = "jpg";
        TARGET_EXTENSIONS[1] = "jpeg";
        TARGET_EXTENSIONS[2] = "gif";
        TARGET_EXTENSIONS[3] = "png";
    }
    
    /**
     * Checks for image files above the threshold.
     * 
     * Sets result title to indicate the check intent. Then iterates through
     * all image files to ensure that they are under the threshold.
     * 
     * When image size is above the threshold, they are added to the result.
     * 
     * @throws Exception 
     */
    @Override
    public void check() throws Exception {        
        getResult().setTitle("Image Size Check Results");
        getResult().addMessage(new Message("info", "Validating image size."));
        
        for (File entry: checkerTargets()) {
            double size = imageSize(entry);
            
            if (size > IMAGE_SIZE_THRESHOLD) { 
                reportResult(entry, Double.toString(size));
            }
        }
    }
    
    /**
     * Produces an error message for files above the image size threshold and
     * adds to check result.
     * 
     * @param testedFile
     * @param testOutcome 
     */
    protected void reportResult(File testedFile, String testOutcome) {
        String message = (
                "ERROR: " 
                + testedFile.getName() 
                + ": File is " 
                + testOutcome  + " "
                + "megapixels in size. It should be less than " 
                + IMAGE_SIZE_THRESHOLD + "mpx. "
                + "Please reduce the size of this image." );
        
        getResult().addMessage(new Message("error", message));
    }
    
    /**
     * Produces a collection of File objects of files for the image size check
     * to validate.
     * 
     * This collection is of all of the valid image files within the expanded
     * EPUB.
     * 
     * @return 
     */
    protected Collection<File> checkerTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
    /**
     * Evaluates the size of an image in Megapixels.
     * 
     * A megapixel is an area of 1 million pixels.
     * 
     * In the event that the check is unable to resolve the size of the image
     * in megapixels an exception will be raised.
     * 
     * @param file
     * @return
     * @throws Exception 
     */
    protected double imageSize(File file) throws Exception {
        BufferedImage currentImage = readImage(file);
        return (currentImage.getWidth() * currentImage.getHeight())/1000000;
    }
    
    /**
     * Reads image file from disk.
     * 
     * @param file
     * @return
     * @throws IOException 
     */
    protected BufferedImage readImage(File file) throws IOException {
        return ImageIO.read(file);
    }
}
