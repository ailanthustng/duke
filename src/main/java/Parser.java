import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Parser class. This class manages the input of the user.
 */
public class Parser {
    Scanner sc;

    /**
     * Constructor. Initialises the Scanner class.
     */
    Parser() {
        this.sc = new Scanner(System.in);
    }

    /**
     * This method reads the input of the users and subsequently does the appropriate actions via the
     * methods in the TaskList class.
     */
    public void scan() {
        while (true) {
            String input = sc.nextLine();
            ArrayList<Task> list = TaskList.getTaskList();
            try {
                if (input.equals("bye")) {
                    UI.printBye();
                    break;
                } else if (input.contains("find")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Please input a task to find");
                    } else {
                        String keyword = input.substring(5);
                        TaskList.findTasks(keyword);
                    }
                } else if (input.equals("list")) {
                    UI.printList();
                } else if (input.contains("done")) {
                    String[] inputArr = input.split(" ");
                    int num = Integer.parseInt(inputArr[1]);
                    if (num > list.size()) {
                        throw new DukeException("Sorry! That is an invalid task number.");
                    } else {
                        TaskList.doneAction(num);
                    }
                } else if (input.contains("todo")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of todo cannot be empty.");
                    } else {
                        String name = input.substring(5);
                        Task todo = new ToDos(name);
                        TaskList.addToList(todo);
                    }
                } else if (input.contains("deadline")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of deadline cannot be empty.");
                    } else {
                        int index = input.indexOf(" ");
                        String action = input.substring(index + 1);
                        String[] actionArr = action.split(" /by ");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a deadline.");
                        } else {
                            String by = actionArr[1];
                            String name = actionArr[0];
                            Task deadline = new Deadline(name, by);
                            TaskList.addToList(deadline);
                        }
                    }
                } else if (input.contains("event")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Description of event cannot be empty.");
                    } else {
                        int index = input.indexOf(" ");
                        String action = input.substring(index + 1);
                        String[] actionArr = action.split(" /by ");
                        if (index == -1) {
                            throw new DukeException("Sorry! Please enter a date and time.");
                        } else {
                            String by = actionArr[1];
                            String name = actionArr[0];
                            Task event = new Event(name, by);
                            TaskList.addToList(event);
                        }
                    }
                } else if (input.contains("delete")) {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("Sorry! Please specify a task number.");
                    } else {
                        String[] inputArr = input.split(" ");
                        int num = Integer.parseInt(inputArr[1]);
                        if (num > list.size()) {
                            throw new DukeException("Sorry! That is an invalid task number.");
                        } else {
                            TaskList.removeAction(num - 1);
                        }
                    }
                } else {
                    throw new DukeException("Sorry! I dont know what that means.");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void scan(String input) {
        ArrayList<Task> list = TaskList.getTaskList();
        try {
            if (input.equals("bye")) {
                UI.printBye();
                Storage.writeList();
                TimeUnit.SECONDS.sleep(5);
                System.exit(0);
            } else if (input.contains("find")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("Sorry! Please input a task to find");
                } else {
                    String keyword = input.substring(5);
                    TaskList.findTasks(keyword);
                }
            } else if (input.equals("list")) {
                UI.printList();
            } else if (input.contains("done")) {
                String[] inputArr = input.split(" ");
                int num = Integer.parseInt(inputArr[1]);
                if (num > list.size()) {
                    throw new DukeException("Sorry! That is an invalid task number.");
                } else {
                    TaskList.doneAction(num);
                }
            } else if (input.contains("todo")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("Sorry! Description of todo cannot be empty.");
                } else {
                    String name = input.substring(5);
                    Task todo = new ToDos(name);
                    TaskList.addToList(todo);
                }
            } else if (input.contains("deadline")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("Sorry! Description of deadline cannot be empty.");
                } else {
                    int index = input.indexOf(" ");
                    String action = input.substring(index + 1);
                    String[] actionArr = action.split(" /by ");
                    if (index == -1) {
                        throw new DukeException("Sorry! Please enter a deadline.");
                    } else {
                        String by = actionArr[1];
                        String name = actionArr[0];
                        Task deadline = new Deadline(name, by);
                        TaskList.addToList(deadline);
                    }
                }
            } else if (input.contains("event")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("Sorry! Description of event cannot be empty.");
                } else {
                    int index = input.indexOf(" ");
                    String action = input.substring(index + 1);
                    String[] actionArr = action.split(" /by ");
                    if (index == -1) {
                        throw new DukeException("Sorry! Please enter a date and time.");
                    } else {
                        String by = actionArr[1];
                        String name = actionArr[0];
                        Task event = new Event(name, by);
                        TaskList.addToList(event);
                    }
                }
            } else if (input.contains("delete")) {
                if (input.split(" ").length == 1) {
                    throw new DukeException("Sorry! Please specify a task number.");
                } else {
                    String[] inputArr = input.split(" ");
                    int num = Integer.parseInt(inputArr[1]);
                    if (num > list.size()) {
                        throw new DukeException("Sorry! That is an invalid task number.");
                    } else {
                        TaskList.removeAction(num - 1);
                    }
                }
            } else {
                throw new DukeException("Sorry! I dont know what that means.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
