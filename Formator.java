import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Formator {
    /*
     * every train data starts with geometry block
     * every train data is seperated by feature }, block
     * 
     */
    public static void main(String[] args) {
        // Specify the input and output file paths
        File inputFile = new File("./ip_trains.json");
        File outputFile = new File("./trains.json");

        try (Scanner scanner = new Scanner(inputFile);
                FileWriter writer = new FileWriter(outputFile)) {

            boolean foundGeometry = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("geometry")) {
                    // Start of a geometry block (prepend '{')
                    foundGeometry = true;
                    writer.write("{\n" + line + "\n");
                } else if (line.contains("Feature")) {
                    // End of a feature block (append '}')
                    writer.write(line + "\n},\n");
                    foundGeometry = false;
                } else if (foundGeometry) {
                    // Inside a geometry block
                    writer.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
