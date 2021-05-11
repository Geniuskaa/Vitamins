import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TextRecognizer {
    Tesseract ts;
    public String RecoText(){
        ts = new Tesseract();
        ts.setDatapath("C:\\code\\Java\\tessdata");
        ts.setLanguage("rus");
        String text = "empty";

        Scanner in = new Scanner(System.in);
        System.out.println("Введите название файла: ");
        String input = in.nextLine();

        try {
            text = ts.doOCR(getImage("C:\\Users\\User\\Downloads\\Telegram Desktop\\Tesj4\\" + input));
        } catch (TesseractException | IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    private BufferedImage getImage(String imgPath) throws IOException {
        Mat mat = Imgcodecs.imread(imgPath);
        Mat gray = new Mat();
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(mat, gray, new Size(0,0), 10); // It`s additional thing
        Core.addWeighted(mat, 1.5, gray, -0.5, 0, gray); // It`s additional thing
        Mat resized = new Mat();
        Size size = new Size(mat.width() * 1.9f, mat.height() * 1.9f);
        Imgproc.resize(gray, resized, size);
        MatOfByte mof = new MatOfByte();
        byte imageByte[];
        Imgcodecs.imencode(".png", resized, mof);
        imageByte = mof.toArray();

        return ImageIO.read(new ByteArrayInputStream(imageByte));
    }
}

//        Mat destination = new Mat();
//        Mat source = Imgcodecs.imread("C:\\Users\\User\\Downloads\\Telegram Desktop\\Tesj4\\test8.jpg");
//
//        for(int i=0; i < 1; i++){
//            destination = new Mat(source.rows(), source.cols(), source.type());
//            Imgproc.GaussianBlur(source, destination, new Size(0,0), 10);
//            Core.addWeighted(source, 1.5, destination, -0.5, 0, destination);
//            Imgcodecs.imwrite("C:\\Users\\User\\Downloads\\Telegram Desktop\\Tesj4\\test8.jpg", destination);
//            source = destination;
//        }
