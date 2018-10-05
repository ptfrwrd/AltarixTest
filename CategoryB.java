import java.util.Stack;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class CategoryB {


    public void taskB1(String brackets) {

       if(sequence(brackets))
           System.out.println("SUCCESS");
       else
           System.out.println("FAIL");

    }

    public void taskB2(int n) {
        spirally(n);
    }


    private boolean sequence(String brackets) {

        Stack openBrackets = new Stack();
        String curr;
        for (int i = 0; i < brackets.length(); ++i) {

            if (brackets.charAt(i) == '(' || brackets.charAt(i) == '{' || brackets.charAt(i) == '[') {
                openBrackets.push(brackets.charAt(i)); // Если открывающая- кидаем в стек
            }

            if (brackets.charAt(i) == ')' || brackets.charAt(i) == '}' || brackets.charAt(i) == ']') {

                if (openBrackets.empty()) {
                   return false;
                }

                else {

                    curr = openBrackets.peek().toString();
                    String currBrackets = String.valueOf(brackets.charAt(i));

                    if (!((curr.equals("(") && currBrackets.equals(")")) || (curr.equals("{") && currBrackets.equals("}"))
                            || (curr.equals("[") && currBrackets.equals("]")))) {
                        break;
                    }

                    openBrackets.pop();
                }
            }

            if (brackets.charAt(i) == '\0') {
                break;
            }

        }

        if (openBrackets.empty())
            return  true;
        else
            return  false;

    }

    private void spirally(int n){

        int[] source = new int[n*n];
        int[][] matrix= new int[n][n];

        for(int i =0; i < source.length; ++i){
            source[i] = (int) Math.round( Math.random()*9);
        }

        //сортировка пузырьком массива в порядке возрастания
        for(int i = source.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){

                if( source[j] > source[j+1] ){
                    int tmp = source[j];
                    source[j] = source[j+1];
                    source[j+1] = tmp;
                }
            }
        }

        System.out.print("Массив: ");
        for (int i =0; i < n*n; ++i){
            System.out.print(source[i]+" ");
        }

        System.out.println();
        int i=0,v=0,s=0,r=1,l=1,c=0,d=0,f,j, k=0;
        f = n;

        while (k < n*n){
            //заполняем справа на лево
            for (j=s;j<f;++j) {
                matrix[i][j]=source[k];
                ++k;

            }
            v=j-1;
            --j;
            //заполняем с верху вниз
            if (k < n*n) {
                for (i=r;i<f;++i) {
                    matrix[i][j]=source[k];
                    ++k;
                }
                l=i-1;
                --i;
            }
            //заполняем слева направо
            if (k < n*n) {
                for (j=v-1;j>=c;--j) {
                    matrix[i][j]=source[k];
                    ++k;
                }
            }
            if (k < n*n) {
                //заполняем снизу вверх
                for (i=l-1;i>d;--i) {
                    matrix[i][j+1]=source[k];
                    ++k;
                }
            }
            //уменшаем параметры матрицы(чтобы числа шли внутрь, а не заполнялись поверх тех которые уже заполнены)
            --f;

            ++s;
            ++r;
            ++c;
            ++d;
            ++i;
        }

        //вывод массива
        for (i=0;i<n;++i)
        {
            for (j=0;j<n;++j)
            {
               System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }

    }



}
