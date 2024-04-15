package shaz;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import shaz.User;

public class App {
    static String configFilePath = "shaz/Config.xml";

    static ApplicationContext context = new ClassPathXmlApplicationContext(configFilePath);

    static JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

    static Scanner scanner = new Scanner(System.in);

    static String activeUser;

    public static void main(String[] args) {
        displayMainMenu();
    }

    public static void userMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n\n\n\n=====USER MENU=====");
            System.out.println("1. Show Profile");
            System.out.println("2. Edit Course");
            System.out.println("3. Delete Account");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr);

            switch (choice) {
                case 1:
                    User userDetails = getUserDetails();
                    userDetails.display();
                    break;
                case 2:
                    System.out.println("Enter New Course: ");
                    String newCourse = scanner.nextLine();
                    updateCourse(newCourse);
                    break;
                case 3:
                    deleteUser();
                    back = true;
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Option Not Found");
                    break;
            }
        }
    }

    public static void displayMainMenu() {
        while (true) {
            System.out.println("\n\n\n\n=====MAIN MENU=====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();
            int choice = Integer.parseInt(choiceStr);

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    closeResources();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option Not Found");
                    break;
            }
        }
    }

    public static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String retrievedPassword = getPassword(username);
        if (retrievedPassword == null) {
            System.out.println("User or password incorrect!!!");
            return;
        }
        if (password.equals(retrievedPassword)) {
            activeUser = username;
            userMenu();
        } else {
            System.out.println("User or password incorrect!!!");
        }
    }

    public static void register() {
        System.out.print("Enter username: ");
        String newUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter Course: ");
        String newCourse = scanner.nextLine();

        setUser(newUser, newPassword, newName, newCourse);
    }

    public static void setUser(String user, String pass, String name, String course) {
        String query = "INSERT INTO users VALUES(?,?,?,?)";
        int rowsAffected = jdbcTemplate.update(query, user, pass, name, course);
        if (rowsAffected == 1) {
            System.out.println("User Added Successfully");
        }
    }

    public static String getPassword(String username) {
        String query = "SELECT password FROM users WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{username}, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void deleteUser() {
        String query = "DELETE FROM users WHERE username=?";
        int rowsAffected = jdbcTemplate.update(query, activeUser);
        if (rowsAffected == 1) {
            System.out.println("User Deleted Successfully");
        }
    }

    public static void updateCourse(String course) {
        String query = "UPDATE users SET course=? WHERE username=?";
        int rowsAffected = jdbcTemplate.update(query, course, activeUser);
        if (rowsAffected == 1) {
            System.out.println("Course Updated Successfully");
        }
    }

    public static User getUserDetails() {
        String query = "SELECT * FROM users WHERE username=?";
        return jdbcTemplate.queryForObject(query, new Object[]{activeUser}, (rs, rowNum) ->
            new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("course")
            )
        );
    }

    public static void closeResources() {
        scanner.close();
        ((ClassPathXmlApplicationContext) context).close();
    }
}
