package test.beyonnex;

import test.beyonnex.service.AnagramCliService;
import test.beyonnex.service.AnagramService;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {
        AnagramCliService.runReader(new Scanner(System.in), new AnagramService());
    }
}
