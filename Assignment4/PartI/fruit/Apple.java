package Assignment4.PartI.fruit;

/**
 * @author Esmee Zhang
 * @date 9/30/20 4:24 下午
 * @projectName IntroToJava-NYU
 */
public class Apple extends Fruit {
    String taste;
    String texture;

    public Apple(){
        this.taste = "Sweet";
        this.texture = "Soft";
    }

    public Apple(String taste, String texture, String color, Boolean rotten){
        super(color, rotten);
        this.taste = taste;
        this.texture = texture;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    @Override
    public String toString() {
        return super.toString() + " Tasts is: " + this.getTaste() + ". Texture is: "
                + getTexture();
    }

//    public boolean equals(Object o){
//        if(o instanceof Fruit){
//            return true;
//        }else{
//            return false;
//        }
//    }

    //compare apple variable to ses weather they are same

    public boolean equals(Object o){
        if(o instanceof Fruit || o instanceof Apple ){
            Fruit temp = (Fruit) o;
            if(this.isRotten() == temp.isRotten() && this.getColor() == temp.getColor()
                   ){
                return true;
            }
        }else{
            return false;
        }
        return false;

    }





}
