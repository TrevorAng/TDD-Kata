import java.util.Scanner;

public class StringCalculator{
    public int Add(String numbers){
        int sum = 0;
        String[] numbersArr = numbers.split(",");

        int size = numbersArr.length;
        int [] intArr = new int [size];
        for(int i=0; i<size; i++) {
           intArr[i] = Integer.parseInt(numbersArr[i]);
        }

        for(int i = 0;i<size;i++){
            sum += intArr[i];
        }
        return sum;

    }

    public static void main (String[] args){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter a string of numbers:");

        String numbers = myObj.nextLine(); 
        myObj.close();
        StringCalculator sc = new StringCalculator();
        System.out.println(sc.Add(numbers));
    }
}
