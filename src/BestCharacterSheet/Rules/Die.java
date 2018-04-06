package BestCharacterSheet.Rules;

public class Die implements Rule{
    public final int size;

    public Die(int size){
        this.size = size;
    }

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
