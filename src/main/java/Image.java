import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.io.File;

public class Image {
    private final Tesseract image = new Tesseract();
    private String imagePath;
    private String text;

    //Constructor
    public Image(String imagePath, String language) {
        this.imagePath = imagePath;
        image.setLanguage(language);
    }

    public Image() {
        image.setLanguage("rus");
    }

    //Converting from image to String method
    void imageScanner(String input) {
        this.imagePath = "C:\\Users\\User\\Downloads\\Telegram Desktop\\Tesj4\\" + input;

        try {
            this.text = image.doOCR(new File(imagePath));
        } catch (TesseractException e) {
            System.out.println("Wrong input");
        }
    }

    public String getText() {
        return text;
    }
}
