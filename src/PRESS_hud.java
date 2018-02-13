
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import useData.parseJSON;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import useData.processData;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author will
 */
public class PRESS_hud extends javax.swing.JFrame {

    /**
     * Creates new form PRESS_hud
     */
    public PRESS_hud() {
        initComponents();
        this.setTitle("P.R.E.S.S");
        this.getContentPane().setBackground(new Color(0,200,100));
        
        Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(D.width/2, D.height/2 );
        //mainPanel.setSize(this.getContentPane().getSize());
        //dataPanel.setSize(mainPanel.getSize());
        //homePanel.setSize(mainPanel.getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        BuyButton = new javax.swing.JButton();
        SellButton = new javax.swing.JButton();
        Status_label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Logo = new javax.swing.JLabel();
        DataButton = new javax.swing.JButton();
        downloadButton = new javax.swing.JButton();
        haltButton = new javax.swing.JButton();
        dataPanel = new javax.swing.JPanel();
        MainMenuBar = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        EditMenuItem = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        AboutMenu = new javax.swing.JMenu();
        AboutMenuItem = new javax.swing.JMenuItem();
        HelpMenu = new javax.swing.JMenu();
        HelpMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 102));

        mainPanel.setLayout(new java.awt.CardLayout());

        BuyButton.setText("Buy");
        BuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyButtonActionPerformed(evt);
            }
        });

        SellButton.setText("Sell");
        SellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SellButtonActionPerformed(evt);
            }
        });

        Status_label.setText("System Status:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.000.jpg"))); // NOI18N
        Logo.setText("jLabel2");
        Logo.setMaximumSize(new java.awt.Dimension(350, 150));
        Logo.setMinimumSize(new java.awt.Dimension(350, 150));
        Logo.setName(""); // NOI18N
        Logo.setPreferredSize(new java.awt.Dimension(350, 150));

        DataButton.setText("See Live Data");
        DataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DataButtonActionPerformed(evt);
            }
        });

        downloadButton.setText("Download Data");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        haltButton.setText("Turn Off Battery");
        haltButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                haltButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addComponent(DataButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downloadButton)
                        .addGap(315, 315, 315)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SellButton)
                            .addComponent(haltButton)
                            .addComponent(BuyButton)))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214)
                        .addComponent(Status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Status_label)
                    .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(BuyButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SellButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downloadButton)
                    .addComponent(DataButton)
                    .addComponent(haltButton))
                .addGap(39, 39, 39))
        );

        mainPanel.add(homePanel, "card3");

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        mainPanel.add(dataPanel, "card2");

        FileMenu.setText("File");

        connectMenuItem.setText("Connect to a Device");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(connectMenuItem);

        EditMenuItem.setText("Edit");
        FileMenu.add(EditMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(exitMenuItem);

        MainMenuBar.add(FileMenu);

        AboutMenu.setText("About");

        AboutMenuItem.setText("About");
        AboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutMenuItemActionPerformed(evt);
            }
        });
        AboutMenu.add(AboutMenuItem);

        MainMenuBar.add(AboutMenu);

        HelpMenu.setText("Help");

        HelpMenuItem.setText("Help");
        HelpMenu.add(HelpMenuItem);

        MainMenuBar.add(HelpMenu);

        setJMenuBar(MainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed

        // Exits the program
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like exit?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void AboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutMenuItemActionPerformed
        // Just some info about the project
        JOptionPane.showMessageDialog(null, "Welcome to P.R.E.S.S.\n Version: Alpha 1.4", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutMenuItemActionPerformed

    private void BuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyButtonActionPerformed
        // Change status JSON to Sell (Buy=false, Sell=true)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Sell\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(true, false, "ManualOverride");
            Status_label.setText("System Status: Buy");
        }
    }//GEN-LAST:event_BuyButtonActionPerformed

    private void SellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SellButtonActionPerformed
        // Change status JSON to Buy (Buy=true, Sell=false)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Buy\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(false, true, "ManualOverride");
            Status_label.setText("System Status: Sell");
        }
    }//GEN-LAST:event_SellButtonActionPerformed

    private void DataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DataButtonActionPerformed
        
        // Get ChartPanels from data and add them to a container panel
        //JFrame frame = new JFrame();;
        //JPanel container = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.PAGE_AXIS));
        JPanel panel1 = processData.plotPower();
        JPanel panel2 = processData.plotPriceData();
        
        // Button to return to home 'page'
        // Check out this cool lamda expression!
        JButton homeButton = new JButton();
        homeButton.setText("Back to Home");
        homeButton.addActionListener((ActionEvent e) -> {
            CardLayout cL = (CardLayout) mainPanel.getLayout();
            cL.show(mainPanel, "card3");
            
            dataPanel.removeAll();
            dataPanel.repaint();
            homePanel.repaint();
        });
        
        processData.analyizePriceData();
        
        Dimension D = this.getSize();
        D.setSize(D.getWidth()/2, D.getHeight()/2);
        
        panel1.setPreferredSize(D);
        panel2.setPreferredSize(D);

        // Add container to a panel which is part of the card layout
        dataPanel.add(homeButton);
        dataPanel.add(panel1);
        dataPanel.add(panel2);
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card2");
    }//GEN-LAST:event_DataButtonActionPerformed

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        // Button to redownload data from the internet
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to redownload today's data?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            try {
                processData.downloadData();
            } catch (IOException ex) {
                Logger.getLogger(PRESS_hud.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        // TODO add your handling code here:
        parseJSON.readJSONArray("v_log.json");
    }//GEN-LAST:event_connectMenuItemActionPerformed

    private void haltButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_haltButtonActionPerformed
        // Set status JSON to halt (Sell=false Buy=false)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Halt\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(false, false, "ManualOverride");
            Status_label.setText("System Status: Halt");
        }
    }//GEN-LAST:event_haltButtonActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PRESS_hud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PRESS_hud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AboutMenu;
    private javax.swing.JMenuItem AboutMenuItem;
    private javax.swing.JButton BuyButton;
    private javax.swing.JButton DataButton;
    private javax.swing.JMenu EditMenuItem;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenu HelpMenu;
    private javax.swing.JMenuItem HelpMenuItem;
    private javax.swing.JLabel Logo;
    private javax.swing.JMenuBar MainMenuBar;
    private javax.swing.JButton SellButton;
    private javax.swing.JLabel Status_label;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton downloadButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton haltButton;
    private javax.swing.JPanel homePanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
