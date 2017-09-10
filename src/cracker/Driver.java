package cracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Driver {

    public static void main(String[] args) throws Exception {
        String passPath = "password.txt";
        String dictPath = "dictionary.txt";

        System.out.println("Loading dictionary");

        List<String> dictionary = new ArrayList<String>();

        // store dictionary file in memory
        try (BufferedReader reader = new BufferedReader(new FileReader(dictPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Dictionary loaded");

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // queue of items (containing a word and it's hash equivalent) added to by producers, taken by consumer
        BlockingQueue<Item> queue = new LinkedBlockingQueue<Item>();

        // checks given hashed word from producers against hashes stored in password text file
        // single consumer serialises writes, avoids concurrent mangling of output file
        Runnable consumer = new Consumer(queue, passPath);
        executor.execute(consumer);
        System.out.println("Consumer started");

        // each thread has increasing approximate computation time based on i
        for (int i = 1; i <= 20; i++) {
            // hashes words from those stored in dictionary text file, sends to consumer to check
            Runnable producer = new Producer(queue, dictionary, i);
            executor.execute(producer);
            System.out.println("Producer " + i + " started");
        }

    }

}
