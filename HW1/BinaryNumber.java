import java.util.Arrays;

//Name: Harishkumar Moothedathu
//CWID: 10450618
//Class: 570 A (HW1 Binary Number little-endian format)
public class BinaryNumber {

    private int data[];
    private boolean overflow;

    //This method will take a length and will create a binary number consisting of zeroes
    public BinaryNumber(int length) {

        data = new int[length];
        if (length == 0)
        {
            System.out.println("Length cannot be zero");
        }
        else {
            for (int i = 0; i < length; i++)
            {
                data[i] = 0;
            }
            System.out.println("A binary number consisting zeroes of length " + length + " has been created");
        }
    }

    //This method will take a string and will check if the input contains 1's and 0's if yes then a Binary
    //number will be created and stored in data[] array and the same will be printed.
    public BinaryNumber(String str) {

        int j = str.length();
        data = new int[j];
        boolean notBinary;
        notBinary = false;
        char ch=0;
        for (int k = 0; k < j; k++)
        {
            ch = str.charAt(k);
            if (ch == 48 || ch == 49) {
                data[k] = Character.getNumericValue(ch);
            }
            else{
                notBinary = true;
            }
        }
        if (notBinary){
            System.out.println("Number not a binary, Please enter proper number for further processing");
            data = new int[j];
            return;
        }
        else{
            System.out.println("Binary number created is ");
            for (int i = 0; i < j; i++)
            {
                System.out.print(data[i]);
            }
            System.out.println();
        }
    }

    //This method will provide the length of an array
    public int getLength() {

        return data.length;

    }

    //This method will provide the digit which is present at an index of the binary number array
    public int getDigit(int index){

        int i = getLength();
        if(index > (i - 1) || index < 0)
        {
            System.out.println("Array Index is out of Bounds");
            return -1;
        }
         return data[index];
    }

    //This method will convert the Binary number to decimal digit using Math function and will return the result
    public int toDecimal(){

        double digit = 0.0;
        int result;
        for(int l = 0; l < data.length; l++)
        {
            digit = digit + (data[l] * Math.pow(2.0,l));
        }
        result = (int)digit;
        return result;
    }

    //This method will shift the Binary number to right in a temp array and will add zeroes in that place
    //It will print the resultant
    public void shiftR(int amount){

        int total = data.length + amount;
        int[] temp = new int[total];
        int j = 0;
        System.out.println("The digit after shifting is ");
        for (int i = 0; i < amount; i++)
        {
            temp[i] = 0;
            System.out.print(temp[i]);
        }
        for(int i = amount; i < total; i++)
        {
            temp[i] = data[j];
            j++;
            System.out.print(temp[i]);
        }
        System.out.println();
    }

    //This method will add two Binary numbers one receiving the message and the other is given as parameter
    //only if the length is same else it will print a message. It will set the overflow flag if the addition
    //resultant has an overflow(carry) present.
    public void add(BinaryNumber aBinaryNumber){

        int carry = 0;
        clearOverflow();
        int sum[] = new int[data.length];
        int var = 0;
        if(data.length != aBinaryNumber.data.length)
        {
            System.out.println("Length not same, please provide input with same length.");
            return;
        }
        System.out.println("Addition result is");
        for(int i = 0; i < data.length; i++)
        {
            var = carry + data[i] + aBinaryNumber.data[i];
            switch (var){
                case 0: sum[i] = 0;
                        break;
                case 1: sum[i] = 1;
                        carry = 0;
                        break;
                case 2: sum[i] = 0;
                        carry = 1;
                        break;
                case 3: sum[i] = 1;
                        carry = 1;
                        break;
            }
            System.out.print(sum[i]);
            data[i] = sum[i];
        }
        System.out.println();
        if (carry == 1)
        {
            System.out.println("Carry is present");
            overflow = true;
        }
    }

    //This method will clear the Overflow flag
    public void clearOverflow(){

        overflow = false;
    }

    //This will print Overflow if Overflow flag is true else it will print the array to a string.
    public String toString(){

        if (overflow)
        {
            return "Overflow";
        }
        int i = getLength();
        char[] ch = new char[i];
        for(int j = 0;j < i;j++)
        {
            ch[j] =  (char) (data[j] + '0');
        }
        String str = new String(ch);
        return str;
    }

    //Main which was used for testing purpose commented out to satisfy UML diagram
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BinaryNumber bin  = new BinaryNumber(2);
        BinaryNumber bin1 = new BinaryNumber("1011");
        int a = bin1.getLength();
        System.out.println("The length of binary number is " +a);
        int b = bin1.getDigit(3);
        System.out.println(b);
        int c = bin1.toDecimal();
        System.out.println("The digit is " +c);
        bin1.shiftR(1);
        //bin1.add(new BinaryNumber("0111"));
        //bin1.clearOverflow();
        //BinaryNumber bin2 = new BinaryNumber("-10");
        String str = bin1.toString();
        System.out.println(str);
    }

}
