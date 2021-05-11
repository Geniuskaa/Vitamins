import org.opencv.core.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);


        //String s1 = new TextRecognizer().RecoText(); // Первый способ


        ConnectionToBD con = new ConnectionToBD(); // Подключение к БД
        con.runDB();
        //con.tableCreation();

        Image image1 = new Image(); // Второй способ
        image1.imageScanner();
        String s2 = image1.getText();

        //structureCleaner(s);
        //FoodValue fd1 = foodAndEnergyValueCleaner(s1);
        FoodValue fd2 = foodAndEnergyValueCleaner(s2);


        con.addProductData(fd2);



        System.out.println("______________________________________________________________________");
        //System.out.println(s);



        }

        public static void structureCleaner(String text){
            String scanedText = text.toLowerCase()
                    .replaceAll("[.|/|?|<|,|!|^|!|№|;|*|”|>|„|%|_|(|)|`|~|:|‹|›|-|‚|‘|\"|\\\\|'|«|—|#|»|@|$|©|°|®|&|=|…|’|і|\\[]", "");

            ArrayList<String> cleanedStructure = new ArrayList<>();

            if(scanedText.contains("состав")){
                String[] consistOf = scanedText.split(" ");
                System.out.println("Мы смогли считать состав!");

                int startPosition = -1;
                for(int i=0; i < consistOf.length; i++){
                    if(consistOf[i].equals("состав")){
                        startPosition = i;
                        break;
                    }
                }

                if(startPosition != -1){

                    for(int j = startPosition + 1; j < consistOf.length; j++){
                        String temp = consistOf[j].replaceAll("\\s+","");

                        if(temp.contains("пищев") || temp.contains("ценнос") || temp.contains("масса")){
                            break;
                        }

                        if(temp.length() > 2){
                            cleanedStructure.add(temp);
                        }
                    }

                }

            }else{
                System.out.println("Нам не удалось считать состав...");
                return;
            }

            for (String s : cleanedStructure) { //cleanedStructure
                System.out.println(s);
            }
            //return s;
        }

        public static FoodValue foodAndEnergyValueCleaner(String text){
            String scanedText = text.toLowerCase()
                    .replaceAll("[.|/|?|<|!|^|!|№|;|*|”|>|„|%|_|(|)|`|~|‹|›|-|‘|\"|\\\\|'|«|—|#|»|@|$|©|°|®|&|=|…|’|і|\\[]", "");

            ArrayList<String> cleanedFoodAndEnVal = new ArrayList<>();

            if(scanedText.contains("ценнос")){
                String[] fAndEnVal = scanedText.split(" ");
                System.out.println("Мы смогли считать пищевую и энергетическую ценность продукта!");

                int startPosition = -1;
                for(int i = 0; i < fAndEnVal.length; i++){
                    if(fAndEnVal[i].contains("ценнос")){
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
                System.out.println("Нам не удалось считать пищевую и энергетическую ценность продукта...");
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

//            for (String s : cleanedFoodAndEnVal) {
//                System.out.println(s);
//            }


            System.out.println(fv.toString());

            return fv;
        }

        public static FoodValue foodValueImprover(FoodValue f1, FoodValue f2){ // TODO: В работе
            FoodValue f3 = new FoodValue();

            return f3;
        }
    }


