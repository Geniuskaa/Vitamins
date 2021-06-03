import java.util.logging.Logger;

public class ThreadsCounting {
    private static Logger log = Logger.getLogger(ThreadsCounting.class.getName());

    private FoodValue foodValue1;
    private FoodValue foodValue2;

    public ThreadsCounting() {
        this.foodValue1 = new FoodValue();
        this.foodValue2 = new FoodValue();
    }

    public FoodValue getFoodValue1() {
        return foodValue1;
    }

    public FoodValue getFoodValue2() {
        return foodValue2;
    }

    public Thread firstCounter(String input){
        Thread thread1 = new Thread(() -> {
            try{
                String s1 = new TextRecognizer().RecoText(input); // падает тут
                foodValue1 = Main.foodAndEnergyValueCleaner(s1);
            }catch (Exception e){
                log.info(e.getMessage());
            }
        });

        thread1.setName("1st Thread");

        return thread1;
    }

    public Thread secondCounter(String input){
        Thread thread2 = new Thread(() -> {
            try{
                Image image1 = new Image();
                image1.imageScanner(input);
                String s2 = image1.getText();
                foodValue2 = Main.foodAndEnergyValueCleaner(s2);
            }catch (Exception e){
                log.info(e.getMessage());
            }
        });
        thread2.setName("2nd Thread");

        return thread2;
    }
}
