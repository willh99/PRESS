package Visual;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        this.getContentPane().setBackground(new Color(50,50,50));
        
        Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(D.width/2, D.height/2 );
        homePanel.setBackground(new Color(200,100,100));
        graphPanel.setBackground(new Color(200,100,100));
        dataPanel.setBackground(new Color(200,100,100));
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        Status_label = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        graphButton = new javax.swing.JButton();
        downloadButton = new javax.swing.JButton();
        haltButton = new javax.swing.JButton();
        dataButton = new javax.swing.JButton();
        graphPanel = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        dataTabbedPane = new javax.swing.JTabbedPane();
        priceScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        voltsScrollPane = new javax.swing.JScrollPane();
        voltsTable = new javax.swing.JTable();
        tempScrollPane = new javax.swing.JScrollPane();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        EditMenuItem = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        feelingMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        AboutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        HelpMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("P.R.E.S.S.");
        setBackground(new java.awt.Color(50, 50, 50));

        mainPanel.setForeground(new java.awt.Color(204, 255, 204));
        mainPanel.setLayout(new java.awt.CardLayout());

        homePanel.setForeground(new java.awt.Color(204, 204, 204));

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

        buyButton.setText("Buy");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        sellButton.setText("Sell");
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        Status_label.setBackground(new java.awt.Color(255, 255, 255));
        Status_label.setFont(new java.awt.Font("HP Simplified Light", 1, 18)); // NOI18N
        Status_label.setForeground(new java.awt.Color(255, 255, 255));
        Status_label.setText("System Status: Init");

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.000.jpg"))); // NOI18N
        Logo.setText("jLabel2");
        Logo.setMaximumSize(new java.awt.Dimension(350, 150));
        Logo.setMinimumSize(new java.awt.Dimension(350, 150));
        Logo.setName(""); // NOI18N
        Logo.setPreferredSize(new java.awt.Dimension(350, 150));

        graphButton.setText("See Live Data");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
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

        dataButton.setText("See Daily Prices");
        dataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataButtonActionPerformed(evt);
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
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(Status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sellButton))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(graphButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homePanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buyButton))
                            .addGroup(homePanelLayout.createSequentialGroup()
                                .addComponent(downloadButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(haltButton)))))
                .addContainerGap())
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(sellButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyButton)
                    .addComponent(graphButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(haltButton)
                    .addComponent(dataButton)
                    .addComponent(downloadButton))
                .addContainerGap())
        );

        mainPanel.add(homePanel, "card3");

        graphPanel.setBorder(new javax.swing.border.MatteBorder(null));
        graphPanel.setLayout(new java.awt.GridBagLayout());
        mainPanel.add(graphPanel, "card2");

        homeButton.setText("Back to Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        dataTabbedPane.setForeground(new java.awt.Color(102, 102, 102));

        dataTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ){public boolean isCellEditable(int row, int column){
            return false;}
    }
    );
    priceScrollPane.setViewportView(dataTable);

    dataTabbedPane.addTab("Price Data", priceScrollPane);

    voltsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){
        return false;
    }}
    );
    voltsScrollPane.setViewportView(voltsTable);

    dataTabbedPane.addTab("Voltage Data", voltsScrollPane);
    dataTabbedPane.addTab("Temperature Data", tempScrollPane);

    javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
    dataPanel.setLayout(dataPanelLayout);
    dataPanelLayout.setHorizontalGroup(
        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dataPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dataTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(homeButton)))
            .addContainerGap())
    );
    dataPanelLayout.setVerticalGroup(
        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dataPanelLayout.createSequentialGroup()
            .addComponent(dataTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addComponent(homeButton)
            .addContainerGap())
    );

    mainPanel.add(dataPanel, "card4");

    fileMenu.setText("File");

    connectMenuItem.setText("Connect to a Device");
    connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            connectMenuItemActionPerformed(evt);
        }
    });
    fileMenu.add(connectMenuItem);

    EditMenuItem.setText("Edit");
    fileMenu.add(EditMenuItem);

    exitMenuItem.setText("Exit");
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
        }
    });
    fileMenu.add(exitMenuItem);

    mainMenuBar.add(fileMenu);

    settingsMenu.setText("Settings");

    feelingMenuItem.setText("Look & Feel");
    settingsMenu.add(feelingMenuItem);

    mainMenuBar.add(settingsMenu);

    aboutMenu.setText("About");

    AboutMenuItem.setText("About");
    AboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            AboutMenuItemActionPerformed(evt);
        }
    });
    aboutMenu.add(AboutMenuItem);

    mainMenuBar.add(aboutMenu);

    helpMenu.setText("Help");

    HelpMenuItem.setText("Help");
    helpMenu.add(HelpMenuItem);

    mainMenuBar.add(helpMenu);

    setJMenuBar(mainMenuBar);

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
        JOptionPane.showMessageDialog(null, "Welcome to P.R.E.S.S.\n Version: Alpha 2.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutMenuItemActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        // Change status JSON to Sell (Buy=false, Sell=true)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Sell\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(true, false, "ManualOverride");
            Status_label.setText("System Status: Buy");
        }
    }//GEN-LAST:event_buyButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        // Change status JSON to Buy (Buy=true, Sell=false)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Buy\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(false, true, "ManualOverride");
            Status_label.setText("System Status: Sell");
        }
    }//GEN-LAST:event_sellButtonActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        
        // Get ChartPanels from data and add them to a container panel
        //JFrame frame = new JFrame();;
        //JPanel container = new JPanel();
        graphPanel.setLayout(new GridBagLayout());
        graphPanel.setPreferredSize(mainPanel.getSize());
        GridBagConstraints c =  new GridBagConstraints();
        JPanel panel1 = processData.plotPower();
        JPanel panel2 = processData.plotPriceData();
        
        // Button to return to home 'page'
        // Check out this cool lamda expression!
        JButton homeButton = new JButton();
        homeButton.setText("Back to Home");
        homeButton.addActionListener((ActionEvent e) -> {
            CardLayout cL = (CardLayout) mainPanel.getLayout();
            cL.show(mainPanel, "card3");
            
            graphPanel.removeAll();
            graphPanel.repaint();
            homePanel.repaint();
        });
        
        processData.analyizePriceData();
        
        /*Dimension D = this.getSize();
        D.setSize(D.getWidth()/2, D.getHeight()/2);
        
        panel1.setPreferredSize(D);
        panel2.setPreferredSize(D);*/

        // Add container to a panel which is part of the card layout
        c.gridx = 2;
        c.gridy = 2;
        graphPanel.add(homeButton, c);
        
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = .5;
        c.weighty = .5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        graphPanel.add(panel1, c);
        c.gridx = 0;
        c.gridy = 1;
        graphPanel.add(panel2, c);
        
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card2");
    }//GEN-LAST:event_graphButtonActionPerformed

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

    private void dataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataButtonActionPerformed
        // Go to the data panel and display the contents of the Today's Data CSV
        processData.populateTable("TodaysData.csv", dataTable);
        processData.populateTableWithJSON("v_log.json", voltsTable);

        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card4");                    
        
    }//GEN-LAST:event_dataButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card3");
    }//GEN-LAST:event_homeButtonActionPerformed

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
    private javax.swing.JMenuItem AboutMenuItem;
    private javax.swing.JMenu EditMenuItem;
    private javax.swing.JMenuItem HelpMenuItem;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Status_label;
    private javax.swing.JMenu aboutMenu;
    private javax.swing.JButton buyButton;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JButton dataButton;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JTabbedPane dataTabbedPane;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton downloadButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem feelingMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton graphButton;
    private javax.swing.JPanel graphPanel;
    private javax.swing.JButton haltButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton homeButton;
    private javax.swing.JPanel homePanel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane priceScrollPane;
    private javax.swing.JButton sellButton;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JScrollPane tempScrollPane;
    private javax.swing.JScrollPane voltsScrollPane;
    private javax.swing.JTable voltsTable;
    // End of variables declaration//GEN-END:variables
}