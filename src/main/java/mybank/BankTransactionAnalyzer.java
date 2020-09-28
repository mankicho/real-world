package mybank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class BankTransactionAnalyzer {
    public static void main(String[] args) throws IOException {
        String resources = "src/main/resources/";
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();

        final Path path = Paths.get(resources + file);
        final List<String> lines = Files.readAllLines(path); // 데이터를 읽어서

        BankStatementCSVParser csvParser = new BankStatementCSVParser(); // 파싱한다
        // 파싱하는 과정을 별도의 도메인으로 생성해서 처리하기때문에 메인 메서드에서는 파싱 방법을 알 필요가 없어졌다.

        List<BankTransaction> bankTransactions = csvParser.parseLinesFromCSV(lines);

        System.out.println(bankTransactions);

    }
}