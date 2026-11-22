package com.sit.rct.storage;

import java.io.*;

public class StorageManager {
    private static final String FILE_PATH = "rct_data.ser";

    public static DataStore load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new DataStore();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (DataStore) ois.readObject();
        } catch (Exception e) {
            System.err.println("Could not load existing data, starting fresh: " + e.getMessage());
            return new DataStore();
        }
    }

    public static void save(DataStore store) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(store);
            System.out.println("DataStore saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save DataStore: " + e.getMessage());
        }
    }
}