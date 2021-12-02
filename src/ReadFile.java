import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {

    String[] fileY2021;
    String[] fileM202101;
    String[] fileM202102;
    String[] fileM202103;


    // считывает месячные отчёты и сохраняет в переменные dataM202101, dataM202102, dataM202103 класса MonthlyReport
    public void readFileM() {
        String[] lineContents;
        fileM202101 = ReadFile.readFileContentsOrNull("resources/m.202101.csv").split("\\n");
        for (String line : fileM202101) {
            lineContents = line.trim().split(",");
            MonthlyReport.dataM202101.add(lineContents);

        }
        fileM202102 = ReadFile.readFileContentsOrNull("resources/m.202102.csv").split("\\n");
        for (String line : fileM202102) {
            lineContents = line.trim().split(",");
            MonthlyReport.dataM202102.add(lineContents);

        }
        fileM202103 = ReadFile.readFileContentsOrNull("resources/m.202103.csv").split("\\n");
        for (String line : fileM202103) {
            lineContents = line.trim().split(",");
            MonthlyReport.dataM202103.add(lineContents);

        }

    }

    // считывает годовой отчёт и сохраняет в переменную dataY2021 класса YearlyReport
    public void readFileY() {
        String[] lineContents;
        fileY2021 = ReadFile.readFileContentsOrNull("resources/y.2021.csv").split("\\n");
        for (String line : fileY2021) {
            lineContents = line.trim().split(",");
            YearlyReport.dataY2021.add(lineContents);

        }
    }

    // метод для считывания данных из файлов
    public static String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
