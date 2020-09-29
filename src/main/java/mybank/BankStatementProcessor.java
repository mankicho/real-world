package mybank;

import mybank.extension.BankTransactionFilter;
import mybank.extension.BankTransactionSummarizer;
import mybank.extension.SummaryStatistics;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

// 계산 기능을 담당하는 도메인 클래스 생성
public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions; // 입출금 내역 목록은 불변 객체이다.

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

//    public double calculateTotalAmount() {
//        double total = 0.0d;
//        for (final BankTransaction bankTransaction : bankTransactions) {
//            total += bankTransaction.getAmount();
//        }
//        return total;
//    }

    /*
    개방 폐쇄 원칙 적용
     */

    public SummaryStatistics summaryTransactions() {
        final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream().mapToDouble(BankTransaction::getAmount).summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum(), doubleSummaryStatistics.getMax(),
                doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getAverage());
    }

    public double summarizeTransactions(final BankTransactionSummarizer summarizer) {
        double result = 0.0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            result = summarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    public double calculateTotalForCategory(final String category) {
        return summarizeTransactions((acc, bankTransaction) -> bankTransaction.getDescription().equals(category) ? acc + bankTransaction.getAmount() : acc);
    }

//    public BankTransaction findMaxAmountTransaction(LocalDate start, LocalDate end) {
//        double max = 0.0d;
//        BankTransaction result = null;
//        for (BankTransaction bankTransaction : bankTransactions) {
//            if (bankTransaction.getDate().compareTo(start) < 0 || bankTransaction.getDate().compareTo(end) > 0) {
//                continue;
//            }
//            if (bankTransaction.getAmount() > max) {
//                max = bankTransaction.getAmount();
//                result = bankTransaction;
//            }
//        }
//        return result;
//    }
//
//    public BankTransaction findMinAmountTransaction(LocalDate start, LocalDate end) {
//        double min = Double.MAX_VALUE;
//        BankTransaction result = null;
//        for (BankTransaction bankTransaction : bankTransactions) {
//            if (bankTransaction.getDate().compareTo(start) < 0 || bankTransaction.getDate().compareTo(end) > 0) {
//                continue;
//            }
//            if (bankTransaction.getAmount() < min) {
//                min = bankTransaction.getAmount();
//                result = bankTransaction;
//            }
//        }
//        return result;
//    }

    /*
    개방 폐쇄 원칙을 적용해 코드를 리팩터링 한다.
     */
    public List<BankTransaction> findTransactions(BankTransactionFilter filter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if (filter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bankTransaction -> bankTransaction.getAmount() > amount);
    }

    private Map<String, List<BankTransaction>> bankTransactionGrouping() {
        Map<String, List<BankTransaction>> group = new HashMap<>();

        for (BankTransaction bankTransaction : bankTransactions) {
            if (group.get(bankTransaction.getDescription()) == null) {
                group.put(bankTransaction.getDescription(), new ArrayList<>());
            }
            group.get(bankTransaction.getDescription()).add(bankTransaction);
        }
        return group;
    }

    public void printHistogramOfGroupedBankTransaction() {
        Map<String, List<BankTransaction>> group = bankTransactionGrouping();

        for (Map.Entry<String, List<BankTransaction>> entry : group.entrySet()) {
            double totalAmount = 0.0d;
            for (int i = 0; i < entry.getValue().size(); i++) {
                BankTransaction ent = entry.getValue().get(i);
                System.out.println(ent.getDescription() + " : " + ent);
                totalAmount += ent.getAmount();
            }
            System.out.print("total transaction number is " + entry.getValue().size());
            System.out.println(" and total amount is " + totalAmount);
            System.out.println("-----------------------------");
        }
    }
}
