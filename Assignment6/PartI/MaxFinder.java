package Assignment6.PartI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
/**
 * @author Esmee Zhang
 * @date 10/22/20 4:26 下午
 * @projectName IntroToJava-NYU
 */

public class MaxFinder<T extends Number> {
        ArrayList<T> list = new ArrayList<T>();

        public T max() {

                for(int i = 0; i < list.size(); i++){
                        if(list.get(i) instanceof ComplexNumber) {
                                ComplexNumber a = (ComplexNumber) list.get(i);
                                Double aReal = a.getReal();
                                list.set(i, (T)aReal);
                        }
                }
                T max = list.get(0);
                for(int i = 0; i < list.size(); i++){
                        if(list.get(i).toString().compareTo(max.toString()) > 0) max = list.get(i);
                }

        return max;

        }



        public void add(T t) {
                list.add(t);
        }

        public static void main (String[] args) {
                MaxFinder<Number> m = new MaxFinder<Number>();
                ComplexNumber n1 = new ComplexNumber(1,5);
                Integer a = new Integer(5);
                Double d= new Double(3.2);
                m.add(n1);
                m.add(a);;
                m.add(d);
                Number max = m.max();
                System.out.println("max for m is "+ max);
                MaxFinder<Integer> m1 = new MaxFinder<Integer>();
                Integer b = new Integer(10);
                Integer c = new Integer(20);
                m1.add(b);
                m1.add(c);
                Integer max1 = m1.max();
                System.out.println("max for m1 is "+ max1);
                
        }



}
