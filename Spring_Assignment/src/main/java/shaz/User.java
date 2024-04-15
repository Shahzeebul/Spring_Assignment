package shaz;

public class User {
    private String username;
    private String password;
    private String name;
    private String course;

    public User(String user, String pass, String newName, String newCourse) { 
        username = user;
        password = pass;
        name = newName;
        course = newCourse; 
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void display() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course); 
    }
}
