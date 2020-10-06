package Assignment4.PartI.fruit;

/**
 * @author Esmee Zhang
 * @date 9/30/20 4:34 下午
 * @projectName IntroToJava-NYU
 */
public class Citris extends Fruit{
    String taste;

    public Citris(){
        this.taste = "Sour";
    }

    public Citris(String color, Boolean rotten, String taste){
        super(color, rotten);
        this.taste = taste;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    @Override
    public String toString() {
        return super.toString() + " Taste is: " + getTaste();
    }

    public boolean equals(Object o){
        if(o instanceof Fruit || o instanceof Citris) {
            Citris temp = (Citris) o;
            if (this.getTaste() == temp.getTaste() && this.getColor() == temp.getColor()
                    && this.isRotten() == temp.isRotten()) {
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
}
