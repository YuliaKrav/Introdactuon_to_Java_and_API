import java.util.HashMap;
import java.util.Stack;

public class Task12 {
    //    12.	*+Написать программу, определяющую правильность расстановки скобок в выражении.
//            Пример 1: a+(d*3) - истина
//    Пример 2: [a+(1*3) - ложь
//    Пример 3: [6+(3*3)] - истина
//    Пример 4: {a}[+]{(d*3)} - истина
//    Пример 5: <{a}+{(d*3)}> - истина
//    Пример 6: {a+]}{(d*3)} - ложь

    public static void main(String[] args) {

//        String inputString = "a+(d*3)"; // истина
//        String inputString = "[a+(1*3)"; // ложь
//        String inputString = "[6+(3*3)]"; // истина
//        String inputString = "{a}[+]{(d*3)}"; // истина
//        String inputString = "<{a}+{(d*3)}>"; // истина
        String inputString = "{a+]}{(d*3)}"; // ложь
//        String inputString = ""; // ложь

        String regex = "[^()\\[\\]<>{}]";
        Stack<Character> openParenthesesStack = new Stack<>();

        HashMap<Character, Character> parenthesesPairs = new HashMap<>();
        parenthesesPairs = fillHashSet();

        String onlyParentheses = inputString.replaceAll(regex, "");

        System.out.println(onlyParentheses);

        boolean answer = onlyParentheses == "" ? false : true;
        for (int i = 0; i < onlyParentheses.length() && answer; i++) {
            char symbol = onlyParentheses.charAt(i);

            if (!parenthesesPairs.containsKey(symbol)) {
                openParenthesesStack.push(symbol);
            } else {
                answer = ifIsPair(openParenthesesStack, parenthesesPairs, symbol);
            }
//            switch (symbol) {
//                case '(':
//                case '<':
//                case '{':
//                case '[': {
//                    openParenthesesStack.push(symbol);
//                    break;
//                }
//                case ')':
//                case '>':
//                case '}':
//                case ']':
//                    answer = ifIsPair(openParenthesesStack, parenthesesPairs, symbol);
//            }
        }

        String answerString = answer && openParenthesesStack.isEmpty() ? "правильно" : "не правильно";
        System.out.println("В выражении " + inputString + " скобки расставлены " + answerString);
    }

    public static HashMap<Character, Character> fillHashSet() {
        HashMap<Character, Character> resultHashMap = new HashMap<>();

        resultHashMap.put(')', '(');
        resultHashMap.put('}', '{');
        resultHashMap.put(']', '[');
        resultHashMap.put('>', '<');

        return resultHashMap;
    }

    public static boolean ifIsPair(Stack<Character> openParenthesesStack, HashMap<Character, Character> parenthesesPairs, Character symbol) {

        return (!openParenthesesStack.isEmpty()) && openParenthesesStack.pop() == parenthesesPairs.get(symbol) ? true : false;
    }
}

