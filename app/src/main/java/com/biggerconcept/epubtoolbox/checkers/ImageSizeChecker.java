package com.biggerconcept.epubtoolbox.checkers;

import com.biggerconcept.epubtoolbox.results.Message;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;

public class ImageSizeChecker extends Checker implements _IChecker {
    public final double IMAGE_SIZE_THRESHOLD = 4.2;
    private final String[] TARGET_EXTENSIONS = new String[4];
    
    public ImageSizeChecker(File inEpub) {
        super(inEpub);
        TARGET_EXTENSIONS[0] = "jpg";
        TARGET_EXTENSIONS[1] = "jpeg";
        TARGET_EXTENSIONS[2] = "gif";
        TARGET_EXTENSIONS[3] = "png";
    }
    
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
    
    protected Collection<File> checkerTargets() {
        return FileUtils.listFiles(workDir, TARGET_EXTENSIONS, true);
    }
    
    protected double imageSize(File file) throws Exception {
        BufferedImage currentImage = readImage(file);
        return (currentImage.getWidth() * currentImage.getHeight())/1000000;
    }
    
    protected BufferedImage readImage(File file) throws IOException {
        return ImageIO.read(file);
    }
}
