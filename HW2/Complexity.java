public class Complexity {

    static int count = 1;

    public static void method1(int n){

        int counter = 0;
        System.out.println("****** Time Complexity for ******");
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                ++counter;
                System.out.println("Operation n2 "+counter );
            }
        }
    }

    public static void method2(int n){

        int counter = 0;
        System.out.println("****** Time Complexity for ******");
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                for(int k = 0; k < n; k++)
                {
                    ++counter;
                    System.out.println("Operation n3 "+counter);
                }
            }
        }
    }

    public static void method3(int n){

        int counter = 0;
        System.out.println("****** Time Complexity for ******");
        for(int i = 1; i < n; i = i*2)
        {
            ++counter;
            System.out.println("Operation log(n) "+counter);
        }
    }

    public static void method4(int n){

        int counter = 0;
        System.out.println("****** Time Complexity for ******");
        for(int j = 0; j < n; j++)
        {
            for(int i = 1; i < n; i = i*2)
            {
                ++counter;
                System.out.println("Operation nlog(n) "+counter);
            }
        }
    }

    public static  void method5(int n){

        int counter=0;
        System.out.println("****** Time Complexity for ******");
        for(n=n;n>2;n=(int)Math.sqrt(n))
        {
            ++counter;
            System.out.println(" Operation log(logn) "+counter);
        }
    }

//    public static void method5(int n){
//        int counter = 0;
//        for(double i = 1.0; i < n; i = Math.pow(2.0,n))
//        {
//            for(int j= 1; j < n; j = j*2)
//            {
//                ++counter;
//                System.out.println("Operation log log(n) "+counter);
//            }
//        }
//    }

//    public static void method6(int n){
//        if(n<= 0)
//        {
//            ++count;
//            System.out.println("method 2^n "+ count);
//            return;
//        }
//        if(n == 1)
//        {
//            ++count;
//            System.out.println("method 2^n "+ count);
//            method6(n-1);
//        }
//        if(n >= 2)
//        {
//            ++count;
//            System.out.println("method 2^n "+count);
//            method6(n-1);
//            ++count;
//            System.out.println("method 2^n "+count);
//            method6(n-2);
//
//        }
//    }

    public static int method6(int n){

        if(n == 0)
        {
            return count;
        }
        return (2 * method6(n-1));
    }

    public static void main(String[] args) {

        method1(3);
        method2(2);
        method3(3560);
        method4(13);
        method5(10);
        int result = method6(4);
        System.out.println("The Operation 2^n will run "+result +" many times for the given n.");
    }
}
