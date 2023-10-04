import java.util.HashMap;
import java.util.ArrayList;

public class MonthlyReport {
    HashMap<String, ArrayList<String>> monthList = new HashMap<>();
    boolean monthLoad = false;
    String[] month = {"Январь", "Февраль", "Март"};

    void loadMonth(FileReader fileReader) {
        if (monthLoad) {
            System.out.println("Данные уже загружены");
        } else {
            for (int i = 1; i < 4; i++) {
                if (fileReader.readFileContents("m.20210" + i + ".csv").isEmpty()) {
                    System.out.println("Файл m.20210" + i + ".csv отсутствует.");
                    return;
                }
                monthList.put(month[i - 1], fileReader.readFileContents("m.20210" + i + ".csv"));
                monthList.get(month[i - 1]).remove(0);
            }
            monthLoad = true;
            System.out.println("Данные считаны.");
        }
    }
}