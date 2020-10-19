package edu.nyu.cs9053.midterm.hierarchy;

/**
 * @author Esmee Zhang
 * @date 10/16/20 3:54 下午
 * @projectName Midterm
 */
public class CrossCountrySkier extends Skier{
    private int countryNum;

    public CrossCountrySkier(String name, int age, int skiLength, int countryNum){
        super(name, age, skiLength);
        setSkiLength(countryNum);

    }

    public int getCountryNum() {

        return countryNum;
    }

    public void setCountryNum(int countryNum) {

        this.countryNum = countryNum;
    }

    @Override
    public boolean equals(WinterSportPlayer o) {
        if(o instanceof CrossCountrySkier){
            if(this.countryNum == ((CrossCountrySkier) o).countryNum && this.getSkiLength() == ((CrossCountrySkier) o).getSkiLength()
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
        return super.toString() + " CrossCountrySkier{" +
                "countryNum=" + countryNum +
                '}';
    }
}
