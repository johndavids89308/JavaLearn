import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayDelimiter {

    public static  <T> List<T[]> arrayDelimiter(T[] arr, int size) {

        if (size <= 0)
            throw new IllegalArgumentException("Size must be > 0 : " + size);

        List<T[]> result = new ArrayList<T[]>();

        int from = 0;
        int to = Math.min(size, arr.length);

        while (from < arr.length) {
            T[] subArray = Arrays.copyOfRange(arr, from, to);
            from = to;
            to += size;
            if (to > arr.length) {
                to = arr.length;
            }
            result.add(subArray);
        }

        return result;
    }
}
