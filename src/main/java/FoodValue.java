public class FoodValue {
    public double fats;
    public double proteins;
    public double carbohydrates;
    public double sucrose;

    public double powerValueCcal;


    public FoodValue() {
    }

    public void stringToDoubleFats(String fats){
        String temp = fats.replaceAll(",",".");
        double answer;
        try {
            answer = Double.parseDouble(temp);
        }catch(NumberFormatException n){
            answer = Double.NEGATIVE_INFINITY; // double.infinity
        }

        this.fats = answer;
    }

    public void stringToDoubleProteins(String proteins){
        String temp = proteins.replaceAll(",",".");
        double answer;
        try {
            answer = Double.parseDouble(temp);
        }catch (NumberFormatException n){
            answer = Double.NEGATIVE_INFINITY;
        }

        this.proteins = answer;
    }

    public void stringToDoubleCarbohydrates(String carbohydrates){
        String temp = carbohydrates.replaceAll(",",".");
        double answer;
        try {
            answer = Double.parseDouble(temp);
        }catch (NumberFormatException n){
            answer = Double.NEGATIVE_INFINITY;
        }
        this.carbohydrates = answer;
    }

    public void stringToDoubleSucRose(String sucrose){
        String temp = sucrose.replaceAll(",",".");
        double answer;
        try {
            answer = Double.parseDouble(temp);
        }catch (NumberFormatException n){
            answer = Double.NEGATIVE_INFINITY;
        }

        this.sucrose = answer;
    }

    public void stringToDoublePowerValueCcal(String powerValueCcal){
        String temp = powerValueCcal.replaceAll(",",".");
        double answer;
        try {
            answer = Double.parseDouble(temp);
        }catch (NumberFormatException n){
            answer = Double.NEGATIVE_INFINITY;
        }

        this.powerValueCcal = answer;
    }

    @Override
    public String toString() {
        return "FoodValue{" +
                "fats=" + fats +
                ", proteins=" + proteins +
                ", carbohydrates=" + carbohydrates +
                ", sucrose=" + sucrose +
                ", powerValueCcal=" + powerValueCcal +
                '}';
    }
}
