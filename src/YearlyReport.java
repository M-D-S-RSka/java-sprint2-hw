import java.util.ArrayList;

public class YearlyReport {
    ArrayList<String> yearList = new ArrayList<>();
    boolean yearLoad = false;

    void loadYear(FileReader fileReader) {
        if (yearLoad) {
            System.out.println("Данные уже загружены");
        } else {
            if (fileReader.readFileContents("y.2021.csv").isEmpty()) {
                System.out.println("Файл y.2021.csv отсутствует.");
                return;
            }
            yearList = fileReader.readFileContents("y.2021.csv");
            yearList.remove(0);
            yearLoad = true;
            System.out.println("Данные считаны.");
        }
    }
}