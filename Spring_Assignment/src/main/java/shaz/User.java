package shaz;

public class User {
    private String username;
    private String password;
    private String name;
    private String course; // Changed from 'bio'

    public User(String user, String pass, String newName, String newCourse) { // Changed from 'new_name' and 'new_bio'
        username = user;
        password = pass;
        name = newName;
        course = newCourse; // Changed from 'new_bio'
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

    public String getCourse() { // Changed from 'getBio'
        return course;
    }

    public void setCourse(String course) { // Changed from 'setBio'
        this.course = course;
    }

    public void display() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course); // Changed from 'Bio'
    }
}
