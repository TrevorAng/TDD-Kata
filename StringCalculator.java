import java.util.Scanner;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringCalculator{
    public int Add(String numbers){
        int sum = 0;
        numbers = "0," + numbers;
        boolean negativeCheck = false;
        ArrayList<Integer> negativeNum = new ArrayList<>();
   
        String[] numbersArr = numbers.split("[^\\d-]+");
        int size = numbersArr.length;
        int [] intArr = new int [size];
        for(int i=0; i<size; i++) {
           intArr[i] = Integer.parseInt(numbersArr[i]);
        }

        for(int i = 0;i<size;i++){
            if (intArr[i]<0){
                negativeCheck = true;
                negativeNum.add(intArr[i]); 
            }

            if (intArr[i]>1000){
                intArr[i] = 0;
            }

            sum += intArr[i];
        }

        if (negativeCheck){
            System.out.println(negativeNum);
            return -1;
        }
        else{
            return sum;
        }

    }



    public static void main (String[] args){

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a string of numbers:");

        String numbers = myObj.nextLine(); 
        myObj.close();
        StringCalculator sc = new StringCalculator();
        System.out.println(sc.Add(numbers));
    }

    @Test
    public void test(){
        StringCalculator sc = new StringCalculator();
        assertEquals(sc.Add("1,2,3"),6);
        assertEquals(sc.Add("3\n7"),10);
        assertEquals(sc.Add("3\n7,6\n3"),19);
        assertEquals(sc.Add("\\e3*g\n7,6\n3"),19);
        assertEquals(sc.Add("//;\n1;2"),3);
        assertEquals(sc.Add("//;\n10000;2,5"),7);
        assertEquals(sc.Add("//[***]\n1***2***3"),6);
        assertEquals(sc.Add("//[**][%%]\n1**2%%3"),6);

    }
} 
