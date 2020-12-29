package TestDB.Music;

import TestDB.Music.model.DataSource;

/**
 * @author Esmee Zhang
 * @date 11/29/20 4:39 下午
 * @projectName IntroToJava-NYU
 */
public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        if(!dataSource.open()){
            System.out.println("cannot open data source");
            return;
        }

        dataSource.close();
    }

}
