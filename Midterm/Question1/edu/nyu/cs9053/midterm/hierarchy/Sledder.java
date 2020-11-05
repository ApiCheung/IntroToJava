package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:53 下午
 * @projectName Midterm
 */
public class Sledder extends WinterSportPlayer {
    private String sledColor;

    public Sledder(String name, int age, String sledColor ){
        super(name, age);
        setSledColor(sledColor);

    }

    public void setSledColor(String sledColor) {
        this.sledColor = sledColor;
    }

    public String getSledColor() {
        return sledColor;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof Sledder){
            if(this.sledColor == ((Sledder) o).sledColor && super.equals(o)){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " Sledder{" +
                "sledColor='" + sledColor + '\'' +
                '}';
    }
}
