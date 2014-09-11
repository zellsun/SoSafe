/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sosafe.view;

import java.net.URL;
import sosafe.report.HelpContentsReader;
import sosafe.report.HTMLFormatter;
import sosafe.report.SimpleHTMLFormatter;


/**
 *
 * @author Z
 */
public class AboutPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    public AboutPanel() {
        initComponents();
        initAbout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aboutLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1260, 411));

        aboutLabel.setText("About");
        aboutLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(aboutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(aboutLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void initAbout() {
        HTMLFormatter formatter = new SimpleHTMLFormatter();
        HelpContentsReader reader = new HelpContentsReader(formatter);
        URL url = getClass().getResource("/sosafe/res/About.txt");
        reader.buildHelpContents(url);
        aboutLabel.setText(formatter.getResult(false));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutLabel;
    // End of variables declaration//GEN-END:variables
}
