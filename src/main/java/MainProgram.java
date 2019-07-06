import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    private ArrayList <String> cities = new ArrayList<>();
    public static void main(String[] args) {
        Calculation calculation = new Calculation();
                calculation.findCheapRefill(Calculation.Cities.valueOf(new MainProgram().readConsole()));
        }


    public  String readConsole (){

        System.out.println("Выберите название города из списка и введите его в консоль: ");
        for (Calculation.Cities x : Calculation.Cities.values()){
            cities.add(x.getNameOfCity().toLowerCase());
            System.out.print(x.ordinal()+ ": " + x.getNameOfCity() + ", ");
        }
        System.out.println();

        String customerCity = getCity();

        while (!cities.contains(customerCity)){
            System.out.println(" Проверьте правильность ввода ");
            customerCity = getCity();
        }
        return customerCity;
    }

    private String getCity (){
        String city = null;
        Scanner scanner = new Scanner(System.in);
        String element = scanner.nextLine();

        while (element.equals("")) {
            element = scanner.nextLine();
        }
        city = element;
        return city.toLowerCase();
    }


}
