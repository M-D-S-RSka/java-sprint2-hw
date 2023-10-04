public class Operation {
    String itemName;
    int quantity;
    int unitPrice;
    boolean isExpense;
    int yearlyAmount;

    Operation(String[] lineContents) {
        itemName = lineContents[0];
        isExpense = Boolean.parseBoolean(lineContents[1]);
        quantity = Integer.parseInt(lineContents[2]);
        unitPrice = Integer.parseInt(lineContents[3]);
    }

    Operation(String[] lineContents, boolean YearOper) {
        itemName = lineContents[0];
        yearlyAmount = Integer.parseInt(lineContents[1]);
        isExpense = Boolean.parseBoolean(lineContents[2]);

    }
}