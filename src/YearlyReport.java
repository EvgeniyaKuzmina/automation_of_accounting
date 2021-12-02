import java.util.ArrayList;
import java.util.HashMap;

 // класс содержит методы, которые помогают получить информацию из годового отчёта
public class YearlyReport  {

    static ArrayList<String[]> dataY2021 = new ArrayList<>(); //переменная для хранения отчёта за год
    HashMap<String, Integer> profitReportYear = new HashMap<>(); //хранит информацию о прибыли по каждому месяцу полученной из годового отчёта

    // выводит пользователю информацию о годовом отчёте
    public void reportInformation() {
        System.out.println("год 2021:");
        profitByEachMonth(dataY2021);
        averageExpensesAndRevenue(dataY2021);
    }

    // находит прибыль по каждому месяцу в годовом отчёте, где прибыль это разность доходов и расходов
    public void profitByEachMonth(ArrayList<String[]> data) {
        int profit;
        for (int j = 1; j < data.size()-1; j++ ) {
            if (data.get(j)[0].equals(data.get(j + 1)[0])) {
                if (data.get(j)[2].equals("false")) {
                    profit = Integer.parseInt(data.get(j)[1]) - Integer.parseInt(data.get(j + 1)[1]);
                } else {
                    profit = Integer.parseInt(data.get(j + 1)[1]) - Integer.parseInt(data.get(j)[1]);
                }
            profitReportYear.put(data.get(j + 1)[0], profit);
            }
        }
       System.out.println("Прибыль по каждому месяцу:");
        for (String month : profitReportYear.keySet()) {
            System.out.println(month + " — " + profitReportYear.get(month));
        }
    }

    // находит средний расход за все месяцы и средний доход за все месяцы
    public void averageExpensesAndRevenue(ArrayList<String[]> data) {
        double sumExpenses = 0.0;
        int countExpenses = 0;
        double sumProfit = 0.0;
        int countProfit = 0;

        for (String[] row : data)    {
            if (row[2].equals("true")) {
                sumExpenses += Double.parseDouble(row[1]);
                ++countExpenses;
            }
            if (row[2].equals("false")) {
                sumProfit += Double.parseDouble(row[1]);
                ++countProfit;
            }
        }
        System.out.printf("Средний расход за все месяцы: %s\n", sumExpenses/countExpenses);
        System.out.printf("Средний доход за все месяцы: %s\n", sumProfit/countProfit);
    }
}
