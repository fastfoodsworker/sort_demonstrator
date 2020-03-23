import java.util.Arrays;
import java.util.List;

public class Sort {

    private static int comparisons = 0;
    private static int rearrangements = 0;

    // all static methods
    private Sort(){}

    // sort methods public usage

    public static <T extends Comparable<T>> Output<T> insertionSort(T[] inputs) {
        // setup methods
        resetCountToZero();

        // short circuit switch
        if (inputs.length == 1) {
            return new Output<>(inputs, 0, 0);
        }

        inspectArray(inputs);

        //start of alg

        // loop to label element to be inserted
        for (int i = 0; i < inputs.length; i++) {
            // short circuit (assume first element is always sorted)
            if (i == 0){
                continue;
            }
            // loop for comparison of elements before element to be inserted
            for (int j = 0; j < i; j++) {
                // System.out.println(Arrays.toString(inputs) + "; " + inputs[i] + "; " + inputs[j]);
                if (compare(inputs[i], inputs[j])) {
                    // System.out.println("Compared: " + Arrays.toString(inputs) + "; " + inputs[i] + "; " + inputs[j]);
                    inputs = shiftToPosition(inputs, i, j);
                    break;
                }
            }
        }

        return new Output<>(inputs, comparisons, rearrangements);
    }

    public static <T extends Comparable<T>> Output<T> selectionSort(T[] inputs) {
        resetCountToZero();

        // short circuit switch
        if (inputs.length == 1) {
            return new Output<>(inputs, 0, 0);
        }

        inspectArray(inputs);

        // start alg
        // var as indicator to be sent to back
        int keyIndex;
        for (int i = inputs.length - 1; i > 0; i--) {
            keyIndex = 0;
            for (int j = 0; j < i; j++) {
//                System.out.println(Arrays.toString(inputs) + "; " + inputs[keyIndex] + "; " + inputs[i]);
                if (compare(inputs[keyIndex], inputs[j])) {
//                    System.out.println("Compared: " + Arrays.toString(inputs) + "; " + inputs[keyIndex] + "; " + inputs[i]);
                    keyIndex = j;
                }
            }
//            System.out.println("Rearranged: " + inputs[keyIndex] + ", " + inputs[i]);

            inputs = rearrange(inputs, keyIndex, i);

        }

        return new Output<T>(inputs, comparisons, rearrangements);
    }

    public static <T extends Comparable<T>> Output<T> bubbleSort(T[] inputs) {
        // setup methods
        resetCountToZero();

        // short circuit switch
        if (inputs.length == 1) {
            return new Output<>(inputs, 0, 0);
        }

        inspectArray(inputs);

        //start of alg
        for (int i = 0; i < inputs.length - 1; i++) {
            for (int j = 0; j < inputs.length - i - 1; j++) {
                // System.out.println(Arrays.toString(inputs) + ", " + inputs[j] + ", " + inputs[j + 1]);
                if (!compare(inputs[j], inputs[j + 1])) {
                    inputs = rearrange(inputs, j, j + 1);
                }
            }
        }

        return new Output<>(inputs, comparisons, rearrangements);
    }

    public static <T extends Comparable<T>> Output<T> mergeSort(T[] inputs) {
        // setup methods
        resetCountToZero();

        // short circuit switch
        if (inputs.length == 1) {
            return new Output<>(inputs, 0, 0);
        }

        inspectArray(inputs);

        //start of alg
        inputs = recursiveMergeSort(inputs);

        return new Output<>(inputs, comparisons, rearrangements);
    }

    // overloading sort methods for various types of arrays (also for public usage)

    public static <E extends Comparable<E>> Output<E> insertionSort(List<E> input) {
        return insertionSort((E[]) input.toArray());
    }

    public static <E extends Comparable<E>> Output<E> selectionSort(List<E> input) {
        return selectionSort((E[]) input.toArray());
    }

    public static <E extends Comparable<E>> Output<E> bubbleSort(List<E> input) {
        return bubbleSort((E[]) input.toArray());
    }

    public static <E extends Comparable<E>> Output<E> mergeSort(List<E> input) {
        return mergeSort((E[]) input.toArray());
    }

    // placeholder methods to simplify code
    // as these methods repeat a lot

    // to increment towards comparison
    private static <T extends Comparable<T>> boolean compare(T a, T b) {
        boolean result;

        // compares values; if a is less than b then return true

        if (a.compareTo(b) == 0) {
            result = false;
        }
        else if (a.compareTo(b) < 0) {
            result = true;
        }
        else if (a.compareTo(b) > 0) {
            result = false;
        }
        else {
            result = false;
        }

        // increment count
        comparisons++;
        return result;
    }

