package Assignment4.PartI.fruit;

/**
 * @author Esmee Zhang
 * @date 9/30/20 4:44 下午
 * @projectName IntroToJava-NYU
 */
public class Lemon extends Citris{
    int sourness;

    public Lemon(){
        this.sourness = 2;
    }

    public Lemon(String taste, Boolean rotten, int sourness){
        super("yellow", rotten, taste);
        this.sourness = sourness;
    }

    public int getSourness() {
        return sourness;
    }

    public void setSourness(int sourness) {
        this.sourness = sourness;
    }

    @Override
    public String toString() {
        return super.toString() + "Sourness is: " + this.getSourness();
    }

    public boolean equals(Object o){
        if(o instanceof Citris || o instanceof Fruit){
            return true;
        }else{
            return false;
        }
    }
}
