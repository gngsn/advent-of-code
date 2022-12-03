package day10;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class day10 {
    public static void main(String[] args) {
//        final Integer answer = 0;
        ArrayList<String> inputs = readFile();
        List<Integer> opens = List.of(40, 91, 123, 60);
        List<Integer> closes = List.of(41, 93, 125, 62);
        List<Integer> scores = List.of(3, 57, 1193, 25137);
        System.out.println(opens.toString());

        Integer answer = inputs.stream().map(input -> {
            Stack<Integer> st = new Stack<>();
            System.out.println("===========\n"+input);
            int first = input.chars().filter(ch -> {
                if (opens.contains(ch)) {
                    st.push(ch);
                    return true;
                } else {
                    return !st.isEmpty() && (ch-st.pop() <= 2);
                }
            }).findFirst().orElse(0);
            System.out.println("first : "+ first);
            return first;
        }).reduce(0, Integer::sum);
        System.out.println("day10! " + answer);
    }

    static ArrayList<String> readFile() {
        ArrayList<String> inputs = new ArrayList<>();

        String path = new File("").getAbsolutePath();
        path = path.concat("/2021/day10/data/test.txt");

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputs;
    }
}