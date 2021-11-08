package Lesson4;

public class Functions {
    public static boolean isNumberPrime(Integer number) {
        if (number <= 0) return false;

        if (number <= 2) return true;

        for (int i = 2; i < number - 1; i++) {
            if (number % i == 0) return false;
        }

        return true;
    }

    //123321
    //12321
    //1
    public static boolean isPalindrome(String word) {
        if (word.length() < 2) return true;

        if (word.charAt(0) != word.charAt(word.length() - 1)) return false;
//123321
        //2332
        //33
        //""
        return isPalindrome(word.substring(1, word.length() - 1));
    }
}
