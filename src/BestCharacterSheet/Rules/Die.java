package BestCharacterSheet.Rules;

/**
 * The Die Rule represents requesting the user to input the result of a die roll
 */
public class Die implements Rule {
    public final int size;

    /**
     * Creates a new Die Rule
     * @param size The size of the die that needs to be rolled (number of faces)
     */
    public Die(int size){
        this.size = size;
    }

    /**
     * Triggers an event where the user should input a value
     * @return The returned value as an Int Rule
     */
    @Override
    public Rule execute() {
        // trigger View input event
        Integer resultOfView = 0; //TODO
        return new Int(resultOfView);
    }

    @Override
    public Rule copy() {
        return new Die(this.size);
    }
}
