
import Calc.Calculator;
import Calc.MementoPattern.Caretaker;
import Calc.MementoPattern.Originator;
import Calc.Reader;
import java.text.ParseException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws ParseException {

       CategoryA taskA = new CategoryA();

       System.out.println("A1:");
       taskA.taskA1(1,1,0,-1,-1,-1,0,0);

       // матрица забивается в методе
       System.out.println("A2:");
       taskA.taskA2();

       System.out.println("A3:");
       taskA.taskA3(10);



       System.out.println("A4:");
       int[] A = {1,2,3,4,5,6};
       taskA.taskA4(A, 5);



        System.out.println("A5:");
        int[][] mA = {
                {1,2,3,4,5,6,7,8,9,0},
                {2,2,2,2,1,2,1,2,3,1},
                {2,7,7,8,1,1,2, 1,1,0},
                {6,7,7,8,1,2,3,4,6,8}
        };

        int[][] B = {
                {1,2,1},
                {1,1,2}
        };

        taskA.taskA5(mA,B);


        CategoryB taskB = new CategoryB();
        System.out.println("B1:");
        String brackets = "([ ] [{ }] ) [ ({}) ]({[]}) {[ ()] }";
        taskB.taskB1(brackets);

        System.out.println("B2(спираль):");
        taskB.taskB2(3);




        System.out.println();
        System.out.println();
        System.out.println("C:");



            // проверка при переводе в обратную польскую нотацию выражения
            //но нет функции самого калькулятора

        //String ex = "(5-6)*7";
        //Reader readEx = new Reader();
        //readEx.parse(ex);

       System.out.println("Введите тип операции: 1 - сложение, 2 - вычитание(a-b), 3 - деление(a/b), 4 - умножение; и два числа(a,b) ");
       Scanner scan = new Scanner(System.in);
       int operation = scan.nextInt();

       System.out.print(" a = ");
       double a = scan.nextDouble();
       System.out.print(" b = ");
       double b = scan.nextDouble();

       Originator originator = new Originator();
       Caretaker caretaker = new Caretaker();

        Calc.Calculator calc = new Calculator();

       switch (operation){
           case 1:
              calc = new Calc.Calculator(Calculator.OPERATION.ADD);
               break;
           case 2:
              calc = new Calc.Calculator(Calc.Calculator.OPERATION.SUBTRACT);
              break;
           case  3:
               calc = new Calc.Calculator(Calculator.OPERATION.DIVIDE);
               break;
           case 4:
              calc = new Calc.Calculator(Calculator.OPERATION.MULTIPLY);
              break;
              default:
                  System.out.println("неверный ввод операции");
                  System.exit(0);
                  break;

       }

        /*
           Паттерн "хранитель". пример реализации для калькулятора:
         */

        //сохраняем первое состояние
        originator.setState(calc.calculate(a,b));
        System.out.println(originator.getState());
        caretaker.setMemento(originator.saveState());

        //устанавливаем второе состояние
        System.out.println("Для проверки паттерна хранитель введите другие числа a,b" );
        System.out.print(" a = ");
        double c = scan.nextDouble();
        System.out.print(" b = ");
        double d = scan.nextDouble();
        originator.setState(calc.calculate(c,d));
        System.out.println(originator.getState());

        // возвращаемся к предыдущему состоянию
        System.out.print("предыдущее состояние: ");
        originator.restoreState(caretaker.getMemento());
        System.out.println(originator.getState());

    }



}
