package mybank;

// 논리 응집 클래스. CSV , JSON, XML 등 다양한 자료를 파싱하는 클래스
// BankTransactionParser 는 CSV,JSON,XML 를 파싱하는 3가지의 책임을 가진다. 단일 책임 원칙 위반. 권장하지 않음
public class BankTransactionParser {
    public BankTransaction parseFromCSV(final String line) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromJSON(final String line) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction parseFromXML(final String line) {
        throw new UnsupportedOperationException();
    }
}
