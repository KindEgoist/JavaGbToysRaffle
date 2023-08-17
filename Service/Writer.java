package ToysRaffle.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    /**
     * Сохранение в файл
     * @param text
     */

    public void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
