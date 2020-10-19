package edu.nyu.cs9053.midterm.hierarchy;

import java.util.ArrayList;

/**
 * @author Esmee Zhang
 * @date 10/16/20 5:07 下午
 * @projectName Midterm
 */
public class TestWinterSportPlayer {
    public static void main(String[] args) {
        ArrayList<WinterSportPlayer> list = new ArrayList<>();

        CrossCountrySkier ccs1 = new CrossCountrySkier("CCS1", 22, 30, 2);
        CrossCountrySkier ccs2 = new CrossCountrySkier("CCS1", 22, 30, 2);
        CrossCountrySkier ccs3 = new CrossCountrySkier("CCS2", 23, 29, 3);

        Skier SK1 = new Skier("SK1", 20, 130);

        Luger L1 = new Luger("L1", 33, "yellow", 66);
        list.add(ccs1);
        list.add(ccs2);
        list.add(ccs3);
        list.add(SK1);
        list.add(L1);

        list.add(new Bobsledder("BBS1", 35, "red", 4));
        list.add(new Bobsledder("BBS2",33,"blue", 2));
        list.add(new Curler("Cl1", 32, "long"));
        list.add(new FigureSkater("FS1", 19, 37, "sigle"));
        list.add(new IceSkater("IS1", 17, 38));
        list.add(new MogulSkier("MS1", 33, 150, 10));
        list.add(new Sledder("SL2", 33, "black"));
        list.add(new SpeedSkater("SPS1", 20, 140, 60));

        for(WinterSportPlayer o : list){
            System.out.println(o.toString());
        }
        //same type same parameters
        System.out.println(ccs1.equals(ccs2));
        //same type different parameters
        System.out.println(ccs2.equals(ccs3));
        //same type
        System.out.println(ccs1.equals(SK1));
        //differet type
        System.out.println(ccs2.equals(L1));



    }
}
