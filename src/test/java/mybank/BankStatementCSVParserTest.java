package mybank;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementCSVParserTest {

    BankStatementParser parser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = parser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30),
                -50, "Tesco");

        final double tolerance = 0.0d;

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void findMaxAndMinTransactionTest() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse("30-01-2017", formatter);
        LocalDate end = LocalDate.parse("05-02-2017",formatter);

        BankTransaction expectedMax = new BankTransaction(
                LocalDate.parse("01-02-2017",formatter), 6000, "Salary");
        BankTransaction expectedMin = new BankTransaction(
                LocalDate.parse("02-02-2017",formatter), -4000, "Rent");

        Path path = Paths.get("src/test/java/resources/bank-data-simple.csv");
        List<String> lines = Files.readAllLines(path);

        BankStatementProcessor processor = new BankStatementProcessor(parser.parseLinesFrom(lines));

        BankTransaction maxResult = processor.findMaxAmountTransaction(start, end);
        BankTransaction minResult = processor.findMinAmountTransaction(start, end);

        Assert.assertEquals(maxResult, expectedMax);
        Assert.assertEquals(minResult, expectedMin);
    }

    public void findMinTransactionTest() {

    }
}
