public class FoodValue {
    private double fats;
    private double proteins;
    private double carbohydrates;
    private double sucrose;

    private double powerValueCcal;


    public FoodValue() {
    }

    public FoodValue(double fats, double proteins, double carbohydrates, double sucrose, double powerValueCcal) {
        this.fats = fats;
        this.proteins = proteins;
        this.carbohydrates = carbohydrates;
        this.sucrose = sucrose;
        this.powerValueCcal = powerValueCcal;
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

    public double getFats() {
        return fats;
    }

    public double getProteins() {
        return proteins;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getSucrose() {
        return sucrose;
    }

    public double getPowerValueCcal() {
        return powerValueCcal;
    }
}
