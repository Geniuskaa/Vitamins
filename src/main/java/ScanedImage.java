import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

public class ScanedImage {

    public static void processImg(BufferedImage ipimage, float scaleFactor, float offset) throws IOException, TesseractException {

        BufferedImage opimage = new BufferedImage(1050, 1024, ipimage.getType());


        Graphics2D graphic = opimage.createGraphics();

        graphic.drawImage(ipimage, 0, 0, 1050, 1024, null);

        graphic.dispose();


        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null);


        // и запись в файл .png

        BufferedImage fopimage = rescale.filter(opimage, null);

        ImageIO.write(fopimage, "jpg", new File("C:\\Users\\User\\Downloads\\Telegram Desktop\\Tesj4\\ph2.png"));


        Tesseract it = new Tesseract();

        it.setDatapath("C:\\Users\\User\\Downloads\\Tess4J-3.4.8-src\\Tess4J");


        String str = it.doOCR(fopimage);

        System.out.println(str);

    }
}