package com.nihongo.backend.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class HiraganaQuiz implements QuizModule {

    private Map<String, String> characterMap;
    private Scanner scanner;


    public HiraganaQuiz() {
        scanner = new Scanner(System.in);
        characterMap = new HashMap<>();

        characterMap.put("a", "あ");
        characterMap.put("i", "い");
        characterMap.put("u", "う");
        characterMap.put("e", "え");
        characterMap.put("o", "お");
        characterMap.put("ka", "か");
        characterMap.put("ki", "き");
    }

    @Override
    public void start() {
        System.out.println("Starting Hiragana Quiz! (Type 'exit' to quit)");
        int score = 0;

        Object[] keys = characterMap.keySet().toArray();
        Random random = new Random();

        boolean isFinished = true;

        while (!isFinished) {
            String randomRomaji = (String) keys[random.nextInt(keys.length)];
            String correctHiragana = characterMap.get(randomRomaji);

            System.out.print("What is '" + correctHiragana + "' in Romaji? ");
            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("exit")) {
                isFinished = true;
                break;
            }

            if (answer.equalsIgnoreCase(randomRomaji)) {
                System.out.println("Correct! (正解)");
                score++;
            } else {
                System.out.println("Wrong! It was: " + randomRomaji);
            }
        }

        System.out.println("Quiz finished. Your Score: " + score);
    }

    @Override
    public String getModuleName() {
        return "Hiragana Practice";
    }
}
