import java.util.Scanner;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class StringCalculator {
    int addCalledCount;

    public int Add(String numbers) {
        this.addCalledCount += 1;
        int sum = 0;
        String[] numbersArr;
        String[] lines;

        if (numbers == "") {
            return 0;
        } else if (numbers.startsWith("//[")) {
            lines = numbers.split("\\\\n");
            String delimiter = findDelimiter(numbers);
            numbersArr = lines[1].split(delimiter);
        } else if (numbers.startsWith("//")) {
            lines = numbers.split("\\\\n");
            String delimiter = String.valueOf(numbers.charAt(2));
            System.out.println(delimiter);
            numbersArr = lines[1].split(delimiter);
        } else {
            numbersArr = numbers.split("[^\\d-]+");
        }

        // System.out.println(Arrays.asList(lines));
        System.out.println(Arrays.asList(numbersArr));

        int size = numbersArr.length;
        int[] intArr = new int[size];

        for (int i = 0; i < size; i++) {
            intArr[i] = Integer.parseInt(numbersArr[i]);
        }

        boolean negativeCheck = false;
        ArrayList<Integer> negativeNum = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (intArr[i] < 0) {
                negativeCheck = true;
                negativeNum.add(intArr[i]);
            }

            if (intArr[i] > 1000) {
                continue;
            }

            sum += intArr[i];
        }

        if (negativeCheck) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNum);
        }

        return sum;
    }

    public int GetCalledCount() {
        return this.addCalledCount;
    }

    public String findDelimiter(String numbers) {

        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(numbers);

        String delimiters = "[//";

        while (matcher.find()) {
            delimiters = delimiters + matcher.group(1);
        }

        delimiters = delimiters + "]+";

        return delimiters;
    }

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a string of numbers:");

        String numbers = myObj.nextLine();
        myObj.close();
        StringCalculator sc = new StringCalculator();
        System.out.println(sc.Add(numbers));
    }

    @Test
    public void test() {
        StringCalculator sc = new StringCalculator();

        assertEquals(sc.Add("1,2,3"), 6);
        assertEquals(sc.Add("3\n7"), 10);
        assertEquals(sc.Add("3\n7,6\n3"), 19);
        assertEquals(sc.Add("//;\n1;2"), 3);
        assertEquals(sc.Add("//;\n10000;2;5"), 7);
        assertEquals(sc.Add("//[***]\n1***2***3"), 6);
        assertEquals(sc.Add("//[**][%%]\n1**2%%3"), 6);
        assertEquals(sc.Add("//[delim1][delim2]\n"), 0);

    }
}
