package mybank;

import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        String fileName = "bank-data-simple.csv";
        final BankTransactionAnalyzer analyzer = new BankTransactionAnalyzer();
        final BankStatementParser parser = new BankStatementCSVParser();

        analyzer.analyze(fileName, parser);
    }
}
