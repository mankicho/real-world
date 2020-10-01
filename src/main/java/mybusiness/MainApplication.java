package mybusiness;

public class MainApplication {
    public static void main(String[] args) {
        Facts f = new Facts();
        f.addFact("jobTitle", "CEO");
        f.addFact("name","");
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(f);
        Rule rule = new RuleBuilder().when(facts -> facts.getFact("jobTitle").equals("CEO"))
                .then(facts -> System.out.println("email transfer")).createRule();

        businessRuleEngine.addRule(rule);
        businessRuleEngine.run();
    }

}
