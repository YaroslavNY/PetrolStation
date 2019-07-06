import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        ArrayList <String> cities = new ArrayList<>();
        Calculation calculation = new Calculation();
        System.out.println("Выберите название города из списка и введите его в консоль: ");
            for (Calculation.Cities x : Calculation.Cities.values()){
                System.out.print(x.ordinal()+ ": " + x.getNameOfCity() + ", ");
        }
        System.out.println();

        String customerCity = readConsole().toLowerCase();

        for (Calculation.Cities x : Calculation.Cities.values()){
            cities.add(x.getNameOfCity().toLowerCase());
        }

        while (!cities.contains(customerCity)){
            System.out.println(" Проверьте правильность ввода ");
            customerCity = readConsole();
        }
                calculation.findCheapRefill(Calculation.Cities.valueOf(customerCity));
        }

    public static String readConsole (){

        String city = null;

        Scanner scanner = new Scanner(System.in);
        String element = scanner.nextLine();

        while (element.equals("")) {
            element = scanner.nextLine();
        }
        city = element;
        return city;
    }


}
