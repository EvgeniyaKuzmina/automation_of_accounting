import java.util.Scanner;

public class Main {
    static String command;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReadFile readFile = new ReadFile();
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        /* Использовала в коде enum по вашему совету, но честно говоря мне не зашло. :) Я не смогла додуматься,
        как использовать и enum и switch case вместе, поэтому с enum я применила if — else, но такая конструкция
        кажется громоздкой на мой взгляд. Я бы предпочла оставить в решении старый вариант со switch case */
        while(true) {
            printMenu();
            command = scanner.next();
            if (command.equals(MenuOptions.ONE.getNumber())) {
                readFile.readFileM();
                System.out.println("Месячные отчёты считаны");
                System.out.println(" ");
            } else if (command.equals(MenuOptions.TWO.getNumber())) {
                readFile.readFileY();
                System.out.println("Годовой отчёт считан");
                System.out.println(" ");
            } else if (command.equals(MenuOptions.THREE.getNumber())) {
                if (yearlyReport.dataY2021.size() == 0 || monthlyReport.dataM202101.size() == 0) {
                    System.out.println("Вы забыли считать отчёт");
                } else {
                    monthlyReport.printResultCheckedReports();
                }
                System.out.println(" ");
            } else if (command.equals(MenuOptions.FOUR.getNumber())) {
                if (monthlyReport.dataM202101.size() == 0) {
                    System.out.println("Вы забыли считать отчёт");
                } else {
                    monthlyReport.reportInformation();
                }
                System.out.println(" ");
            } else if (command.equals(MenuOptions.FIVE.getNumber())) {
                if (yearlyReport.dataY2021.size() == 0) {
                    System.out.println("Вы забыли считать отчёт");
                } else {
                    yearlyReport.reportInformation();
                }
                System.out.println(" ");
            } else if (command.equals(MenuOptions.ZERO.getNumber())) {
                scanner.close();
                break;
            } else {
                System.out.println("Такой команды нет! Выберите один из вариантов: 1, 2, 3, 4 или 5");
                System.out.println(" ");
            }

        }
    }

    // вывод меню для пользователя
    public static void printMenu() {
        System.out.println("Выберите один из вариантов: 1, 2, 3, 4, 5 или 0:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("0. Выход");

    }
}

