import static org.junit.Assert.assertEquals;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class StringCalculator3 {
    public int Add(String numbers) {

        if (numbers == "") {
            return 0;
        }

        String[] nums;
        

        if (numbers.startsWith("//")) {
            
            String[] stringArray = numbers.split("\n", 2);
            String string1 = stringArray[0];
            String string2 = (stringArray.length > 1) ? stringArray[1] : "";
            nums = string2.split(findDelimiter(numbers));
            return calculate(nums);

        }

        nums = numbers.split("[,\n]");

        return calculate(nums);

    }

    public String findDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.+)\n");
            Matcher matcher = pattern.matcher(numbers);
            if (matcher.find()) {
                String delimiter = matcher.group(1);
                return "[" + Pattern.quote(delimiter) + "\n,]";
            }
        }
        return "[,\n]";
    }

    public int calculate(String[] nums) {

        int sum = 0;
        int size = nums.length;
        int[] intArr = new int[size];
    
        for (int i = 0; i < size; i++) {
            try {
                intArr[i] = Integer.parseInt(nums[i]);
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

    @Test
    public void forEmptyString() {
        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add(""), 0);

    }

    @Test
    public void commaDelimitedString() {

        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add("1,2,3"), 6);
        assertEquals(sc.Add("0,2,3"), 5);
        assertEquals(sc.Add("5,3,2"), 10);

    }
    
    @Test 
    public void newLineDelimitedString() {

        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add("1\n2"),3);
        assertEquals(sc.Add("1\n2,3,4"), 10);
    }

    @Test
    public void differentDelimiter() {

        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add("//;\n1;2"),3);
    }

    @Test
    public void valuesOver1000() {

        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add("//;\n1001;1;2"), 3);
        assertEquals(sc.Add("5000,1,2,10"), 13);
    }
}
