package adrianromanski.reduction;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class MainReduction {

    public static int reduce(
            List<Integer> values,
            int valueIfEmpty,
            BinaryOperator<Integer> reduction) {

        int result = valueIfEmpty;
        for(int value : values) {
            result = reduction.apply(result, value);
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> ints1 = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> ints2 = Arrays.asList(5, 6, 7, 8, 9);

        BinaryOperator<Integer> op = (i1, i2) -> (i1 + i2) * (i1 + i2); // This one is not associative

        System.out.println("***************** (i1 + i2) * (i1 + i2) *****************");

        int reduction = reduce(ints, 0 , op);

        System.out.println("Reduction Single : " + reduction + " is corrupted");

        int reduction0_1 = reduce(ints1, 0 ,op);  // first core
        int reduction0_2 = reduce(ints2, 0 ,op);  // second core

        int reduction0_3 = reduce(Arrays.asList(reduction0_1, reduction0_2), 0, op); // Combine

        System.out.println("Reduction Parallel : " + reduction0_3 + " is corrupted"); // This one is corrupted

        System.out.println("***************** Integer::sum *****************");

        BinaryOperator<Integer> op1 = Integer::sum; // This one is associative

        int reduction1 = reduce(ints, 0 , op1);

        System.out.println("Reduction Single : " + reduction1 + " is NOT corrupted");

        int reduction1_1 = reduce(ints1, 0 ,op1);  // first core
        int reduction1_2 = reduce(ints2, 0 ,op1);  // second core

        int reduction1_3 = reduce(Arrays.asList(reduction1_1, reduction1_2), 0, op1); // Combine

        System.out.println("Reduction Parallel : " + reduction1_3 + " is NOT corrupted");

        System.out.println("***************** (i1 + i2) / 2 AVERAGE *****************");

        BinaryOperator<Integer> op2 = (i1, i2) -> (i1 + i2) / 2; // This one is not associative

        int reduction2 = reduce(ints, 0 , op2);

        System.out.println("Reduction Single : " + reduction2 + " is corrupted");

        int reduction2_1 = reduce(ints1, 0 ,op1);  // first core
        int reduction2_2 = reduce(ints2, 0 ,op1);  // second core

        int reduction2_3 = reduce(Arrays.asList(reduction2_1, reduction2_2), 0, op2); // Combine

        System.out.println("Reduction Parallel : " + reduction2_3 + " is corrupted");









    }

}
