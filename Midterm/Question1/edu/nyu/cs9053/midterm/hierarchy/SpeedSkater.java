package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:53 下午
 * @projectName Midterm
 */
public class SpeedSkater extends IceSkater{
    private double speed;

    public SpeedSkater(String name, int age, int skateSize, double speed) {
        super(name, age, skateSize);
        setSpeed(speed);

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof SpeedSkater){
            if(this.speed == ((SpeedSkater) o).speed && this.getSkateSize() == ((SpeedSkater) o).getSkateSize()
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
        return super.toString() + " SpeedSkater{" +
                "speed=" + speed +
                '}';
    }
}
