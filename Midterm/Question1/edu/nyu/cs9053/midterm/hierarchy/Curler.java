package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:53 下午
 * @projectName Midterm
 */
public class Curler extends WinterSportPlayer {
    private String trouserPattern;

    public Curler(String name, int age, String trouserPattern){
        super(name, age);
        setTrouserPattern(trouserPattern);
    }

    public String getTrouserPattern() {
        return trouserPattern;
    }

    public void setTrouserPattern(String trouserPattern) {
        this.trouserPattern = trouserPattern;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof Curler){
            if(this.trouserPattern == ((Curler) o).trouserPattern && super.equals(o)){
                return true;
            }else{
                return false;
            }

        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " Curler{" +
                "trouserPattern='" + trouserPattern + '\'' +
                '}';
    }
}
