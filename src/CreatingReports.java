import java.util.HashMap;
import java.util.ArrayList;

public class CreatingReports {
    Operation operation;
    ArrayList<String> months;

    void checkReports(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        if (yearlyReport.yearLoad && monthlyReport.monthLoad) {
            months = new ArrayList<>();
            months.add("Январь");
            months.add("Февраль");
            months.add("Март");

            double totalProfit = 0;
            double totalExpense = 0;
            HashMap<String, HashMap<String, Double>> monthlyTable1 = new HashMap<>();
            HashMap<String, Double> monthlyTable2;
            Operation yearlyOperations;

            for (String month : monthlyReport.monthList.keySet()) {
                int i = (months.indexOf(month) + 1);
                monthlyTable2 = new HashMap<>();
                ArrayList<String> monthList = monthlyReport.monthList.get(month);

                for (String trans : monthList) {
                    operation = new Operation(trans.split(","));
                    if (operation.isExpense) {
                        totalExpense += operation.quantity * operation.unitPrice;
                    } else {
                        totalProfit += operation.quantity * operation.unitPrice;
                    }
                }
                monthlyTable2.put("Доходы", totalProfit);
                monthlyTable2.put("Расходы", totalExpense);
                totalExpense = 0;
                totalProfit = 0;
                monthlyTable1.put("0" + i, monthlyTable2);
            }

            for (int j = 0; j < yearlyReport.yearList.size(); j++) {
                yearlyOperations = new Operation(yearlyReport.yearList.get(j).split(","), true);
                if (yearlyOperations.isExpense) {
                    monthlyTable2 = new HashMap<>(monthlyTable1.get(yearlyOperations.itemName));
                    if (!(yearlyOperations.yearlyAmount == monthlyTable2.get("Расходы"))) {
                        System.out.println("Не соответствует месяц " + yearlyOperations.itemName + " по расходам.");
                    }
                } else {
                    monthlyTable2 = new HashMap<>(monthlyTable1.get(yearlyOperations.itemName));
                    if (!(yearlyOperations.yearlyAmount == monthlyTable2.get("Доходы"))) {
                        System.out.println("Не соответствует месяц " + yearlyOperations.itemName + " по доходам.");
                    }
                }
            }
            System.out.println("Отчеты сверены");

        } else if (!yearlyReport.yearLoad && !monthlyReport.monthLoad) {
            System.out.println("Загрузите месячные и годовые данные.");
        } else if (!monthlyReport.monthLoad) {
            System.out.println("Загрузите месячные данные.");
        } else {
            System.out.println("Загрузите годовые данные.");
        }

    }

    void reportMonth(MonthlyReport monthlyReport) {
        if (!monthlyReport.monthLoad) {
            System.out.println("Загрузите месячные данные");
            return;
        }
        double sumProfit = 0;
        String maxProfit = "";
        double sumExpenses = 0;
        String maxExpenses = "";
        for (String month : months) {
            System.out.println(month);
            ArrayList<String> monthList = monthlyReport.monthList.get(month);

            for (String transaction : monthList) {
                operation = new Operation(transaction.split(","));
                if (operation.isExpense) {
                    double sumExp = operation.unitPrice * operation.quantity;
                    String maxExp = operation.itemName;
                    if (sumExp > sumExpenses) {
                        sumExpenses = sumExp;
                        maxExpenses = maxExp;
                    }
                } else {
                    double sumProf = operation.unitPrice * operation.quantity;
                    String maxProf = operation.itemName;
                    if (sumProf > sumProfit) {
                        sumProfit = sumProf;
                        maxProfit = maxProf;
                    }
                }
            }

            System.out.println("Прибыль составляет - " + sumProfit + ". Наибольший доход приносит - " + maxProfit);
            System.out.println("Расходы составляют - " + sumExpenses + ". Наибольший расход приносит - " + maxExpenses);
            sumProfit = 0;
            maxProfit = "";
            sumExpenses = 0;
            maxExpenses = "";
        }
    }

    void yearReport(YearlyReport yearlyReport) {
        if (!yearlyReport.yearLoad) {
            System.out.println("Загрузите годовые данные.");
            return;
        }

        double sumProfit = 0;
        double sumExpenses = 0;
        Operation monthlyProfit1;
        Operation monthlyProfit2;
        System.out.println("Рассматриваемый год - 2021");

        for (int i = 0; i < yearlyReport.yearList.size(); i = i + 2) {
            monthlyProfit1 = new Operation(yearlyReport.yearList.get(i).split(","), true);
            monthlyProfit2 = new Operation(yearlyReport.yearList.get(i + 1).split(","), true);
            if (monthlyProfit1.isExpense) {
                sumExpenses += monthlyProfit1.yearlyAmount;
                sumProfit += monthlyProfit2.yearlyAmount;
                int profit1 = monthlyProfit2.yearlyAmount - monthlyProfit1.yearlyAmount;
                System.out.println("За " + monthlyProfit1.itemName + " месяц прибыль составляет - " + profit1);
            } else {
                sumExpenses += monthlyProfit2.yearlyAmount;
                sumProfit += monthlyProfit1.yearlyAmount;
                int profit2 = monthlyProfit1.yearlyAmount - monthlyProfit2.yearlyAmount;
                System.out.println("За " + monthlyProfit1.itemName + " месяц прибыль составляет - " + profit2);
            }

            System.out.println("Среднегодовой расход составляет - " + sumExpenses / 3);
            System.out.println("Среднегодовой доход составляет - " + sumProfit / 3);
        }
    }
}