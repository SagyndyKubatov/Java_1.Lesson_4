package com.company;

import java.util.Random;

public class Main {

    private static int bossHealth = 700;
    private static int bossDamage = 50;
    private static String bossDefenceType = "";
    private static int[] heroesHealth = {300, 250, 250, 250, 350};
    private static int[] heroDamage = {30, 40, 30, 25, 0};
    private static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Archer", "Medic"};
    private static int roundCounter = 1;

    public static void main(String[] args) {
        printStatistics();
        while (isGameOver() != true) {
            round();
            roundCounter++;
        }
    }

    public static void printStatistics() {
        System.out.println("_____________________________");
        System.out.println("Round: " + roundCounter);
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("_____________________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void setMedicHeal() {

        for (int i = 0; i < heroesAttackType.length; i++) {
            if (i!=4 && heroesHealth[i] <= 100 && heroesHealth[i] > 0){
                heroesHealth[i]+= 100;
                System.out.println("Medic choice: "+heroesAttackType[i]+ " + 100 heal");
                break;
            }
        }
    }

    public static void heroesHits(){
        for (int i = 0; i < heroesAttackType.length-1; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if (heroesAttackType[i] == bossDefenceType){
                    heroDamage[i] = 0;
                    System.out.println("Boss reflected " + heroesAttackType[i]);
                }else {
                    bossHealth = bossHealth - heroDamage[i];
                }
            }else {
                if (bossHealth - heroDamage[i] < 0){
                    bossHealth = 0;
                }else {
                    bossHealth = bossHealth - heroDamage[i];
                }
            }
        }
    }


    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead == true){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }


    public static void changeDefenceType(){
        int randomIndex = new Random().nextInt(heroesAttackType.length-1);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choice: "+ bossDefenceType);
    }

    public static void round(){
        if (heroesHealth[4] > 0){

            setMedicHeal();
        }
        if (bossHealth > 0){
            //Босс отражает
            changeDefenceType();
            //Босс наносит урон
            bossHits();
        }
        //Герои наносят урон
        heroesHits();
        //Распечатка статистики
        printStatistics();
    }

}
