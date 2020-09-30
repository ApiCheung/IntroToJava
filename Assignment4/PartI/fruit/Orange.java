package Assignment4.PartI.fruit;

/**
 * @author Esmee Zhang
 * @date 9/30/20 4:39 下午
 * @projectName IntroToJava-NYU
 */
public class Orange extends Citris{
    String type;
    public Orange(){
        this.type = "Round";
    }

    public Orange(String taste, Boolean rotten,  String type){
        super("orange", rotten, taste);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + " Type is: " + this.getType();
    }

    public boolean equals(Object o){
        if(o instanceof Citris || o instanceof Fruit ){
            return true;
        }else{
            return false;
        }
    }
}
