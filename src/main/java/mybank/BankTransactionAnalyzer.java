package mybank;

import mybank.extension.Exporter;
import mybank.extension.SummaryStatistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.Scanner;

public class BankTransactionAnalyzer {
    private String resources = "src/main/resources/";

    private void collectSummary(BankStatementProcessor processor) {
        System.out.println("total amount = " + processor.summarizeTransactions((acc, transaction) -> acc + transaction.getAmount()));
        System.out.println("total amount of January = " + processor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("total amount of Tesco = " + processor.calculateTotalForCategory("Tesco"));
    }

    public String analyze(final String fileName, final BankStatementParser parser, Exporter exporter) throws IOException {
        final Path path = Paths.get(resources + fileName);
        final List<String> lines = Files.readAllLines(path); // 데이터를 읽어서

        final List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);
        final BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);

        SummaryStatistics statistics = processor.summaryTransactions();

        return exporter.export(statistics);
    }

}