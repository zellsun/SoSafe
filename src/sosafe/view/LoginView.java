/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.view;

import javax.swing.JOptionPane;
import sosafe.control.UserAdmin;
import sosafe.control.UserAdminAdapter;

/**
 *
 * @author Z
 */
public class LoginView extends javax.swing.JFrame {

    /**
     * Creates new form LoginView
     */
    public LoginView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcomeLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        signupButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SoSafe Login");
        setName("loginFrame"); // NOI18N
        setResizable(false);

        welcomeLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(51, 102, 255));
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Welcome to SoSafe Services");

        usernameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usernameLabel.setText("Username");

        usernameTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        usernameTextField.setToolTipText("");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passwordLabel.setText("Password");

        passwordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        signupButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        signupButton.setText("Sign up");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginButton.setText("Log in");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTextField)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(signupButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signupButton)
                    .addComponent(loginButton))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        UserAdmin userAdmin = new UserAdminAdapter();
        if (!userAdmin.login(usernameTextField.getText(), passwordField.getPassword())) {
            JOptionPane.showMessageDialog(null, "Failed to log in.\n"
                    + "Please check your username or password.\n"
                    + "If you haven't signed up, please sign up your account first.\n"
                    + "Thank you!");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        // TODO add your handling code here:
        UserAdmin userAdmin = new UserAdminAdapter();
        if (!userAdmin.signup()) {
            JOptionPane.showMessageDialog(null, "Can't sign up.\n"
                    + "Maybe a user is already in the system and it only supports one user in this version.\n"
                    + "Please log in with its username and password.\n"
                    + "If you don't need that user's data, you can delete the database directory named 'sosafe_db'.\n"
                    + "Then you can sign up a new user.");
        }
    }//GEN-LAST:event_signupButtonActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
        loginButtonActionPerformed(evt);
    }//GEN-LAST:event_passwordFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton signupButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}