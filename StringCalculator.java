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

    public void main(String[] args){
        System.out.println(Add("1,2"));
    }
}
