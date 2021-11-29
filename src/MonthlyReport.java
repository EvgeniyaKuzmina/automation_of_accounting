import java.util.ArrayList;

public class MonthlyReport  {
    String[] lineContents;
    String[] fileM202101;
    String[] fileM202102;
    String[] fileM202103;
    ArrayList<String[]> dataM202101 = new ArrayList<>();
    ArrayList<String[]> dataM202102 = new ArrayList<>();
    ArrayList<String[]> dataM202103 = new ArrayList<>();
    int sumExpense;
    int sumIncome;


    public void readFileM() {
        fileM202101 = ReadFile.readFileContentsOrNull("resources/m.202101.csv").split("\\n");
        for (String line : fileM202101) {
            lineContents = line.trim().split(",");
            dataM202101.add(lineContents);

        }
        fileM202102 = ReadFile.readFileContentsOrNull("resources/m.202102.csv").split("\\n");
        for (String line : fileM202102) {
            lineContents = line.trim().split(",");
            dataM202102.add(lineContents);

        }
        fileM202103 = ReadFile.readFileContentsOrNull("resources/m.202103.csv").split("\\n");
        for (String line : fileM202103) {
            lineContents = line.trim().split(",");
            dataM202103.add(lineContents);

        }

    }

    public void checkReports() {
        boolean checkMonth01 = false;
        boolean checkMonth02 = false;
        boolean checkMonth03 = false;

            for (int j = 1; j < YearlyReport.dataY2021.size() - 1; j++) {
                if (YearlyReport.dataY2021.get(j)[0].equals("01")) {
                    sumExpense = totalSumExpense(dataM202101);
                    sumIncome = totalSumIncome(dataM202101);
                    if (checkReports(j, sumExpense, sumIncome)) {
                        checkMonth01 = true;
                    }
                }
                if (YearlyReport.dataY2021.get(j)[0].equals("02")) {
                    sumExpense = totalSumExpense(dataM202102);
                    sumIncome = totalSumIncome(dataM202102);
                    if (checkReports(j, sumExpense, sumIncome)) {
                        checkMonth02 = true;
                    }
                }
                if (YearlyReport.dataY2021.get(j)[0].equals("03")) {
                    sumExpense = totalSumExpense(dataM202103);
                    sumIncome = totalSumIncome(dataM202103);
                    if (checkReports(j, sumExpense, sumIncome)) {
                        checkMonth03 = true;
                    }
                }
                if (checkMonth01 && checkMonth02 && checkMonth03) {
                    System.out.println("Сверка прошла успешно");
                }
            }
        }

    public boolean checkReports(int j, int sumExpense, int sumIncome) {
        boolean expensesIsCorrect = false;
        boolean profitIsCorrect = false;
            if (YearlyReport.dataY2021.get(j)[0].equals(YearlyReport.dataY2021.get(j + 1)[0])) {
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
            if (expensesIsCorrect && profitIsCorrect) {
                return true;
            } else {
                System.out.printf("Несоответствие: месяц —  %s\n", YearlyReport.dataY2021.get(j)[0]);
                return false;
            }
        }
        return false;
    }

    public int totalSumExpense(ArrayList<String[]> data) {
        int sumExpense = 0;
        for (String[] row : data) {
            if (row[1].equals("TRUE")) {
                sumExpense += Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
            }
        }
        return sumExpense;
    }

    public int totalSumIncome(ArrayList<String[]> data) {
        int sumIncome = 0;
        for (String[] row : data) {
            if (row[1].equals("FALSE")) {
                sumIncome += Integer.parseInt(row[2]) * Integer.parseInt(row[3]);
            }
        }
        return sumIncome;
    }

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



