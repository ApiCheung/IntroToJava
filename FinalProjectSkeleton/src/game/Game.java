package FinalProjectSkeleton.src.game;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Esmee Zhang
 * @date 12/12/20 10:14 下午
 * @projectName IntroToJava-NYU
 */
public class Game implements Serializable {
    protected String playerName;
    protected String savedDate;
    protected int aces;
    protected int twos;
    protected int threes;
    protected int fours;
    protected int fives;
    protected int sixes;
    protected int scoreTotal;
    protected int Bonus;
    protected int threeOfKind;
    protected int fourOfKind;
    protected int fullHouse;
    protected int smallHouse;
    protected int largeStraight;
    protected int yahtzee;
    protected int chance;
    protected int yahtzeeBonus;
    protected int totalLower;
    protected int grandTotal;
    protected int roll;
    protected int round;

    public Game(String playerName, String savedDate, int aces, int twos, int threes, int fours, int fives, int sixes, int scoreTotal, int bonus, int threeOfKind, int fourOfKind, int fullHouse, int smallHouse, int largeStraight, int yahtzee, int chance, int yahtzeeBonus, int totalLower, int grandTotal, int roll, int round) {
        this.playerName = playerName;
        this.savedDate = savedDate;
        this.aces = aces;
        this.twos = twos;
        this.threes = threes;
        this.fours = fours;
        this.fives = fives;
        this.sixes = sixes;
        this.scoreTotal = scoreTotal;
        Bonus = bonus;
        this.threeOfKind = threeOfKind;
        this.fourOfKind = fourOfKind;
        this.fullHouse = fullHouse;
        this.smallHouse = smallHouse;
        this.largeStraight = largeStraight;
        this.yahtzee = yahtzee;
        this.chance = chance;
        this.yahtzeeBonus = yahtzeeBonus;
        this.totalLower = totalLower;
        this.grandTotal = grandTotal;
        this.roll = roll;
        this.round = round;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getTwos() {
        return twos;
    }

    public void setTwos(int twos) {
        this.twos = twos;
    }

    public int getThrees() {
        return threes;
    }

    public void setThrees(int threes) {
        this.threes = threes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getFives() {
        return fives;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public int getBonus() {
        return Bonus;
    }

    public void setBonus(int bonus) {
        Bonus = bonus;
    }

    public int getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(int threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    public int getFourOfKind() {
        return fourOfKind;
    }

    public void setFourOfKind(int fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    public int getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(int fullHouse) {
        this.fullHouse = fullHouse;
    }

    public int getSmallHouse() {
        return smallHouse;
    }

    public void setSmallHouse(int smallHouse) {
        this.smallHouse = smallHouse;
    }

    public int getLargeStraight() {
        return largeStraight;
    }

    public void setLargeStraight(int largeStraight) {
        this.largeStraight = largeStraight;
    }

    public int getYahtzee() {
        return yahtzee;
    }

    public void setYahtzee(int yahtzee) {
        this.yahtzee = yahtzee;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getYahtzeeBonus() {
        return yahtzeeBonus;
    }

    public void setYahtzeeBonus(int yahtzeeBonus) {
        this.yahtzeeBonus = yahtzeeBonus;
    }

    public int getTotalLower() {
        return totalLower;
    }

    public void setTotalLower(int totalLower) {
        this.totalLower = totalLower;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
        this.grandTotal = grandTotal;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
