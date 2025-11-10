import java.util.Scanner;

public class B1 {
    static {
        System.loadLibrary("B1");
    }

    
    public native int add(int num1, int num2);
    public native int sub(int num1, int num2);
    public native int mul(int num1, int num2);
    public native double div(int num1, int num2);

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DLL Example Operations");
        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        B1 obj = new B1();

        System.out.println("Addition : " + obj.add(num1, num2));
        System.out.println("Subtraction : " + obj.sub(num1, num2));
        System.out.println("Multiplication : " + obj.mul(num1, num2));
        System.out.println("Division : " + obj.div(num1, num2));

        scanner.close();
    }
}

// Command to run 

// java "-Djava.library.path=." B1