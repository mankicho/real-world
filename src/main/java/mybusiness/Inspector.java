package mybusiness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inspector {
    private final List<ConditionalAction> conditionalActions;

    public Inspector(final ConditionalAction... conditionalActions) {
        this.conditionalActions = Arrays.asList(conditionalActions);
    }

    public List<Report> inspect(Facts facts) {
        List<Report> reports = new ArrayList<>();
        for (ConditionalAction action : conditionalActions) {
            final boolean conditionResult = action.evaluate(facts);
            reports.add(new Report(facts, action, conditionResult));
        }
        return reports;
    }
}
