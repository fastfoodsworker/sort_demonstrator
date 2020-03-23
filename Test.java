import java.security.SecureRandom;
import java.util.Arrays;

public class Test {
    private static final int TEST_COUNT = 1;
    private static final int ARRAY_LENGTH = 50;
    private static final int RANDOM_NUMBER_BOUND = 1000;

    public static Output mainMethod() {
        Integer[] array = new Integer[ARRAY_LENGTH];
        SecureRandom r = new SecureRandom();

        for (int i = 0; i < ARRAY_LENGTH; i++) {
            array[i] = r.nextInt(RANDOM_NUMBER_BOUND);
        }
        System.out.println(Arrays.toString(array));

        Output output = Sort.mergeSort(array);

        System.out.println(output.toString());

        return output;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[] comparisons = new int[TEST_COUNT];
        int[] rearrangements = new int[TEST_COUNT];

        int totalComparisons = 0;
        int totalRearrangements = 0;

        Output<Integer> output;

        for (int i = 0; i < TEST_COUNT; i++) {
            output = mainMethod();
            comparisons[i] = output.getComparisons();
            rearrangements[i] = output.getRearrangements();
        }
        for (int comparison : comparisons) {
            totalComparisons += comparison;
        }
        for (int rearrangement : rearrangements) {
            totalRearrangements += rearrangement;
        }
        System.out.println("Total comparisons: " + totalComparisons);
        System.out.println("Total rearrangements: " + totalRearrangements);
        float averageComparisons = (float) totalComparisons / TEST_COUNT;
        float averageRearrangements = (float) totalRearrangements / TEST_COUNT;
        System.out.println("Average comparisons: " + averageComparisons + "\nAverage rearrangements: " + averageRearrangements);
        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime);
        System.out.println("Time elapsed: " + (double) elapsedTime / 1000000000 + " secs / " + elapsedTime + " nanoseconds");
    }
}
