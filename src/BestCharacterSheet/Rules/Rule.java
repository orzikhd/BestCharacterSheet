package BestCharacterSheet.Rules;

/**
 * Rule interface represents the core behavior of any Rule
 */
public interface Rule {
    /**
     * Executes the rule. This can have the following consequences:
     * - Triggers a View event
     * - Alters the global Adventurer
     * - Returns a value that a parent rule is waiting on
     * Any combination of these can occur.
     * @return Rule that is a result of execution, to be passed to a parent Rule.
     * - This value can be null, representing no return value.
     */
    Rule execute();

    /**
     * Allows deep copying of a rule
     * @return Rule which is a deep copy of itself
     */
    Rule copy();
}
