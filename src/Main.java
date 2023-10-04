import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();
        CreatingReports creatingReports = new CreatingReports();

        System.out.println("Вас приветствует программа расчета!");
        System.out.println("Придумайте и введите пароль из цифр для завершения работы программы(не менее 2 цифр)");
        int password = scanner.nextInt();

        while (true) {
            printMenu();
            int command = scanner.nextInt();

            if (command == 1) {
                monthlyReport.loadMonth(fileReader);
            } else if (command == 2) {
                yearlyReport.loadYear(fileReader);
            } else if (command == 3) {
                creatingReports.checkReports(yearlyReport, monthlyReport);
            } else if (command == 4) {
                creatingReports.reportMonth(monthlyReport);
            } else if (command == 5) {
                creatingReports.yearReport(yearlyReport);
            } else if (command == password) {
                System.out.println("До скорых встреч!");
                return;
            } else {
                System.out.println("Вы ошиблись кнопкой, повторим :)");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что Вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты;");
        System.out.println("2 - Считать годовой отчёт;");
        System.out.println("3 - Сверить отчёты;");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах;");
        System.out.println("5 - Вывести информацию о годовом отчёте;");
        System.out.println();
        System.out.println("Для выхода из программы введите установленный Вами пароль");
    }
}