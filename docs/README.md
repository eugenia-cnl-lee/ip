# Athena User Guide

Athena is a desktop chatbot that helps users manage tasks efficiently using simple text commands.  
It allows users to add tasks, manage deadlines and events, mark tasks as complete, search tasks, and automatically save tasks.

Athena is optimised for users who prefer fast keyboard-based interaction instead of graphical interfaces.

---

## Quick Start

1. Ensure you have **Java 11 or above** installed.
2. Download the latest `athena.jar` from the releases page.
3. Run the app with: `java -jar athena.jar`
4. Type a command and press **Enter** to interact with athena.
5. Refer to the features below for a full list of commands.

---

## Features

### 1. Adding a Todo - `todo`

Adds a simple task with no date or time attached.

**Format:** `todo DESCRIPTION`

**Example**
```
todo borrow book
```
```
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
```

---

### 2. Adding a Todo - `deadline`

Adds a task that needs to be completed by a specific date/time.

**Format:** `deadline DESCRIPTION by DATE`

**Example**
```
deadline return book by Sunday
```
```
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
```

---

### 3. Adding an Event - `event`

Adds a task that occurs during a specific time period.

**Format:** `event DESCRIPTION from START to END`

**Example**
```
event project meeting from Monday 2pm to 4pm
```
```
Got it. I've added this task:
  [E][ ] event book club meeting (from: Monday 2pm to: 4pm)
Now you have 3 tasks in the list.
```

---

### 4. List All Tasks - `list`

Displays all tasks currently in your list. If your list is empty, Athena will let you know.

**Format:** `list`

**Example**
```
list
```
```
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] book club meeting (from: Monday 2pm to: 4pm)
```

---

### 5. Mark a Task as Done - `mark`

Marks a task as completed.

**Format:** `mark INDEX`

**Example**
```
mark 1
```
```
Well done scholar. I've marked this quest as done:
  [T][X] borrow book
```

---

### 6. Unmark a Task - `unmark`

Marks a task as not done.

**Format:** `unmark INDEX`

**Example**
```
unmark 1
```
```
You incompetent fool. This quest has now been marked as undone:
  [T][ ] borrow book
```

---

### 7. Delete a Task - `delete`

Removes a task from the list permanently.

**Format:** `delete INDEX`

**Example**
```
delete 1
```
```
I have removed this task from your to-conquest list:
  [T][ ] borrow book
You disappoint me. Now you have 2 in the list.
```

---

### 8. Find Tasks by Keyword - `find`

Searches for tasks whose description contains the given keyword.

**Format:** `find KEYWORD`

**Example**
```
find book
```
```
1. [D][ ] return book (by: Sunday)
2. [E][ ] book club meeting (from: Monday 2pm to: 4pm)
```

---

### 9. Exit - `bye`

Exits the Athena chatbot. Your tasks are automatically saved and will be reloaded the next time you start the app.

**Format:** `bye`

**Example**
```
bye
```
```
You no longer require my assistance? Then I shall take my leave now.
I hope my teachings of today reside with you forever.
May your thirst for knowledge be as fierce and as eternal as Greek Fire.
```

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| todo | `todo DESCRIPTION` | `todo borrow book` |
| deadline | `deadline DESCRIPTION by DATE` | `deadline return book /by 2019-10-15 1800` |
| event | `event DESCRIPTION from START to END` | `event meeting /from Mon 2pm /to 4pm` |
| list | `list` | `list` |
| mark | `mark INDEX` | `mark 1` |
| unmark | `unmark INDEX` | `unmark 1` |
| delete | `delete INDEX` | `delete 2` |
| find | `find KEYWORD` | `find book` |
| bye | `bye` | `bye` |

---

## Data Storage
Tasks are automatically saved to `data/athena.txt` after every command. There is no need to save manually. The file is loaded automatically when Athena starts up.
