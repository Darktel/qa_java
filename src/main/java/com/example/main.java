package com.example;

public class main {
    public static void main(String[] args) throws Exception {
        // Ваш код здесь
        System.out.println("Привет, мир!");
        IFeline feline = new Feline();

        Lion lion = new Lion("Самец", feline);
        lion.getKittens();
        lion.getKittens();
        lion.getClass();
        lion.getFood();

    }
}
