import java.util.HashSet;

public class QuestionMarkEquation {
    //    5.	*+Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.
    //    Требуется восстановить выражение до верного равенства. Предложить хотя бы одно решение или сообщить, что его нет.

    public static char[] questionChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public static HashSet<String> combinationsSet = new HashSet<>();

    public static void main(String[] args) {
        String expression = "2?? + ?5 = ?69";

        expression = expression.replaceAll("\\s+", "");

        int numberOfQuestions = countQuestions(expression);
        combinations("", questionChar, numberOfQuestions);

        int count = 0;
        for (String possibleSet : combinationsSet) {
            String expressionFilled = fillQuestionsInExpression(possibleSet, expression);

            if (isCorrectEquations(expressionFilled)) {
                count++;
                System.out.println(expressionFilled);
            }
        }
        System.out.println("Из выражения " + expression + " получается " + count + " восстановленн(ое)ых выражени(е)й.");
    }


    public static int countQuestions(String expression) {
        return expression.replaceAll("[^'?']", "").length();
    }


    public static void combinations(String result, char[] charAllSymbols, int lengthArray) {
        if (result.length() == lengthArray) {
            combinationsSet.add(result);
            return;
        }
        for (char symbol : charAllSymbols) {
            combinations(result + symbol, charAllSymbols, lengthArray);
        }
    }


    public static String fillQuestionsInExpression(String fillers, String expression) {
        String resultExpression = new String(expression);
        for (char filler : fillers.toCharArray()) {
            resultExpression = resultExpression.replaceFirst("\\?", String.valueOf(filler));
        }
        return resultExpression;
    }


    public static boolean isCorrectEquations(String expression) {

        int answer = Integer.parseInt(expression.substring(expression.indexOf("=") + 1));
        int number1String = Integer.parseInt(expression.substring(0, expression.indexOf("+")));
        int number2String = Integer.parseInt(expression.substring(expression.indexOf("+") + 1, expression.indexOf("=")));

        return number1String + number2String == answer;
    }
}
