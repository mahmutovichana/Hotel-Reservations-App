package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.User;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoSQLImpl implements UserDao {

    private Connection connection;

    public UserDaoSQLImpl() {
        try {
            FileReader reader = new FileReader("src/main/resources/db.properties");
            Properties p = new Properties();
            p.load(reader);
            String s1 = p.getProperty("username"), s2 = p.getProperty("password"), s3 = p.getProperty("server");
            this.connection = DriverManager.getConnection(s3, s1, s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        String query = "SELECT * FROM USERS WHERE username = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){ // result set is iterator.
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getInt("role"));
                user.setPassword(rs.getString("password"));
                rs.close();
                return user;
            }else{
                return null; // if there is no elements in the result set return null
            }
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return null;
    }

    @Override
    public User add(User person) {
        String insert = "INSERT INTO USERS(first_name,last_name,email,role,username,password) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, person.getFirstName());
            stmt.setString(2,person.getLastName());
            stmt.setString(3,person.getEmail());
            stmt.setInt(4,person.getRole());
            stmt.setString(5,person.getUsername());
            stmt.setString(6,person.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            person.setUsername(rs.getString(1)); //set username to return it back
            return person;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User person) {
        String insert = "UPDATE USERS SET first_name = ?, last_name = ?, email = ?, password = ? WHERE username = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, person.getFirstName());
            stmt.setObject(2, person.getLastName());
            stmt.setObject(3, person.getEmail());
            stmt.setObject(4, person.getPassword());
            stmt.setObject(5, person.getUsername());
            stmt.executeUpdate();
            return person;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(User user) {
        String insert = "DELETE FROM USERS WHERE username = ?";
        try{
            PreparedStatement stmt = this.connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, user.getUsername());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM USERS";
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                User person = new User();
                person.setUsername(rs.getString("username"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                users.add(person);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace(); // poor error handling
        }
        return users;
    }

}