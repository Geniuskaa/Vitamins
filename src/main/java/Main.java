import org.opencv.core.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите название файла: ");
        String input = in.nextLine();

        ThreadsCounting counting = new ThreadsCounting();
        Thread thread1 = counting.firstCounter(input);
        Thread thread2 = counting.secondCounter(input);

        ConnectionToBD con = new ConnectionToBD(); // Подключение к БД

        //con.tableCreationSQLite();

        thread1.start();
        thread2.start();


        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }

        System.out.println("\nThis is final version!");
        FoodValue f3 = new FoodValue();
        try {
            f3 = foodValueImprover(counting.getFoodValue1(), counting.getFoodValue2());
            System.out.println(f3.toString());
            //con.addProductDataSQLite(f3);     //TODO добавление в БД
        } catch (ClassNotFoundException e) {
            log.info(e.getMessage());
        }



        System.out.println("______________________________________________________________________");

        }


        synchronized public static FoodValue foodAndEnergyValueCleaner(String text) throws InterruptedException {
            String line = "";
            try {
                BufferedReader reader = new BufferedReader(new FileReader("regex.txt"));
                line = reader.readLine();
            }catch (IOException e){
                log.info(e.getMessage());
            }

            String scanedText = text.toLowerCase()
                    .replaceAll(line, "");

            ArrayList<String> cleanedFoodAndEnVal = new ArrayList<>();

            if(scanedText.contains("ценнос") || scanedText.contains("еннос")){
                String[] fAndEnVal = scanedText.split(" ");
                System.out.println(Thread.currentThread().getName() + " смог считать пищевую и энергетическую ценность продукта!");

                int startPosition = -1;
                for(int i = 0; i < fAndEnVal.length; i++){
                    if(fAndEnVal[i].contains("ценнос") || fAndEnVal[i].contains("еннос")){
                        startPosition = i;
                        break;
                    }
                }

                if(startPosition != -1){
                    for(int j = startPosition + 1; j < fAndEnVal.length; j++){
                        String temp = fAndEnVal[j].replaceAll("\\s+","");

                        if(temp.contains("дата") ||temp.contains("хран") || temp.contains("годен")){
                            break;
                        }

                        if(temp.length() > 2){
                            cleanedFoodAndEnVal.add(temp);
                        }
                    }

                }

            }else{
                System.out.println(Thread.currentThread().getName() + " не смог считать пищевую и энергетическую ценность продукта...");
                return null;
            }

            FoodValue fv = new FoodValue();
            for(int t = 0; t < cleanedFoodAndEnVal.size(); t++){
                String currentElement = cleanedFoodAndEnVal.get(t);

                if(currentElement.contains("жир")){
                    fv.stringToDoubleFats(cleanedFoodAndEnVal.get(t + 1));
                    continue;
                }

                if(currentElement.contains("бел")){
                    fv.stringToDoubleProteins(cleanedFoodAndEnVal.get(t + 1));
                    continue;
                }

                if(currentElement.contains("углевод")){
                    fv.stringToDoubleCarbohydrates(cleanedFoodAndEnVal.get(t + 1));
                    continue;
                }

                if(currentElement.contains("сахароз")){
                    fv.stringToDoubleSucRose(cleanedFoodAndEnVal.get(t + 1));
                    continue;
                }

                if(currentElement.contains("кка")){
                    fv.stringToDoublePowerValueCcal(cleanedFoodAndEnVal.get(t - 1));
                    continue;
                }
            }

            System.out.println(fv.toString());

            return fv;
        }

        public static FoodValue foodValueImprover(FoodValue f1, FoodValue f2) throws ClassNotFoundException {
            if(f1 == null && f2 == null){
                //дальше продолжать нельзя
                throw new ClassNotFoundException("Данные не распознаны!");
            }else if(f1 == null){
                return f2;
            }else if (f2 == null){
                return f1;
            }

            FoodValue f3;

            f3 = new FoodValue(fieldsComparator(f1.getFats(), f2.getFats()),
                    fieldsComparator(f1.getProteins(), f2.getProteins()),
                    fieldsComparator(f1.getCarbohydrates(), f2.getCarbohydrates()),
                    fieldsComparator(f1.getSucrose(), f2.getSucrose()),
                    fieldsComparator(f1.getPowerValueCcal(), f2.getPowerValueCcal()));


            return f3;
        }


        private static double fieldsComparator(double first, double second){
            if(first == second && first != Double.NEGATIVE_INFINITY){
                return first;
            }else if (first == second){
                return 0;
            }

            if(first != Double.NEGATIVE_INFINITY && first != 0){
                return first;
            }else if (second != Double.NEGATIVE_INFINITY && second != 0){
                return second;
            }else {
                return Math.min(first, second);
            }


        }
    }


