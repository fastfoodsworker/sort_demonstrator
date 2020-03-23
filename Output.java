import java.util.Arrays;
import java.util.List;

// provides output for sort methods
// as the needed output is quite awkward
public class Output<E> {
    private final E[] array;
    private final int comparisons;
    private final int rearrangements;

    public Output (E[] array, int comparisons, int rearrangements) {
        this.array = array;
        this.comparisons = comparisons;
        this.rearrangements = rearrangements;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getRearrangements() {
        return rearrangements;
    }

    public E[] getArray() {
        return array;
    }

    @Override
    public String toString() {
        return "Result: " + Arrays.toString(getArray()) + "; Comparisons: " + getComparisons() + "; Rearrangements: " + getRearrangements();
    }
}
