package ToysRaffle.Data;

import ToysRaffle.Service.Raffle;

import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    private Raffle raffle = new Raffle();
    private Scanner scanner = new Scanner(System.in);
    private static ArrayList<Toy> toys = new ArrayList<>();
    private static int idCounter = 0;

    /**
     * Главное меню
     */
    public void menu() {
        while (true) {
            System.out.print("""
                    Меню:
                    1 - Добавить игрушку
                    2 - Изменить частоту выпадения
                    3 - Провести розыгрыш и сохранить результат
                    0 - Выход
                    >\s""");
            String selection = scanner.nextLine();
            switch (selection) {
                case "1" -> addToy();
                case "2" -> replaceFrequency();
                case "3" -> raffle.raffle(toys);
                case "0" -> {
                    System.out.println("Пока");
                    System.exit(0);
                }
                default -> System.err.println("Неправильный ввод. Попробуйте еще раз");
            }

        }
    }

    /**
     * Добавление игрушек с проверками ввода
     */
    private void addToy() {
        String title;
        int frequency;
        while (true){
            System.out.print("Введите название игрушки: ");
            title = scanner.nextLine();
            if (title.isEmpty()) {
                System.err.println("Неправильный ввод. Попробуйте еще раз");
                break;
            }
            System.out.print("Введите частоту выпадения: ");
            String value = scanner.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.err.println("Неправильный ввод. Попробуйте еще раз");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Игрушка " + title + " с частотой: " + value + " добавлена!");
                    } else {
                        System.err.println("Эта игрушка уже существует. Повторите");
                    }
                }
            } else {
                System.err.println("Неправильный ввод. Попробуйте еще раз");
            }

            break;
        }

     }

    /**
     * Смена частоту выпадения
     */
    private void replaceFrequency(){
        System.out.print("Введите ID игрушки которой нужно поменять частоту: ");
        String value = scanner.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId > 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + toys.get(selectedId - 1).getName() +
                        " имеет частоту победы " + toys.get(selectedId - 1).getFrequency());
                System.out.print("Введите новую частоту выбывания: ");
                value = scanner.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId - 1).setFrequency(newFrequency);
                    System.out.println("Частота была изменена.");
                } else {
                    System.err.println("Неправильный ввод. Попробуйте еще раз.");
                }
            } else {
                System.err.println("С таким ID нет игрушки");
            }
        } else {
            System.err.println("Неправильный ввод. Попробуйте еще раз.");
        }

    }

    /**
     * Проверка на число
     * @param value Строка
     * @return тру если число
     * @throws NumberFormatException Исключение если не число
     */
    private boolean isDigit(String value)throws NumberFormatException {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}


