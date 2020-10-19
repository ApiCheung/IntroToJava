package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:52 下午
 * @projectName Midterm
 */
public class Luger extends Sledder {
    private double weight;

    public Luger(String name, int age, String sledColor, double weight){
        super(name, age, sledColor);
        setWeight(weight);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof Luger){
            if(this.weight == ((Luger) o).weight && this.getSledColor() == ((Luger) o).getSledColor()
            && super.equals(o)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " Luger{" +
                "weight=" + weight +
                '}';
    }
}
