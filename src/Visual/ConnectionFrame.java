/*
 * Copyright (C) 2018 Will Horowitz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Visual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import useData.Globals;
import javax.swing.DefaultComboBoxModel;
import useData.Scheduler;

/**
 *
 * @author will
 */
public class ConnectionFrame extends javax.swing.JFrame {

    /**
     * Creates new form connectionFrame
     * @param h
     */
    
    private ArrayList<String> hosts;
    
    public ConnectionFrame(PRESS_hud h) {
        hosts = getSavedHosts();
        hud = h;
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

        jScrollBar1 = new javax.swing.JScrollBar();
        mainPanel = new javax.swing.JPanel();
        hostComboBox = new javax.swing.JComboBox<>();
        portSpinner = new javax.swing.JSpinner();
        hostTextField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        hostComboLabel = new javax.swing.JLabel();
        portComboLabel = new javax.swing.JLabel();
        hostTextLabel = new javax.swing.JLabel();
        currentValueLabel = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        applyBtn = new javax.swing.JButton();
        deleteHostBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        hostComboBox.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        hostComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
            new String[]{""}));
    setComboItems();

    portSpinner.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
    portSpinner.setModel(new javax.swing.SpinnerNumberModel(0, null, 65000, 1));
    portSpinner.setValue(Globals.getHostPort());

    hostTextField.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N

    titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
    titleLabel.setText("Choose Your Host and Port");

    hostComboLabel.setText("Saved Hosts");

    portComboLabel.setText("Port (only change if necessary)");

    hostTextLabel.setText("Enter a New Host:");

    currentValueLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    currentValueLabel.setText("Current host and port: ( "
        + Globals.getHostName().getHostAddress() + " , "
        + Globals.getHostPort() + " )"
    );

    addBtn.setText("Add New");
    addBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            addBtnActionPerformed(evt);
        }
    });

    applyBtn.setText("Apply Settings");
    applyBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            applyBtnActionPerformed(evt);
        }
    });

    deleteHostBtn.setText("Remove Saved Host");
    deleteHostBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteHostBtnActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
        mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(mainPanelLayout.createSequentialGroup()
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(currentValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hostTextLabel)
                                .addComponent(hostComboLabel)
                                .addComponent(titleLabel)
                                .addComponent(hostComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(portComboLabel))
                            .addGap(0, 98, Short.MAX_VALUE))
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(portSpinner)
                                .addComponent(hostTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(applyBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteHostBtn)))
            .addContainerGap())
    );
    mainPanelLayout.setVerticalGroup(
        mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(mainPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(hostComboLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(hostComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(portComboLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(applyBtn)
                .addComponent(portSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(hostTextLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(hostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(addBtn))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteHostBtn)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
            .addComponent(currentValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBtnActionPerformed
        // Sets new host and port name in the Globals class
        Globals.setHostName(hostComboBox.getSelectedItem().toString());
        Globals.setHostPort((int) portSpinner.getValue());
        currentValueLabel.setText("Current host and port: ( "
                                + Globals.getHostName().getHostAddress() + " , "
                                + Globals.getHostPort() + " )");
        Scheduler.updateTask("STATUS");
    }//GEN-LAST:event_applyBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Adds a new IP address string to the hostComboBox
        // Puts new IP at beginning of hosts List and then rewrites file
        // Combobox.
        String text = hostTextField.getText();
        
        if(validIP(text)){
            // Write new IP to file
            BufferedWriter writer = null;
            // If the IP doesn't already exist in the list
            if(((DefaultComboBoxModel)hostComboBox.getModel()).getIndexOf(text) == -1) {
                hosts.add(hosts.get(0));
                hosts.set(0, text);
                saveHosts();
                setComboItems();
            }
        }
            
    }//GEN-LAST:event_addBtnActionPerformed

    
// Delete the IP currently select in the hostComboBox
    private void deleteHostBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteHostBtnActionPerformed
        String selected = (String) hostComboBox.getSelectedItem();
        int index=0;
        
        while(index<hosts.size()){
            if(hosts.get(index).equals(selected))
                break;
            index++;
        }
        
        hosts.remove(index);
        saveHosts();
        setComboItems();
    }//GEN-LAST:event_deleteHostBtnActionPerformed

    // Save IPs in 'hosts' to a file
    private void saveHosts(){
        
        File path = new File("Settings/");
        File file = new File(path, "hosts.txt");
        path.mkdir();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));){
            writer.write(hosts.get(0));
            writer.newLine();

            for(int i=1; i<hosts.size(); i++){
                writer.append(hosts.get(i));
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            return !ip.endsWith(".");
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private ArrayList<String> getSavedHosts(){
        ArrayList<String> hostList = new ArrayList<>();
        File path = new File("Settings/");
        File file = new File(path, "hosts.txt");
        if(!file.exists()){
            path.mkdir();
            hostList.add("127.0.0.1");
            return hostList;
        }
        
        // Read file line-by-line to get lists of hosts
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String line = null;
            while (( line = input.readLine()) != null)
                hostList.add(line);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(ConnectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return hostList;
    }
    
    private void setComboItems(){
        hostComboBox.removeAllItems();
        for(int i=0; i<hosts.size(); i++){
            hostComboBox.addItem(hosts.get(i));
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionFrame(new PRESS_hud()).setVisible(true);
            }
        });
    }

    private PRESS_hud hud;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton applyBtn;
    private javax.swing.JLabel currentValueLabel;
    private javax.swing.JButton deleteHostBtn;
    private javax.swing.JComboBox<String> hostComboBox;
    private javax.swing.JLabel hostComboLabel;
    private javax.swing.JTextField hostTextField;
    private javax.swing.JLabel hostTextLabel;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel portComboLabel;
    private javax.swing.JSpinner portSpinner;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
