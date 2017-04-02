package ohtu.data_access;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUserDAO implements UserDao {
    private List<User> users;
    private final String filename;

    public FileUserDAO(String filename) {
        this.filename = filename;
        read();
    }

    @Override
    public List<User> listAll() {
        return users;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        users.add(user);
        write();
    }

    private void read() {
        users = new ArrayList();
        try {
            Scanner reader = new Scanner(new File(filename));
            while (reader.hasNext()) {
                users.add(new User(reader.nextLine()));
            }
        } catch (IOException e) {
            System.out.println("Failed to read user data!");
        }
    }

    private void write() {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            for (User user : users) {
                writer.println(user.getUsername() + "\t" + user.getPassword());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write user data!");
        }
    }
}