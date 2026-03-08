package athena.parser;
/** athena.parser.Parser interprets the user input **/

import athena.command.AthenaException;

public class Parser {

    public String[] splitInput(String inputLine) {
        return inputLine.split(" ", 2);
    }

    public String parseCommandWord(String inputLine) {
        return splitInput(inputLine)[0].toLowerCase();
    }

    /** Error Handling:
     parses index from user input **/
    public int parseIndex(String[] inputParts) throws AthenaException {
        if (inputParts.length != 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task number, you fool.");
        }
        try {
            return Integer.parseInt(inputParts[1].trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AthenaException("Task number must be an integer, you fool.");
        }
    }

    public String parseTodoDescription(String[] inputParts) throws AthenaException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task description, you fool.");
        }
        return inputParts[1].trim();
    }

    public String[] parseDeadlineParts(String[] inputParts) throws AthenaException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task description and deadline, you fool.");
        }
        String[] deadlineParts = inputParts[1].split("by ", 2);
        if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty()
                || deadlineParts[1].trim().isEmpty()) {
            throw new AthenaException("Use the format: deadline <description> by <time>, you fool.");
        }
        return new String[]{deadlineParts[0].trim(), deadlineParts[1].trim()};
    }

    public String[] parseEventParts(String[] inputParts) throws AthenaException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task description, start, and end time, you fool.");
        }
        String[] eventParts = inputParts[1].split("from ", 2);
        if (eventParts.length < 2 || eventParts[0].trim().isEmpty()
                || eventParts[1].trim().isEmpty()) {
            throw new AthenaException("Use the format: event <description> from <start> to <end>, you fool.");
        }
        String[] timeParts = eventParts[1].split("to ", 2);
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty()
                || timeParts[1].trim().isEmpty()) {
            throw new AthenaException("Use the format: event <description> from <start> to <end>, you fool.");
        }
        return new String[]{eventParts[0].trim(), timeParts[0].trim(), timeParts[1].trim()};
    }

    public String parseFindKeyword(String[] inputParts) throws AthenaException{
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a keyword to search, you fool.");
        }
        return inputParts[1].trim();
    }
}