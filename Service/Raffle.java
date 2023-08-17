package ToysRaffle.Service;

import ToysRaffle.Data.Toy;

import java.util.*;

public class Raffle {
    private static PriorityQueue<Toy> prizes = new PriorityQueue<>();//обработка
    private Writer writer = new Writer();

    /**
     * Розыгрыш
     */
    public void raffle(ArrayList<Toy> toys) {
        if (toys.size() >= 2) {
            Toy prize = getPrize(toys);
            System.out.println("Приз: " + prize.getName());
            writer.saveResult(prize.getInfo());
        } else {
            System.err.println("В призовой фонд необходимо добавить не менее двух игрушек.");
        }
    }

    /**
     * Получение приза. Через рандом с помошью PriorityQueue
     * @return Возвращает выпавшую игрушку
     */
    private Toy getPrize(ArrayList<Toy> toys) {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getFrequency(); i++) {
                    Toy temp = new Toy(toy.getId(), toy.getName(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }
}
