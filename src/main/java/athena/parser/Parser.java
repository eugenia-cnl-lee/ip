package athena.parser;

import athena.command.AthenaException;

/**
 * Parser is responsible for interpreting user input
 * and extracting the command and its arguments.
 */
public class Parser {

    /**
     * Parses the task index from the user's input.
     *
     * @param 'inputParts' array containing the split user input
     * @return the zero-based index of the task
     * @throws AthenaException if the task number is missing or not a valid integer
     */
    public String[] splitInput(String inputLine) {
        return inputLine.split(" ", 2);
    }

    /**
     * Extracts the command word from the user's input.
     *
     * @param inputLine full line entered by the user
     * @return the command word in lowercase
     */
    public String parseCommandWord(String inputLine) {
        return splitInput(inputLine)[0].toLowerCase();
    }

    /**
     * Parses the task index from the user's input.
     *
     * @param inputParts array containing the split user input
     * @return the zero-based index of the task
     * @throws AthenaException if the task number is missing or not a valid integer
     */
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

    /**
     * Extracts the description for a todo task.
     *
     * @param inputParts array containing the split user input
     * @return the task description
     * @throws AthenaException if the description is missing
     */
    public String parseTodoDescription(String[] inputParts) throws AthenaException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a task description, you fool.");
        }
        return inputParts[1].trim();
    }

    /**
     * Parses the description and deadline time from a deadline command.
     *
     * @param inputParts array containing the split user input
     * @return an array containing the task description and deadline time
     * @throws AthenaException if the command format is invalid
     */
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

    /**
     * Parses the description and deadline time from a deadline command.
     *
     * @param inputParts array containing the split user input
     * @return an array containing the task description and deadline time
     * @throws AthenaException if the command format is invalid
     */
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

    /**
     * Extracts the keyword used in the find command.
     *
     * @param inputParts split user input
     * @return keyword to search
     * @throws AthenaException if keyword is missing
     */
    public String parseFindKeyword(String[] inputParts) throws AthenaException{
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new AthenaException("Provide a keyword to search, you fool.");
        }
        return inputParts[1].trim();
    }
}