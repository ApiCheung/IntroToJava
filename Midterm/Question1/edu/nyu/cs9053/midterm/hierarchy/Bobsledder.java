package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:54 下午
 * @projectName Midterm
 */
public class Bobsledder extends Sledder{
    private int crewNumber;

    public Bobsledder(String name, int age, String sledColor, int crewNumber){
        super(name, age, sledColor);
        setCrewNumber(crewNumber);
    }

    public int getCrewNumber() {

        return crewNumber;
    }

    public void setCrewNumber(int crewNumber) {

        this.crewNumber = crewNumber;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof  Bobsledder){
            if(this.crewNumber == ((Bobsledder) o).crewNumber && this.getSledColor() == ((Bobsledder) o).getSledColor()
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
        return super.toString() + " Bobsledder{" +
                "crewNumber=" + crewNumber +
                '}';
    }
}
