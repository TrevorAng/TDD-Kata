import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculator3 {
    public int Add(String numbers){
        
        if (numbers == "") {
            return 0;
        }

    

        return 0;

    }

    @Test
    public void forEmptyString() {
        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add(""), 0);
    
    }

    @Test
    public void commaDelimitedString () {

        StringCalculator3 sc = new StringCalculator3();
        assertEquals(sc.Add("1,2,3"),6);

    }

}
