package Assignment3.PartI;

public class Ellipse {
    double semiMajorAxis;
    double semiMinorAxis;
    static int id;
    int ellid;

    public Ellipse(){
        semiMajorAxis = 2.0;
        semiMinorAxis = 1.0;
        ellid = id++;

    }

    public Ellipse(double a, double b){
        if(a > b) {
            this.semiMajorAxis = a;
            this.semiMinorAxis = b;
        }else{
            this.semiMinorAxis = a;
            this.semiMajorAxis = b;
        }
        ellid = id++;

    }

    public double getSemiMajorAxis(){
        return semiMajorAxis;
    }

    public double getSemiMinorAxis(){
        return semiMinorAxis;
    }

    public double getArea(){
        return semiMajorAxis * semiMinorAxis * Math.PI;
    }

    public double getId(){
        return ellid;
    }
	
}
