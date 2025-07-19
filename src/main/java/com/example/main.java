package com.example;

public class main {
    public static void main(String[] args) throws Exception {
        // Ваш код здесь
        System.out.println("Привет, мир!");

        Lion lion = new Lion("Самец", new Feline());
        lion.getKittens();
        lion.getKittens();
        System.out.printf(String.valueOf(lion.getFood()));

    }
}
