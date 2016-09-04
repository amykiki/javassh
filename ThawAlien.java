import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Amysue on 2016/9/4.
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\Java\\ebook\\test\\Alien.file"));
        try {
            Object mystery = in.readObject();
            System.out.println(mystery.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
