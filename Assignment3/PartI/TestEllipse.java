package Assignment3.PartI;

/**
 * @author Esmee Zhang
 * @date 9/23/20 10:00 下午
 * @projectName IntroToJava-NYU
 */
public class TestEllipse {
    public static void main(String[] args) {
        Ellipse e1 = new Ellipse();
        System.out.println(e1.getId() + " "+ e1.getArea() + e1.getSemiMajorAxis() + e1.getSemiMinorAxis() + e1.getArea());

        Ellipse e2 = new Ellipse(5, 7);
        System.out.println(e2.getId() + " " +e2.getArea() + e2.getSemiMajorAxis() + e2.getSemiMinorAxis() + e2.getArea());

        Ellipse e3 = new Ellipse(7, 5);
        System.out.println(e3.getId() + " " +e3.getArea() + e3.getSemiMajorAxis() + e3.getSemiMinorAxis() + e3.getArea());


    }
}
