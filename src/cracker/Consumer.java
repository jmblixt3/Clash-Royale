package cracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Item> queue;
    private Map<String, String> passwords;

    public Consumer(BlockingQueue<Item> queue, String passPath) {
        this.queue = queue;
        this.passwords = new HashMap<String, String>();

        // store password file in memory
        try (BufferedReader reader = new BufferedReader(new FileReader(passPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String password = line.substring(line.indexOf(":") + 1);
                String user = line.substring(0, line.indexOf(":") + 1);
                passwords.put(password, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    public void run() {
        File output = new File("output.txt");
        if (output.exists()) {
            output.delete();
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int matchCount = 0;
        int passwordCount = passwords.size();
        while (true) {
            try {
                // wait until queue has content from producers
                Item item = queue.take();

                if (passwords.containsKey(item.getHash())) {
                    matchCount++;
                    System.out.println("Passwords matched: " + matchCount + " / " + passwordCount);
                    // retrieves corresponding user from passwords using queue item,
                    // then prints associated plain text pass from queue item
                    writer.write(passwords.get(item.getHash()) + item.getPlain());
                    writer.newLine();
                    writer.flush();
                    passwords.remove(item.getHash());
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
