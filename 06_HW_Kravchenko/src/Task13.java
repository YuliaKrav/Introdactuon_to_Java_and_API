import java.util.*;

public class Task13 {
    //    13.	**Написать программу вычисляющую значение сложного арифметического выражения. Для простоты - выражение всегда вычисляемое
//    Пример: (2^3 * (10 / (5 - 3)))^(Sin(Pi)) ответ - 1 -- без тригонометрических функций
    private static final char OPEN_PARENTHESES = '(';
    private static final char CLOSE_PARENTHESES = ')';
    private static final char OP_PLUS = '+';
    private static final char OP_SUB = '-';
    private static final char OP_MUL = '*';
    private static final char OP_DIV = '/';
    private static final char OP_POW = '^';

    public static void main(String[] args) {

//        String inputString = "2 ^ 2 ^ 1 ^ 0";
//        String inputString = "(13 + 6) ^ (4 * 7) + 2 ^ 8 * 9 + 3 / 3";
        String inputString = "(2 + 2) * 2 ^ 3 + 12 / 3 - 4";


        if (checkParenthheses(inputString)) {
            List<String> postfixInputList = fromInfixToPostfixNotation(inputString);

            System.out.println("\nInfix expression " + inputString + " = postfix expression " + postfixInputList);
            System.out.println(inputString + " = " + evaluateExpression(postfixInputList));

        } else {
            System.out.println("The parentheses are not correct in the given expression - " + inputString);
        }
    }

    public static List<String> fromInfixToPostfixNotation(String expressionString) {

        Map<Character, Integer> operatorsPriority = Map.of('^', 3, '*', 2, '/', 2, '+', 1, '-', 1, '(', 0);

        List<String> postfixExpression = new ArrayList<>();
        Deque<Character> operators = new ArrayDeque<>();

        char[] expression = deleteAllSpaces(expressionString).toCharArray();

        int currentPosition = 0;

        while (currentPosition < expression.length) {
            char currentSymbol = expression[currentPosition];

            if (Character.isDigit(currentSymbol)) {
                String numberString = getNumber(currentPosition, expression);
                postfixExpression.add(numberString);
                currentPosition += numberString.length() - 1;

            } else if (currentSymbol == OPEN_PARENTHESES) {
                operators.addLast(currentSymbol);

            } else if (currentSymbol == CLOSE_PARENTHESES) {
                while (operators.peekLast() != OPEN_PARENTHESES) {
                    postfixExpression.add(Character.toString(operators.pollLast()));
                }
                operators.removeLast();
//            } else if (currentSymbol == OP_POW) {
//                operators.addLast(currentSymbol);

            } else if (isMathOperator(currentSymbol)) {
                char currentOperator = currentSymbol;
                int currentPriority = operatorsPriority.get(currentOperator);
                while (!operators.isEmpty() && currentPriority < operatorsPriority.get(operators.peekLast())) {
                    postfixExpression.add(Character.toString(operators.pollLast()));
                }
                operators.addLast(currentSymbol);
            }
            currentPosition++;
        }

        while ((!operators.isEmpty())) {
            char operator = operators.pollLast();
            postfixExpression.add(Character.toString(operator));
        }

        return postfixExpression;
    }

    public static boolean isMathOperator(char symbol) {
        return (symbol == OP_POW || symbol == OP_MUL || symbol == OP_DIV || symbol == OP_PLUS || symbol == OP_SUB);
    }

    public static String getNumber(int index, char[] originalChars) {

        StringBuilder numberString = new StringBuilder();

        while (index < originalChars.length && Character.isDigit(originalChars[index])) {
            numberString.append(originalChars[index]);
            index++;
        }
        return numberString.toString();
    }

    public static String deleteAllSpaces(String originalString) {
        return originalString.replaceAll("\\s+", "");
    }

    public static int evaluateExpression(List<String> expressionList) {

        Set operatorsAll = Set.of("+", "-", "/", "*", "^");
        Deque<Integer> evaluation = new ArrayDeque<>();

        for (String symbol : expressionList) {
            if (operatorsAll.contains(symbol)) {
                int operand2 = evaluation.pollLast();
                int operand1 = evaluation.pollLast();
                evaluation.addLast(calculate(operand1, operand2, symbol));
            } else {
                evaluation.addLast(Integer.valueOf(symbol));
            }
        }

        return evaluation.pollLast();
    }

    private static int calculate(int operand1, int operand2, String operator) {

        int result = 0;

        switch (operator) {
            case "+" -> result = operand1 + operand2;
            case "-" -> result = operand1 - operand2;
            case "*" -> result = operand1 * operand2;
            case "/" -> result = operand1 / operand2;
            case "^" -> result = toPutInThePower(operand1, operand2);
        }

        return result;
    }

    private static int toPutInThePower(int operand1, int operand2) {

        int result = 1;

        for (int i = 0; i < operand2; i++) {
            result *= operand1;
        }

        return result;
    }

    private static boolean checkParenthheses(String inputString) {

        String regex = "[^()]";
        Stack<Character> openParenthesesStack = new Stack<>();

        HashMap<Character, Character> parenthesesPairs = new HashMap<>();
        parenthesesPairs = fillHashSet();

        String onlyParentheses = inputString.replaceAll(regex, "");

        boolean answer = true;
        for (int i = 0; i < onlyParentheses.length() && answer; i++) {
            char symbol = onlyParentheses.charAt(i);

            if (!parenthesesPairs.containsKey(symbol)) {
                openParenthesesStack.push(symbol);
            } else {
                answer = ifIsPair(openParenthesesStack, parenthesesPairs, symbol);
            }
        }

        return answer && openParenthesesStack.isEmpty();
    }

    private static HashMap<Character, Character> fillHashSet() {

        HashMap<Character, Character> resultHashMap = new HashMap<>();

        resultHashMap.put(')', '(');

        return resultHashMap;
    }

    private static boolean ifIsPair(Stack<Character> stack, HashMap<Character, Character> hashMap, Character symbol) {
        return (!stack.isEmpty()) && stack.pop() == hashMap.get(symbol) ? true : false;
    }
}