    // swaps value in two specified elements
    // to increment towards rearrangement
    private static <E> E[] rearrange(E[] list, int a, int b) {
        E temp = list[a];
        list[a] = list[b];
        list[b] = temp;

        //increment count
        rearrangements++;
        return list;
    }

    // to "insert" element towards position
    // (useful for some algs like insertion and selection sort)
    // to increment towards rearrangement
    private static <E> E[] shiftToPosition(E[] input, int elementToShift, int shiftPosition) {
        E key = input[elementToShift];
        for (int i = elementToShift; i > shiftPosition; i--) {
            input[i] = input[i - 1];
        }

        input[shiftPosition] = key;

        rearrangements++;
        return input;
    }

    // to reset count for every start of sort
    private static void resetCountToZero() {
        comparisons = 0;
        rearrangements = 0;
    }

    // inspect array to prevent errors
    private static <E> void inspectArray(E[] inputs) {
        // inspection before running alg.
        for (E element : inputs) {
            if (element == null) {
                throw new NullPointerException("Cannot sort array with null elements");
            }
        }

    }

    // a special exception for mergeSort algorithm
    // mergeSort is recursive hence special methods
    private static <T extends Comparable<T>> T[] recursiveMergeSort(T[] inputs) {
        T[] part1;
        T[] part2;
        if (inputs.length > 2) {
            double pivot = inputs.length / 2;
            // System.out.println("Pivot: " + pivot);
            // System.out.println("Split: " + Arrays.toString(Arrays.copyOfRange(inputs, 0, (int) Math.floor(pivot))) + ", " + Arrays.toString(Arrays.copyOfRange(inputs, (int) Math.floor(pivot) + 1, inputs.length - 1)));
            part1 = recursiveMergeSort(Arrays.copyOfRange(inputs, 0, (int) Math.floor(pivot)));
            part2 = recursiveMergeSort(Arrays.copyOfRange(inputs, (int) Math.floor(pivot) , inputs.length));
        }
        else if (inputs.length == 2) {
            // System.out.println("Split: " + Arrays.toString(Arrays.copyOfRange(inputs, 0, 1)) + ", " + Arrays.toString(Arrays.copyOfRange(inputs, 1, 2)));
            part1 = recursiveMergeSort(Arrays.copyOfRange(inputs, 0, 1));
            part2 = recursiveMergeSort(Arrays.copyOfRange(inputs, 1, 2));
        }
        else {
            return inputs;
        }
        return mergeArray(part1, part2);
    }

    private static <T extends Comparable<T>> T[] mergeArray(T[] part1, T[] part2) {

        T[] result = (T[]) new Comparable[part1.length + part2.length];

        int currentIndexPart1 = 0;
        int currentIndexPart2 = 0;
        int currentIndexMerged = 0;

        if (part1.length >= 2 || part2.length >= 2) {

            while (currentIndexMerged < result.length && currentIndexPart1 < part1.length && currentIndexPart2 < part2.length) {
                // System.out.println(Arrays.toString(part1));
                // System.out.println("Element to be compared: " + part1[currentIndexPart1]);
                // System.out.println(Arrays.toString(part2));
                // System.out.println("Element to be compared: " + part2[currentIndexPart2]);
                if (compare(part1[currentIndexPart1], part2[currentIndexPart2])) {
                    // System.out.println("Element to be inserted: " + part1[currentIndexPart1]);
                    result[currentIndexMerged] = part1[currentIndexPart1++];
                }
                else {
                    // System.out.println("Element to be inserted: " + part2[currentIndexPart2]);
                    result[currentIndexMerged] = part2[currentIndexPart2++];
                }
                currentIndexMerged++;
                rearrangements++;
            }
            if (currentIndexPart1 < part1.length) {
                for (T r : Arrays.copyOfRange(part1, currentIndexPart1, part1.length)) {
                    result[currentIndexMerged++] = r;
                    rearrangements++;
                }
            }
            if (currentIndexPart2 < part2.length) {
                for (T r : Arrays.copyOfRange(part2, currentIndexPart2, part2.length)) {
                    result[currentIndexMerged++] = r;
                    rearrangements++;
                }
            }
        }
        else if (part1.length == 1 && part2.length == 1) {
            // System.out.println("Only one element in each array");
            // System.out.println(Arrays.toString(part1));
            // System.out.println(Arrays.toString(part2));
            if (compare(part1[0], part2[0])) {
                result[0] = part1[0];
                result[1] = part2[0];
                rearrangements += 2;
            }
            else {
                result[1] = part1[0];
                result[0] = part2[0];
                rearrangements += 2;
            }
        }

        return result;
    }
}