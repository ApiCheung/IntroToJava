package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:52 下午
 * @projectName Midterm
 */
public class IceSkater extends WinterSportPlayer {
    private int skateSize;

    public IceSkater(String name, int age, int skateSize){
        super(name, age);
        setSkateSize(skateSize);
    }

    public int getSkateSize() {
        return skateSize;
    }

    public void setSkateSize(int skateSize) {
        this.skateSize = skateSize;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof IceSkater){
            if(this.skateSize == ((IceSkater) o).skateSize && super.equals(o)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " IceSkater{" +
                "skateSize=" + skateSize +
                '}';
    }
}
