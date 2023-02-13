import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator2 {

    int addCalledCount;

    public int Add(String numbers) {

        int sum = 0;
        GetCalledCount();

        if (numbers.equals("")) {
            return 0;
        }

        String[] numbersArr;

        numbersArr = splitIntoArr(numbers);

        sum = calculateFromArr(numbersArr);

        return sum;
    }

    public String[] splitIntoArr(String numbers) {

        String[] numbersArr;

        if (!numbers.startsWith("//")) {

            numbersArr = numbers.split("[^\\d-]+");

            return numbersArr;
        }

        String delimiter = findDelimiter(numbers);

        String afterNewLine = splitNewLine(numbers);

        System.out.println(afterNewLine);
        System.out.println(delimiter);



        numbersArr = afterNewLine.split(delimiter);

        return numbersArr;

    }

    public int calculateFromArr(String[] numbersArr) {
        int sum = 0;
        int size = numbersArr.length;
        int[] intArr = new int[size];
    
        for (int i = 0; i < size; i++) {
            try {
                intArr[i] = Integer.parseInt(numbersArr[i]);
                if (intArr[i] < 0) {
                    throw new Exception("Negatives not allowed: " + intArr[i]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return -1;
            }
        }
    
        for (int i = 0; i < size; i++) {
            if (intArr[i] > 1000) {
                continue;
            }
            sum += intArr[i];
        }
    
        return sum;
    }

    public String splitNewLine(String numbers) {
        String[] numbersArr = numbers.split("");

        int size = numbersArr.length;
        int index = 0;

        for (int i = 0; i < size; i++) {

            //System.out.println(numbersArr[i]);
            if (numbersArr[i].equals("\n")) {
                index = i + 1;
                System.out.println("found");
            }
        }
        System.out.println(index);
        String afterNewLine[] = Arrays.copyOfRange(numbersArr, index, size);

        return String.join("", afterNewLine);
    }

    public String findDelimiter(String numbers) {

        
        if (numbers.startsWith("//[")){

            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(numbers);
    
            String delimiters = "[//";
    
            while (matcher.find()) {
                delimiters = delimiters + matcher.group(1);
            }
    
            delimiters = delimiters + "]+";
    
            return delimiters;
        }

        

        return String.valueOf(numbers.charAt(2));

    }

    public int GetCalledCount() {
        return this.addCalledCount;
    }

    @Test
    public void Test() {

        StringCalculator2 sc = new StringCalculator2();

        assertEquals(sc.Add("1,2,3"), 6);
        assertEquals(sc.Add("3\n7"), 10);
        assertEquals(sc.Add("3\n7,6\n3"), 19);
        assertEquals(sc.Add("//;\n1;2"), 3);
        assertEquals(sc.Add("//;\n10000;2;5"), 7);
        assertEquals(sc.Add("//[***]\n1***2***3"), 6);
        assertEquals(sc.Add("//[**][%%]\n1**2%%3"), 6);
    }
}
