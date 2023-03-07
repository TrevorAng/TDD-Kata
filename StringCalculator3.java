import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculator3 {
    public int Add(String numbers) {

        if (numbers == "") {
            return 0;
        }

        String[] nums = numbers.split("[,\n]");
        int sum = 0;
        for (String num : nums) {
            sum += Integer.parseInt(num);
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
}
