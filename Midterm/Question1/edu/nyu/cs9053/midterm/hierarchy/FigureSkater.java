package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:55 下午
 * @projectName Midterm
 */
public class FigureSkater extends IceSkater{
    private String type;

    public FigureSkater(String name, int age, int skateSize, String type){
        super(name, age, skateSize);
        setType(type);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof FigureSkater){
            if(this.type == ((FigureSkater)o).type && this.getSkateSize() == ((FigureSkater)o).getSkateSize()
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
        return super.toString() + " FigureSkater{" +
                "type='" + type + '\'' +
                '}';
    }
}
