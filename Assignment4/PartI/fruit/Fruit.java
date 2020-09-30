package Assignment4.PartI.fruit;

/**
 * @author Esmee Zhang
 * @date 9/30/20 4:08 下午
 * @projectName IntroToJava-NYU
 */
public class Fruit {
    String color;
    Boolean rotten;
    static int id;
    int FruitId;


    public Fruit(){
        this.color = "white";
        this.rotten = false;
        this.FruitId = id++;
    }

    public Fruit(String color, Boolean rotten){
        this.color = color;
        this.rotten = rotten;
        this.FruitId = id++;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRotten(){
        return this.rotten;
    }

    public void setRotten(boolean rotten){
        this.rotten = rotten;
    }

    public int getId(){
        return this.FruitId;
    }

    public String toString(){
        return "Class name is: " + this.getClass().getSimpleName() + ". Color is: " + this.getColor()
        + " Class id is: " + this.getId() + ". Rotten: " + this.isRotten();
    }
// compare is in the same class

    public boolean equals(Object o){
        if(o instanceof Object){
            return true;
        }else{
            return false;
        }
    }


}
