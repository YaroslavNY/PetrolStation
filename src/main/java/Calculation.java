public class Calculation {

    public static void main(String[] args) {
   // findCheapRefill(Cities.california);
    }

    public enum Cities {

        idaho(0, "Idaho", 17, 3),
        newyork(1, "NewYork", 19, 5),
        lakegeorge(2, "Lake-George", 5, 12),
        alabama(3, "Alabama", 2, 9),
        oklahoma(4, "Oklahoma", 12,25),
        california (5, "California", 20,9),
        florida(6, "Florida", 5,1),
        utah (7,"Utah", 4, 2);


        private int id, z, c;
        private String name;

        Cities(int id, String name, int z, int c) {
            this.id = id;
            this.name = name;
            this.z = z;
            this.c = c;
        }

        public int getID() {
            return id;
        }

        public int getRoadCost() {
            return c;
        }

        public int getFuelCost() {
            return z;
        }

        public String getNameOfCity() {
            return name;
        }

        public void setRoadCost(int cost) {
            this.c = cost;
        }

        public void setFuelCost(int cost) {
            this.z = cost;
        }

        public void setNameOfCity(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return  getNameOfCity();
        }
    }

    public  void findCheapRefill (Cities city){
        int minForwardway = 0, minBackwardWay = 0, counterForward = 1, counterBackward = 1;
        int [][] matrixForward = createMatrixForward();
        int [][] originalMatrixForward = createMatrixForward();
        int [][] matrixBackWard = createMatrixBackward();
        int [][] originalMatrixBackward = createMatrixBackward();

        for (int i = 0; i < matrixForward.length; i++){
            for (int j = 0; j <matrixForward.length; j++){
                System.out.print(matrixForward[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < matrixBackWard.length; i++){
            for (int j = 0; j <matrixBackWard.length; j++){
                System.out.print(matrixBackWard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < matrixForward.length - 1; i++) {
            int catcher;
            if (matrixForward[city.getID()][i] < matrixForward[city.getID()][i + 1]) {
                minForwardway = matrixForward[city.getID()][i];
                catcher = matrixForward[city.getID()][i + 1];
                matrixForward[city.getID()][i + 1] = minForwardway;
                matrixForward[city.getID()][i] = catcher;
                counterForward++;
            }
            else {
                minForwardway = matrixForward[city.getID()][i+1];
            }
        }
        System.out.println("minForward = " + minForwardway);
        for (int i = 0; i < matrixForward.length - 1; i++) {
            int catcher;
            if (matrixBackWard[city.getID()][i] < matrixBackWard[city.getID()][i + 1]) {
                minBackwardWay = matrixBackWard[city.getID()][i];
                catcher = matrixBackWard[city.getID()][i + 1];
                matrixBackWard[city.getID()][i + 1] = minBackwardWay;
                matrixBackWard[city.getID()][i] = catcher;
                counterBackward++;
            }
            else {
                minBackwardWay = matrixBackWard[city.getID()][i+1];
            }

        }
        System.out.println("minBack = " + minBackwardWay);

        if (minForwardway < minBackwardWay){
            int counter = 0;
            for (int i = 0; i < matrixForward.length; i++){
                if (minForwardway == originalMatrixForward[city.getID()][i]){
                     counter = i;
                }
            }
            System.out.println("counter = " + counter);
            if (city.getID() == counter){
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в своем городе по цене: " + minForwardway);
            }
            else {
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в городе " + Cities.values()[counter].getNameOfCity() + " По цене: " + minForwardway + " в прямом направлении ");
            }
        }
        else if (minForwardway == minBackwardWay){
            int counter = 0;
            for (int i = 0; i < matrixForward.length; i++){
                if (minForwardway == originalMatrixForward[city.getID()][i]){
                    counter = i;
                }
            }
            System.out.println("counter = " + counter);

            if (city.getID() == counter){
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в своем городе по цене: " + minForwardway);
            }
            else {
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в городе " + Cities.values()[counter].getNameOfCity() + " По цене: " + minForwardway + " в любом направлении ");
            }
        }
        else {
            int counter = 0;
            for (int i = 0; i < matrixBackWard.length; i++){
                if (minBackwardWay == originalMatrixBackward[city.getID()][i]){
                     counter = i;
                }
            }
            System.out.println("counter = " + counter);
            if (city.getID() == counter){
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в своем городе по цене: " + minBackwardWay);
            }
            else {
                System.out.println("Жителям города " + city.getNameOfCity() + " дешевле заправиться в городе " + Cities.values()[counter].getNameOfCity() + " По цене: " + minBackwardWay + " в обратном направлении ");
            }
        }
    }

    public  int [][] createMatrixForward () {
         int wayCost;   // Создаем переменную wayCost которая будет хранить промежуточные стоимости пути
        int[][] costsMatrixForward = new int[Cities.values().length][Cities.values().length]; // Создаем матрицу массив который является матрицей стоимости пути + заправки в конкретном городе по часовой стрелке
        for (int i = 0; i < Cities.values().length; i++) {  // Пробегаемся по всем городам вдоль
            wayCost = 0;
            for (int j = 0; j < Cities.values().length; j++) {  // и поперек
                if (i == j) {   // Если диагональ (i) и вертикаль (j) совпадают, то просто указываем в ячейке стоимость заправки в своем же городе
                    costsMatrixForward[i][j] = Cities.values()[i].z;    //добавляем в массив стоимость заправки
//                    System.out.println(costsMatrixForward[i][j]);
                }
                else if (i == 0){  // Если мы заполняем первую строчку (i == 0) то потребуется только 1 прогон до конца массива
                    wayCost += Cities.values()[j-1].c;  // считаем стоимость пути, переменная динамически увеличивается при итерации
                    costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;  // Заносим в массив значение суммы пути до города j и стоимость заправки там
//                    System.out.println(costsMatrixForward[i][j]);
                }
                else if (i >= 1){   // Если город не является нулевым, но нужно дойти до конца массива а затем с начала до самого города, так мы посчитаем все пути
                    if (i > j) {    // Нужно определить, где находится искомый город,если он позади нашего (от куда мы стартуем) то нужно пройти круг и дойти до последнего непройденного города
                        for (int currentCity = i; currentCity < Cities.values().length; currentCity++) { // Считаем стоимость пути до искомого города
                            wayCost += Cities.values()[currentCity].c;
                        }
                        if (j > 0) {    // если искомый город не в самом начале, необходимо с самого начала посчитать стоимость пути к нему
                            for (int currentCity = 0; currentCity < j; currentCity++) {
                                wayCost += Cities.values()[currentCity].c;
                            }
                        }
                        costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;  // и сложить со стоимостью заправки
//                        System.out.println(costsMatrixForward[i][j]);
                        wayCost = 0;    // Обнуляем счетчик стоимости пути, чтобы не было ошибки
                    }
                    else {  // Если искомый город находится впереди, необходимо просто посчитать путь до него
                        for (int currentCity = i; currentCity < j; currentCity++){
                            wayCost += Cities.values()[currentCity].c;
                        }
                        costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;
//                        System.out.println(costsMatrixForward[i][j]);
                        wayCost = 0;
                    }
                }
            }
        }
        return costsMatrixForward;
    }

    public int [][] createMatrixBackward (){
        int wayCost;   // Создаем переменную wayCost которая будет хранить промежуточные стоимости пути
        int[][] costsMatrixForward = new int[Cities.values().length][Cities.values().length]; // Создаем матрицу массив который является матрицей стоимости пути + заправки в конкретном городе против часовой стрелке
        for (int i = Cities.values().length-1; i >= 0; i--){
            wayCost = 0;
            for (int j = Cities.values().length-1; j >= 0; j--){

                if (i == j) {   // Если диагональ (i) и вертикаль (j) совпадают, то просто указываем в ячейке стоимость заправки в своем же городе
                    costsMatrixForward[i][j] = Cities.values()[i].z;    //добавляем в массив стоимость заправки
//                    System.out.println(costsMatrixForward[i][j]);
                }
                else if (i == Cities.values().length-1) {
                    wayCost += Cities.values()[j].c;
                    costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;
//                    System.out.println(costsMatrixForward[i][j]);
                }
                else {
                        if (j > i) {
                            for (int currentCity = i; currentCity > 0; currentCity --){
                                wayCost += Cities.values()[currentCity - 1].c;
                            }
                            for (int currentCity = Cities.values().length -1; currentCity >= j; currentCity --){
                                wayCost += Cities.values()[currentCity].c;
                            }
                            costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;
//                            System.out.println(costsMatrixForward[i][j]);
                            wayCost = 0;
                        }
                        else {
                            for (int currentCity = i-1; currentCity >= j; currentCity --){
                                wayCost += Cities.values()[currentCity].c;
                            }
                            costsMatrixForward[i][j] = Cities.values()[j].z + wayCost;
//                            System.out.println(costsMatrixForward[i][j]);
                            wayCost = 0;
                        }
                }
            }
        }
        return costsMatrixForward;
    }
}