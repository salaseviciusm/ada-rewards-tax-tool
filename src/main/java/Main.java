import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide path to CSV file");
            return;
        }
        try {
            List<String> csvLines = Files.readAllLines(Path.of(args[0]));

            PoolToolCSVParser.parseCSV(csvLines.subList(1, csvLines.size())).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
