package mybusiness;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InspectorTest {
    @Test
    public void inspectOneConditionEvaluatesTrue() {
        final Facts facts = new Facts();
        facts.addFact("jobTitle", "CEO");
        final ConditionalAction action = new JobTitleCondition();
        final Inspector inspector = new Inspector(action);

        final List<Report> reportList = inspector.inspect(facts);
        assertEquals(1, reportList.size());
        assertEquals(true, reportList.get(0).isPositive());
    }

    class JobTitleCondition implements ConditionalAction {
        @Override
        public boolean evaluate(Facts facts) {
            return "CEO".equals(facts.getFact("jobTitle"));
        }

        @Override
        public void perform(Facts facts) {

        }
    }
}
