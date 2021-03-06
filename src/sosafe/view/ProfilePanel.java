/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.view;

import java.util.Arrays;

import javax.swing.JOptionPane;
import sosafe.control.UserAdmin;
import sosafe.control.UserAdminAdapter;
import sosafe.control.ViewController;
import sosafe.model.CustomerInfo;

/**
 *
 * @author Z
 */
public class ProfilePanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public ProfilePanel() {
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

        customerIdLabel = new javax.swing.JLabel();
        customerNameLabel = new javax.swing.JLabel();
        propertyAddressLabel = new javax.swing.JLabel();
        phoneNumberLabel = new javax.swing.JLabel();
        emailAddressLabel = new javax.swing.JLabel();
        primaryContactNumberLabel = new javax.swing.JLabel();
        secondaryContactNumberLabel = new javax.swing.JLabel();
        licenseCodeLabel = new javax.swing.JLabel();
        masterPasswordLabel = new javax.swing.JLabel();
        responseCodeLabel = new javax.swing.JLabel();
        customerIdField = new javax.swing.JTextField();
        customerNameField = new javax.swing.JTextField();
        propertyAddressField = new javax.swing.JTextField();
        phoneNumberField = new javax.swing.JTextField();
        emailAddressField = new javax.swing.JTextField();
        primaryContactNumberField = new javax.swing.JTextField();
        secondaryContactNumberField = new javax.swing.JTextField();
        licenseCodeField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        responseCodeField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();

        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1260, 411));

        customerIdLabel.setText("Customer Id:");

        customerNameLabel.setText("Customer Name:");

        propertyAddressLabel.setText("Property Address:");

        phoneNumberLabel.setText("Phone Number:");

        emailAddressLabel.setText("Email Address:");

        primaryContactNumberLabel.setText("Primary Contact Number:");

        secondaryContactNumberLabel.setText("Secondary Contact Number:");

        licenseCodeLabel.setText("License Code:");

        masterPasswordLabel.setText("Master Password:");

        responseCodeLabel.setText("Response Code:");

        customerIdField.setText(ViewController.getInstance().getCurrentUser().getCustomerId().toString());
        customerIdField.setEditable(false);

        customerNameField.setText(ViewController.getInstance().getCurrentUser().getCustomerName());

        propertyAddressField.setText(ViewController.getInstance().getCurrentUser().getPropertyAddress());

        phoneNumberField.setText(ViewController.getInstance().getCurrentUser().getPhoneNumber());

        emailAddressField.setText(ViewController.getInstance().getCurrentUser().getEmailAddress());

        primaryContactNumberField.setText(ViewController.getInstance().getCurrentUser().getPrimaryContactNumber());

        secondaryContactNumberField.setText(ViewController.getInstance().getCurrentUser().getSecondaryContactNumber());

        licenseCodeField.setText(ViewController.getInstance().getCurrentUser().getLicenseCode());

        jPasswordField1.setText(ViewController.getInstance().getCurrentUser().getMasterPassword());

        responseCodeField.setText(ViewController.getInstance().getCurrentUser().getResponseCode());

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerIdLabel)
                            .addComponent(customerNameLabel)
                            .addComponent(propertyAddressLabel)
                            .addComponent(phoneNumberLabel)
                            .addComponent(emailAddressLabel)
                            .addComponent(primaryContactNumberLabel)
                            .addComponent(secondaryContactNumberLabel)
                            .addComponent(licenseCodeLabel)
                            .addComponent(masterPasswordLabel)
                            .addComponent(responseCodeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customerIdField, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
                            .addComponent(customerNameField)
                            .addComponent(propertyAddressField)
                            .addComponent(phoneNumberField)
                            .addComponent(emailAddressField)
                            .addComponent(primaryContactNumberField)
                            .addComponent(secondaryContactNumberField)
                            .addComponent(licenseCodeField)
                            .addComponent(responseCodeField)
                            .addComponent(jPasswordField1)
                            .addComponent(jPasswordField2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIdLabel)
                    .addComponent(customerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameLabel)
                    .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(propertyAddressLabel)
                    .addComponent(propertyAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberLabel)
                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailAddressLabel)
                    .addComponent(emailAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(primaryContactNumberLabel)
                    .addComponent(primaryContactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(secondaryContactNumberLabel)
                    .addComponent(secondaryContactNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(licenseCodeLabel)
                    .addComponent(licenseCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterPasswordLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(responseCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseCodeLabel))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (!Arrays.equals(jPasswordField1.getPassword(), jPasswordField2.getPassword())) {
            JOptionPane.showMessageDialog(null, "Your two passwords are inconsistent.\n"
                    + "Please check your input.");
            return;
        }
        
        UserAdmin userAdmin = new UserAdminAdapter();
        Long customerId = Long.valueOf(customerIdField.getText());
        String customerName = customerNameField.getText();
        String propertyAddress = propertyAddressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String emailAddress = emailAddressField.getText();
        String primaryContactNumber = primaryContactNumberField.getText();
        String secondaryContactNumber = secondaryContactNumberField.getText();
        String licenseCode = licenseCodeField.getText();
        String masterPassword = String.valueOf(jPasswordField1.getPassword());
        String responseCode = responseCodeField.getText();
        
        CustomerInfo newCustomerInfo= ViewController.getInstance().getCurrentUser();
        newCustomerInfo.setCustomerId(customerId);
        newCustomerInfo.setCustomerName(customerName);
        newCustomerInfo.setPropertyAddress(propertyAddress);
        newCustomerInfo.setPhoneNumber(phoneNumber);
        newCustomerInfo.setEmailAddress(emailAddress);
        newCustomerInfo.setPrimaryContactNumber(primaryContactNumber);
        newCustomerInfo.setSecondaryContactNumber(secondaryContactNumber);
        newCustomerInfo.setLicenseCode(licenseCode);
        newCustomerInfo.setMasterPassword(masterPassword);
        newCustomerInfo.setResponseCode(responseCode);
        
        if (userAdmin.editUser(newCustomerInfo)) {
            JOptionPane.showMessageDialog(null, "Successfully edit your account.\n"
                    + "You can use your phone number: " + phoneNumber + " or your email: " + emailAddress + "\n"
                    + "as username along with your password to log in later.\n"
                    + "Thank you!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to edit your account.\n"
                    + "Please check your input.\n"
                    + "Please do not use too long or illegal characters.");
        }
    }//GEN-LAST:event_saveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField customerIdField;
    private javax.swing.JLabel customerIdLabel;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JLabel customerNameLabel;
    private javax.swing.JTextField emailAddressField;
    private javax.swing.JLabel emailAddressLabel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField licenseCodeField;
    private javax.swing.JLabel licenseCodeLabel;
    private javax.swing.JLabel masterPasswordLabel;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField primaryContactNumberField;
    private javax.swing.JLabel primaryContactNumberLabel;
    private javax.swing.JTextField propertyAddressField;
    private javax.swing.JLabel propertyAddressLabel;
    private javax.swing.JTextField responseCodeField;
    private javax.swing.JLabel responseCodeLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField secondaryContactNumberField;
    private javax.swing.JLabel secondaryContactNumberLabel;
    // End of variables declaration//GEN-END:variables
}
