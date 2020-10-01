package mybusiness;

@FunctionalInterface
public interface Condition {
    boolean evaluate(Facts facts);
}
