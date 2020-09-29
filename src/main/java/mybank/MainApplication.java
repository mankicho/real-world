package mybank;

import mybank.extension.HTMLExporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainApplication {
    public static void main(String[] args) throws IOException {
        String fileName = "bank-data-simple.csv";
        final BankTransactionAnalyzer analyzer = new BankTransactionAnalyzer();
        final mybank.BankStatementParser parser = new mybank.BankStatementCSVParser();

        String html = analyzer.analyze(fileName, parser, new HTMLExporter());

        System.out.println(html);
        File file = new File("src/main/resources/test.html");

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(html);
        bw.flush();
    }
}
