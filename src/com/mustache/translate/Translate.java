package com.mustache.translate;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Translate {

    private final ArrayList<String> firstLanguage = new ArrayList<>();
    private final ArrayList<String> secondLanguage = new ArrayList<>();

    public Translate(String first, String second) {
        Scanner readerFirstLanguage = null;
        try {
            readerFirstLanguage = new Scanner(new FileReader("src/com/mustache/translate/files/" + first.toLowerCase()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (readerFirstLanguage.hasNextLine()) {
            firstLanguage.add(readerFirstLanguage.nextLine());
        }
        Scanner readerSecondLanguage = null;
        try {
            readerSecondLanguage = new Scanner(new FileReader("src/com/mustache/translate/files/" + second.toLowerCase()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (readerSecondLanguage.hasNextLine()) {
            secondLanguage.add(readerSecondLanguage.nextLine());
        }
    }

    public String translated(String str) {
        int counter = 0;
        for (String in : firstLanguage) {
            if(in.equalsIgnoreCase(str)) break;
            counter++;
        }
        if(secondLanguage.get(counter) != null) return secondLanguage.get(counter);
        return str;
    }

}
