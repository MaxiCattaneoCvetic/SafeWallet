package com.safewallet.userDataService.aliasCbu_generator;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Component
public class Alias {

    public static List<String> generateArrayString() {
        try {

            URL url = new URL("https://raw.githubusercontent.com/MaxiCattaneoCvetic/words/main/words.txt");
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String linea;
            List<String> aliasWordList = new ArrayList<>();



            while ((linea = reader.readLine()) != null) {
                linea.split("\\.");
                aliasWordList.add(linea);
                String[] split = aliasWordList.get(0).split("\\.");

                for (String s : split) {
                    aliasWordList.add(s.toLowerCase());
                }
                aliasWordList.remove(0);
            }
            reader.close();
            return aliasWordList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String generateAlias() {
        List<String> aliasWordList = generateArrayString();
        String alias =aliasWordList.get((int) (Math.random() * aliasWordList.size())) + ".";
        for (int i = 0; i < 2; i++){
            alias += aliasWordList.get((int) (Math.random() * aliasWordList.size())) + ".";
        }
        int i = alias.lastIndexOf(".");
        alias = alias.substring(0, i);

        return alias.toLowerCase();
    }


}

