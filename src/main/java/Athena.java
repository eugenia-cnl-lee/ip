import java.util.Scanner;

public class Athena {

    private final Ui ui;
    private final Scanner scanner;

    /** Constructs an Athena chatbot instance
    Initialises the UI and input reader **/
    public Athena() {
        this.ui = new Ui();
        this.scanner = new Scanner(System.in);
    }

    /** Runs the main chatbot loop:
    Reads user input, echoes it, and exits when user enters "bye" **/
    public void run() {
        ui.showGreeting();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                ui.showExit();
                break;
            }

            else if (input.equals("list")) {
                ui.showTaskList(tasks);
            }

            else {
                ui.showEcho(input);
            }
        }
    }

    /** Program entry point **/
    public static void main(String[] args) {
        new Athena().run();
    }
}
