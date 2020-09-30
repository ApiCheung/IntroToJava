package Assignment3;

/**
 * @author Esmee Zhang
 * @date 9/29/20 6:51 下午
 * @projectName IntroToJava-NYU
 */


public class JBTThisAsParameter {

    public static void main(String[] args) {
        JBT1 obj = new JBT1();
        obj.i = 10;
        obj.method();
    }

}

class JBT1 extends JBTThisAsParameter {
    int i;

    void method() {
        method1(this);
    }

    void method1(JBT1 t) {
        System.out.println(t.i);
    }
}

