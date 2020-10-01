package mybusiness;

@FunctionalInterface
public interface Rule {
    void perform(Facts facts);
}
