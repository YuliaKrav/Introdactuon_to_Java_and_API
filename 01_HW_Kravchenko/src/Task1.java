import java.util.Scanner;

public class Task1 {
//    3.	+Написать программу вычисления n-ого треугольного числа. url

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число n, для вычисления n-ого треугольного числа:");

        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("Для отрицательных чисел треугольное число посчитать не получится.");
        } else {
            System.out.println("Для n = " + n + " треугольное число = " + triangularNumber(n));
        }
    }

    public static int triangularNumber(int n) {
        int result = 0; //=  (n * (n + 1)) / 2;
        for (int i = 0; i <= n; i++) {
            result += i;
        }
        return (result);
    }
}
