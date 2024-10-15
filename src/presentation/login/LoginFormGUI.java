package presentation.login;

import DAO.user;
import presentation.WeatherApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormGUI extends Form {
    user manageuser;

    {
        try {
            manageuser = new user();
        } catch (SQLException ee) {
            throw new RuntimeException(ee);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginFormGUI() {
        super("Login");
        setLayout(new BorderLayout());
        addGuiComponents();
        setResizable(true);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addGuiComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        // create login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.white);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(loginLabel, constraints);

        // create username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        // create username text field
        JTextField usernameField = new JTextField(20);
        usernameField.setBackground(Color.white);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(usernameField, constraints);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.black);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.black);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(passwordField, constraints);


        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(new Color(186, 255, 41));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);


        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(registerLabel, constraints);


        add(panel, BorderLayout.CENTER);
        panel.setBackground(new Color(5, 130, 202));


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();


                String password = new String(passwordField.getPassword());

                ResultSet resultSet = null;
                try {
                    resultSet = manageuser.verifLogin(username, password);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    if (resultSet.next()) {

                        JOptionPane.showMessageDialog(LoginFormGUI.this,
                                "Login Successful!");
                        String user = resultSet.getString("username");
                        JOptionPane.showMessageDialog(null, "Welcome, " + username + "!");
                        new WeatherApp(user).setVisible(true);
                        dispose();


                    }else{

                        JOptionPane.showMessageDialog(LoginFormGUI.this,
                                "Login Failed...");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                LoginFormGUI.this.dispose();


                new RegisterFormGUI().setVisible(true);
            }
        });
    }
}
