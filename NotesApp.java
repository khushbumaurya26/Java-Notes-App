import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {
  private static final String FILE_NAME = "notes.txt";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\n--- Notes Manager ---");
      System.out.println("1. Write a new note");
      System.out.println("2. View all notes");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          writeNote(scanner);
          break;
        case 2:
          readNotes();
          break;
        case 3:
          System.out.println("Exiting Notes App. Bye!");
          break;
        default:
          System.out.println("Invalid choice. Try again.");
      }
    } while (choice != 3);

    scanner.close();
  }

  private static void writeNote(Scanner scanner) {
    System.out.print("Enter your note: ");
    String note = scanner.nextLine();

    try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
      writer.write(note + System.lineSeparator());
      System.out.println("Note saved successfully.");
    } catch (IOException e) {
      System.out.println("An error occurred while writing the note.");
      e.printStackTrace();
    }
  }

  private static void readNotes() {
    System.out.println("\n--- All Notes ---");
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      boolean isEmpty = true;
      while ((line = reader.readLine()) != null) {
        System.out.println("- " + line);
        isEmpty = false;
      }
      if (isEmpty) {
        System.out.println("(No notes found.)");
      }
    } catch (FileNotFoundException e) {
      System.out.println("No notes found. Start writing!");
    } catch (IOException e) {
      System.out.println("An error occurred while reading notes.");
      e.printStackTrace();
    }
  }
}
