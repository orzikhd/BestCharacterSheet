package BestCharacterSheet.Rules;

/**
 * All Rules must implement a copy routine to allow deep copying
 */
public interface Rule {
    Rule copy();
}
