package BestCharacterSheet.Rules;

public class Die implements Rule{
    public int size;
    public Die(int size){
        this.size = size;
    }

    @Override
    public Rule copy() {
        return new Die(this.size);
    }
}
