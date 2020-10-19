package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:54 下午
 * @projectName Midterm
 */
public class MogulSkier extends Skier {
    private double height;

    public MogulSkier(String name, int age, int skiLength, double height){
        super(name, age, skiLength);
        setHeight(height);
    }

    public double getHeight() {

        return height;
    }

    public void setHeight(double height) {

        this.height = height;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof MogulSkier){
            if(this.height == ((MogulSkier) o).height && this.getSkiLength() == ((MogulSkier) o).getSkiLength()
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
        return super.toString() + " MogulSkier{" +
                "height=" + height +
                '}';
    }
}
