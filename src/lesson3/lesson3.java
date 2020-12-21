package lesson3;

import java.util.Random;
import java.util.Scanner;

public class lesson3 {
    public static void main(String[] args) {
        // 0
        String str1 = "Это предложение  один     А это    два      Тут третье   предложение   ";
        //fixString(str1);

        // 1
        //guessTheNumber();

        // 2
        //guessTheWord();
    }

    // 0
    public static void fixString(String str) {
        System.out.println(str);
        str = str.replaceAll(" +"," ");
        System.out.println(str);
        StringBuilder str1 = new StringBuilder(str);

        for (int i = 1; i < str1.length(); i++) {
            if (str1.charAt(i) >= 'А' && str1.charAt(i) <= 'Я') {
                str1.setCharAt(i-1,'.');
                str1.insert(i," ");
                i++;
            }
        }

        for (int i = str1.length() - 1; i >= 0; i--) {
            if (str1.charAt(i) >= 'а' && str1.charAt(i) <= 'я') {
                str1.insert(i + 1,".");
                break;
            }
        }

        System.out.println(str1);
    }

    // 1
    public static void guessTheNumber() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int a = random.nextInt(10);
        boolean flag = false;

        System.out.println("Угадайте число от 0 до 9");
        for (int i = 0; i < 3; i++) {
            int answer = scanner.nextInt();
            if (answer == a) {
                flag = true;
                break;
            } else if (answer < a) {
                System.out.println("Ваше число меньше загаданного.");
            } else {
                System.out.println("Ваше число больше загаданного.");
            }
        }

        if (flag) {
            System.out.println("Поздравляю! Вы угадали!");
        } else {
            System.out.println("У Вас закончились попытки.");
        }

        repeatTheGame();
    }

    public static void repeatTheGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        int answer = scanner.nextInt();
        if (answer == 1) {
            guessTheNumber();
        }
    }

    // 2
    public static void guessTheWord() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        int index = random.nextInt(words.length - 1);
        StringBuilder word = new StringBuilder(words[index]);
        StringBuilder cipher = new StringBuilder("###############");
        boolean flag = false;

        System.out.println("Отгадайте загаданное слово. Если хотите завершить игру, наберите exit");
        do {
            String answer = scanner.nextLine();
            StringBuilder answer1 = new StringBuilder(answer);

            if (answer.equalsIgnoreCase("exit")) {
                break;
            } else if (answer1.toString().equalsIgnoreCase(word.toString())) {
                flag = true;
                break;
            } else {
                int length = (answer1.length() < word.length()) ? answer1.length() : word.length();
                for (int i = 0; i < length; i++) {
                    if (answer1.charAt(i) == word.charAt(i)) {
                        cipher.setCharAt(i,answer1.charAt(i));
                    }
                }
            }
            System.out.println(cipher);
        } while (true);

        if (flag) {
            System.out.println("Поздравляю! Вы отгадали слово!");
        } else {
            System.out.println("В следующий раз повезет.");
        }

        repeatTheGameWords();
    }

    public static void repeatTheGameWords() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
        int answer = scanner.nextInt();
        if (answer == 1) {
            guessTheWord();
        }
    }
}
