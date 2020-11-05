package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:53 下午
 * @projectName Midterm
 */
public class Skier extends WinterSportPlayer {
    private int skiLength;

    public Skier(String name, int age, int skiLength){
        super(name, age);
        setSkiLength(skiLength);
    }

    public int getSkiLength() {
        return skiLength;
    }

    public void setSkiLength(int skiLength) {
        this.skiLength = skiLength;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof Skier){
            if(this.skiLength == ((Skier) o).skiLength && super.equals(o)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " Skier{" +
                "skiLength=" + skiLength +
                '}';
    }
}
