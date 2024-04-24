package com.intelligence;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MessageReconstructor {

    public static String getMessage(List<List<String>> messages) {
        int maxLength = messages.stream().mapToInt(List::size).max().orElse(0);
        String[] finalMessage = new String[maxLength];

        for (List<String> message : messages) {
            for (int i = 0; i < message.size(); i++) {
                if (finalMessage[i] == null || finalMessage[i].isEmpty()) {
                    if (message.get(i) != null && !message.get(i).isEmpty()) {
                        finalMessage[i] = message.get(i);
                    }
                }
            }
        }

        return Arrays.stream(finalMessage).collect(Collectors.joining(" ")).trim();
    }
}