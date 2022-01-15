package com.mustache.translate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Translate {

    private final ArrayList<String> firstLanguage = new ArrayList<>();
    private final ArrayList<String> secondLanguage = new ArrayList<>();

    public Translate(String first, String second) throws IOException {
        Scanner readerFirstLanguage = new Scanner(new FileReader("src/com/mustache/translate/files/" + first));
        while (readerFirstLanguage.hasNextLine()) {
            firstLanguage.add(readerFirstLanguage.nextLine());
        }
        Scanner readerSecondLanguage = new Scanner(new FileReader("src/com/mustache/translate/files/" + second));
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
