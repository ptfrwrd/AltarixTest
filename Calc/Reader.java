package Calc;

import java.text.ParseException;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;


// так этот класс должен читать выражение с клавиатуры и преобразовывать его в обратную польскую нотацию
//(что и делает)
//предусмотрены модуль, тригонометрия, корень квадратный, степень, логарифм, экспонента

public class Reader {
    // функции
    private final String[] FUNCTIONS = { "abs", "acos", "arg", "asin", "atan",  "cos", "cosh", "exp", "log",  "pow",  "sin", "sinh", "sqrt", "tan", "tanh" };
    // операции
    private final String OPERATORS = "+-*/";
    private final String SEPARATOR = ",";

    // токен для переменной
    private final String VARIABLE = "var";
    // временный стек
    private Stack<String> stackOperations = new Stack<String>();
    // стек для хранения ОПН
    private Stack<String> stackRPN = new Stack<String>();
    // стек для хранения выражения калькулятора
    private Stack<String> stackAnswer = new Stack<String>();


    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception e) {
            if ( token.equals(VARIABLE)) {
                return true;
            }
            return false;
        }
        return true;
    }

    private boolean isFunction(String token) {
        for (String item : FUNCTIONS) {
            if (item.equals(token)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSeparator(String token) {
        return token.equals(SEPARATOR);
    }

    private boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    private boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private byte getPrecedence(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        return 2;
    }

    public void parse(String expression) throws ParseException {

        stackOperations.clear();
        stackRPN.clear();


        expression = expression.replace(" ", "").replace("(-", "(0-")
                .replace(",-", ",0-");
        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }

        StringTokenizer stringTokenizer = new StringTokenizer(expression,
                OPERATORS + SEPARATOR + "()", true);


        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (isSeparator(token)) {
                while (!stackOperations.empty()
                        && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
            } else if (isOpenBracket(token)) {
                stackOperations.push(token);
            } else if (isCloseBracket(token)) {
                while (!stackOperations.empty()
                        && !isOpenBracket(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.pop();
                if (!stackOperations.empty()
                        && isFunction(stackOperations.lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
            } else if (isNumber(token)) {
                    stackRPN.push(token);

            } else if (isOperator(token)) {
                while (!stackOperations.empty()
                        && isOperator(stackOperations.lastElement())
                        && getPrecedence(token) <= getPrecedence(stackOperations
                        .lastElement())) {
                    stackRPN.push(stackOperations.pop());
                }
                stackOperations.push(token);
            } else if (isFunction(token)) {
                stackOperations.push(token);
            }
        }
        while (!stackOperations.empty()) {
            stackRPN.push(stackOperations.pop());
        }

        Collections.reverse(stackRPN);
        System.out.println(stackRPN);

        // вытаскиваем элементы и производим операции
    }




}
