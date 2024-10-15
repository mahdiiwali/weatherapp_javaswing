package presentation.login;

import DAO.user;
import presentation.login.constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class RegisterFormGUI extends Form {
    user manageuser;

    {
        try {
            manageuser = new user();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public RegisterFormGUI() {
        super("Register");
        addGuiComponents();
        setBackground(new Color(5, 130, 202));
    }

    private void addGuiComponents(){

        JLabel registerLabel = new JLabel("Register");


        registerLabel.setBounds(0, 25, 520, 100);


        registerLabel.setForeground(Color.white);


        registerLabel.setFont(new Font("Dialog", Font.BOLD, 40));


        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);


        add(registerLabel);


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setForeground(Color.white);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));


        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setBackground(Color.white);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(usernameLabel);
        add(usernameField);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 255, 400, 25);
        passwordLabel.setForeground(Color.white);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));


        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 285, 450, 55);
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(passwordLabel);
        add(passwordField);


        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(30, 365, 400, 25);
        rePasswordLabel.setForeground(Color.white);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));


        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(30, 395, 450, 55);
        rePasswordField.setBackground(Color.white);
        rePasswordField.setForeground(Color.BLACK);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(rePasswordLabel);
        add(rePasswordField);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));


        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.setBackground(Color.white);
        registerButton.setBounds(125, 520, 250, 50);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();


                String password = new String(passwordField.getPassword());


                String rePassword = new String(rePasswordField.getPassword());


                if(validateUserInput(username, password, rePassword)){

                    int resultSet = 0;
                    try {
                        resultSet = manageuser.verifregister(username,password);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if(resultSet > 0){

                        RegisterFormGUI.this.dispose();


                        LoginFormGUI loginFormGUI = new LoginFormGUI();
                        loginFormGUI.setVisible(true);


                        JOptionPane.showMessageDialog(loginFormGUI,
                                "Registered Account Successfully!");
                    }else{

                        JOptionPane.showMessageDialog(RegisterFormGUI.this,
                                "Error: Username already taken");
                    }
                }else{
                    // invalid user input
                    JOptionPane.showMessageDialog(RegisterFormGUI.this,
                            "Error: Username must be at least 6 characters \n" +
                                    "and/or Passwords must match");
                }
            }
        });
        add(registerButton);


        JLabel loginLabel = new JLabel("Have an account? Login Here");
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setForeground(Color.white);


        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                RegisterFormGUI.this.dispose();

                new LoginFormGUI().setVisible(true);
            }
        });

        loginLabel.setBounds(125, 600, 250, 30);
        add(loginLabel);
    }

    private boolean validateUserInput(String username, String password, String rePassword){
        // all fields must have a value
        if(username.length() == 0 || password.length() == 0 || rePassword.length() == 0) return false;





        if(!password.equals(rePassword)) return false;


        return true;
    }

}











