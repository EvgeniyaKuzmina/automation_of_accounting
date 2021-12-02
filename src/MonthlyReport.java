import java.util.ArrayList;

// класс содержит методы, которые помогают получить информацию из месячных отчётов
public class MonthlyReport  {

    static ArrayList<String[]> dataM202101 = new ArrayList<>();  //переменная для хранения отчёта за январь
    static ArrayList<String[]> dataM202102 = new ArrayList<>();  //переменная для хранения отчёта за февраль
    static ArrayList<String[]> dataM202103 = new ArrayList<>();  //переменная для хранения отчёта за март
    int sumExpense; // хранит сумму по расходам
    int sumIncome; // хранит сумму по доходам



    // выводит ответ на экран о результатах проверки месячных и годового отчётов
    public void printResultCheckedReports() {
        boolean checkMonth01 = false;
        boolean checkMonth02 = false;
        boolean checkMonth03 = false;

        for (int j = 1; j < YearlyReport.dataY2021.size() - 1; j++) {
            if (YearlyReport.dataY2021.get(j)[0].equals("01")) {
                sumExpense = totalSumExpense(dataM202101);
                sumIncome = totalSumIncome(dataM202101);
                if (checkReports(j)) {
                    checkMonth01 = true;
                }
            }
            if (YearlyReport.dataY2021.get(j)[0].equals("02")) {
                sumExpense = totalSumExpense(dataM202102);
                sumIncome = totalSumIncome(dataM202102);
                if (checkReports(j)) {
                    checkMonth02 = true;
                }
            }
            if (YearlyReport.dataY2021.get(j)[0].equals("03")) {
                sumExpense = totalSumExpense(dataM202103);
                sumIncome = totalSumIncome(dataM202103);
                if (checkReports(j)) {
                    checkMonth03 = true;
                }
            }
            if (checkMonth01 && checkMonth02 && checkMonth03) {
                    System.out.println("Сверка прошла успешно");
            }
        }
    }

    // сверяет данные за каждый месяц из месячного отчёта с данными за этот же месяц в годовом отчёте
    public boolean checkReports(int j) {
        if (YearlyReport.dataY2021.get(j)[0].equals(YearlyReport.dataY2021.get(j + 1)[0])) {
            if (checkExpensesAndProfitsBetweenMonthAndYear(j)) {
                return true;
            } else {
                System.out.printf("Несоответствие: месяц —  %s\n", YearlyReport.dataY2021.get(j)[0]);
                return false;
            }
        }
        return false;
    }

    //проверяет, соответствует ли сумма расходов и доходов в месячном отчёте расходам и доходам в годовом отчёте
    public boolean checkExpensesAndProfitsBetweenMonthAndYear(int j) {
        boolean expensesIsCorrect = false;
        boolean profitIsCorrect = false;
        if (Boolean.parseBoolean(YearlyReport.dataY2021.get(j)[2])) {
            if (sumExpense == Integer.parseInt(YearlyReport.dataY2021.get(j)[1])) {
                expensesIsCorrect = true;
                if (sumIncome == Integer.parseInt(YearlyReport.dataY2021.get(j + 1)[1])) {
                    profitIsCorrect = true;
                }
            }
        } else {
            if (sumIncome == Integer.parseInt(YearlyReport.dataY2021.get(j)[1])) {
                profitIsCorrect = true;
                if (sumExpense == Integer.parseInt(YearlyReport.dataY2021.get(j + 1)[1])) {
                    expensesIsCorrect = true;
                }
            }
        }
        return expensesIsCorrect && profitIsCorrect;
    }

    // получает итоговую сумму по расходам за месяц
    public int totalSumExpense(ArrayList<String[]> data) {
        int sumExpense = 0;
        for (String[] row : data) {
            if (row[1].equals("TRUE")) {
                sumExpense += Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
            }
        }
        return sumExpense;
    }

    // получает итоговую сумму по доходам за месяц
    public int totalSumIncome(ArrayList<String[]> data) {
        int sumIncome = 0;
        for (String[] row : data) {
            if (row[1].equals("FALSE")) {
                sumIncome += Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
            }
        }
        return sumIncome;
    }

    // выводит информацию о всех месячных отчётах
    public void reportInformation() {
            System.out.println("Январь:");
            System.out.println(maxProfitableProduct(dataM202101));
            System.out.println(maxExpense(dataM202101));
            System.out.println("Февраль:");
            System.out.println(maxProfitableProduct(dataM202102));
            System.out.println(maxExpense(dataM202102));
            System.out.println("Март:");
            System.out.println(maxProfitableProduct(dataM202103));
            System.out.println(maxExpense(dataM202103));
    }

    // находит самый прибыльный товар в месяце
    public String maxProfitableProduct(ArrayList<String[]> data) {
        int max = 0;
        String name = null;
        for (String[] row : data) {
            if (row[1].equals("FALSE") && max < (Integer.parseInt(row[2]) * Integer.parseInt(row[3]))) {
                max = Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
                name = row[0];
            }
        }
        return String.format("Самый прибыльный товар: %s — %d", name, max);
    }

    // находит самый затратный товар в месяце
    public String maxExpense(ArrayList<String[]> data) {
        int max = 0;
        String name = null;
        for (String[] row : data) {
            if (row[1].equals("TRUE") && max < (Integer.parseInt(row[2]) * Integer.parseInt(row[3]))) {
                max = Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
                name = row[0];
            }
        }
        return String.format("Самая большая трата: %s — %d", name, max);
    }

}



