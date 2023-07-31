import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SentimentAnalyser {
    private static LinkedList myDataSet;

    private static void loadDataSet() {  // Loading the data from the text file
        String fileName = "mydataset.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseDataSet(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("Welcome to my Sentiment Analyser!");
        System.out.println();

        myDataSet = new LinkedList();  // A new instance of the Linked List
        loadDataSet();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the word to be analyzed: ");
        try {
            String word = input.nextLine();  // The entered word has to be case-sensitive

            System.out.println();

            int wordOccurrence = myDataSet.countWordOccurrences(word);
            System.out.println("Occurrences of the word \"" + word + "\": " + wordOccurrence);

            int sentiment = myDataSet.calculateSentiment(word);  // Scanning the sentiment value of the word
            System.out.println();
            if (sentiment == 1) {  // Checking the sentiment value of the word
                System.out.println("\"" + word + "\" gives a positive sentiment.");
            } else if (sentiment == 0) {
                System.out.println("\"" + word + "\" gives a negative sentiment.");
            } else {
                System.out.println("\"" + word + "\" can be positive or negative.");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseDataSet(String line) {
        String[] parts = line.split(":");  // Splitting the lines
        if (parts.length == 2) {
            myDataSet = LinkedList.insert(myDataSet, parts[0].trim(), Integer.parseInt(parts[1].trim()));
        } else {
            throw new IllegalArgumentException("Invalid Data Set");
        }
    }
}
