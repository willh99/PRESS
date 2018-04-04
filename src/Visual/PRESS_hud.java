package Visual;


import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import useData.parseJSON;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import useData.ClientConnect;
import useData.Globals;
import useData.SendAppStatus;
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
        this.getContentPane().setBackground(new Color(255,255,255));
        
        Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(Integer.D.width/2, D.height/2 );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        navPanel = new javax.swing.JPanel();
        dataButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();
        systemButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        buyButton = new javax.swing.JButton();
        sellButton = new javax.swing.JButton();
        Status_label = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        downloadButton = new javax.swing.JButton();
        haltButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        useAlgorithmBtn = new javax.swing.JButton();
        dataPanel = new javax.swing.JPanel();
        dataTabbedPane = new javax.swing.JTabbedPane();
        priceTabPanel = new javax.swing.JPanel();
        priceScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        priceTabGraphPanel = new javax.swing.JPanel();
        voltsTabPanel = new javax.swing.JPanel();
        voltsScrollPane = new javax.swing.JScrollPane();
        voltsTable = new javax.swing.JTable();
        voltsTabGraphPanel = new javax.swing.JPanel();
        tempTabPanel = new javax.swing.JPanel();
        tempScrollPane = new javax.swing.JScrollPane();
        tempTable = new javax.swing.JTable();
        tempTabGraphPanel = new javax.swing.JPanel();
        systemPanel = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        EditMenuItem = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        feelingMenuItem = new javax.swing.JMenuItem();
        connectionMenuItem = new javax.swing.JMenuItem();
        aboutMenu = new javax.swing.JMenu();
        AboutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        HelpMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("P.R.E.S.S.");
        setBackground(new java.awt.Color(50, 50, 50));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        navPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dataButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graphicon-sm.png"))); // NOI18N
        dataButton.setText("See Your Data");
        dataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataButtonActionPerformed(evt);
            }
        });

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/homeicon.png"))); // NOI18N
        homeButton.setText("     Home");
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        systemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/battery-sm.png"))); // NOI18N
        systemButton.setText(" System Status");
        systemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                systemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout navPanelLayout = new javax.swing.GroupLayout(navPanel);
        navPanel.setLayout(navPanelLayout);
        navPanelLayout.setHorizontalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, navPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(systemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(homeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        navPanelLayout.setVerticalGroup(
            navPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(navPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(systemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 0);
        getContentPane().add(navPanel, gridBagConstraints);
        navPanel.setBackground(new Color(255,153,102));
        navPanel.getAccessibleContext().setAccessibleDescription("");

        mainPanel.setForeground(new java.awt.Color(204, 255, 204));
        mainPanel.setLayout(new java.awt.CardLayout());

        homePanel.setForeground(new java.awt.Color(204, 204, 204));
        homePanel.setBackground(new Color(255,153,102));

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
        Status_label.setText("System Status: Use Algorithm");

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGO.000.jpg"))); // NOI18N
        Logo.setText("jLabel2");
        Logo.setMaximumSize(new java.awt.Dimension(350, 150));
        Logo.setMinimumSize(new java.awt.Dimension(350, 150));
        Logo.setName(""); // NOI18N
        Logo.setPreferredSize(new java.awt.Dimension(350, 150));

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

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(4);
        jTextArea1.setText("     Welcome to the Price Reactive Energy Storage System (PRESS). After the instalation of the of battery system, this application can be used to connect with the battery, analyze the system's data, and manually override the buy/sell state of the battery.\n     Start saving energy and dollars with the market efficient provided by PRESS!");
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        useAlgorithmBtn.setText("Use Algorithm");
        useAlgorithmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useAlgorithmBtnActionPerformed(evt);
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
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(Status_label, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addComponent(downloadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(useAlgorithmBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                                .addComponent(sellButton)
                                .addGap(18, 18, 18)
                                .addComponent(buyButton))
                            .addComponent(haltButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyButton)
                    .addComponent(sellButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(haltButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(downloadButton)
                    .addComponent(useAlgorithmBtn))
                .addContainerGap())
        );

        mainPanel.add(homePanel, "card3");

        dataPanel.setBackground(new Color(255,153,102));

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

    priceTabGraphPanel.setLayout(new java.awt.GridBagLayout());

    javax.swing.GroupLayout priceTabPanelLayout = new javax.swing.GroupLayout(priceTabPanel);
    priceTabPanel.setLayout(priceTabPanelLayout);
    priceTabPanelLayout.setHorizontalGroup(
        priceTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, priceTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(priceTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(priceTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(priceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addContainerGap())
    );
    priceTabPanelLayout.setVerticalGroup(
        priceTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, priceTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(priceTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(priceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    dataTabbedPane.addTab("Price Data", priceTabPanel);

    voltsTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    voltsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){
        return false;}
    }
    );
    voltsScrollPane.setViewportView(voltsTable);

    voltsTabGraphPanel.setLayout(new java.awt.GridBagLayout());

    javax.swing.GroupLayout voltsTabPanelLayout = new javax.swing.GroupLayout(voltsTabPanel);
    voltsTabPanel.setLayout(voltsTabPanelLayout);
    voltsTabPanelLayout.setHorizontalGroup(
        voltsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, voltsTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(voltsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(voltsTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(voltsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addContainerGap())
    );
    voltsTabPanelLayout.setVerticalGroup(
        voltsTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, voltsTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(voltsTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(voltsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    dataTabbedPane.addTab("Voltage Data", voltsTabPanel);

    tempTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    tempTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ){public boolean isCellEditable(int row, int column){
        return false;}
    }
    );
    tempScrollPane.setViewportView(tempTable);

    tempTabGraphPanel.setLayout(new java.awt.GridBagLayout());

    javax.swing.GroupLayout tempTabPanelLayout = new javax.swing.GroupLayout(tempTabPanel);
    tempTabPanel.setLayout(tempTabPanelLayout);
    tempTabPanelLayout.setHorizontalGroup(
        tempTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tempTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(tempTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(tempTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tempScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
            .addContainerGap())
    );
    tempTabPanelLayout.setVerticalGroup(
        tempTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tempTabPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(tempTabGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tempScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    dataTabbedPane.addTab("Tempurature Data", tempTabPanel);

    javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
    dataPanel.setLayout(dataPanelLayout);
    dataPanelLayout.setHorizontalGroup(
        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dataPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dataTabbedPane)
            .addContainerGap())
    );
    dataPanelLayout.setVerticalGroup(
        dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(dataPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(dataTabbedPane)
            .addContainerGap())
    );

    mainPanel.add(dataPanel, "card4");

    systemPanel.setBackground(new Color(255,153,102));
    systemPanel.setLayout(new java.awt.GridBagLayout());
    mainPanel.add(systemPanel, "card5");

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.ipadx = 50;
    gridBagConstraints.ipady = 100;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(6, 12, 6, 6);
    getContentPane().add(mainPanel, gridBagConstraints);

    fileMenu.setText("File");

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
    feelingMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            feelingMenuItemActionPerformed(evt);
        }
    });
    settingsMenu.add(feelingMenuItem);

    connectionMenuItem.setText("Edit Connection Settings");
    connectionMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            connectionMenuItemActionPerformed(evt);
        }
    });
    settingsMenu.add(connectionMenuItem);

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
        JOptionPane.showMessageDialog(null, "Welcome to P.R.E.S.S.\n Version: Beta 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutMenuItemActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        // Change status JSON to Sell (Buy=false, Sell=true)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Buy\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(true, false, "ManualOverride");
            Status_label.setText("System Status: Buy");
            //ClientConnect c = new ClientConnect(Globals.getHostName(), Globals.getHostPort(), Globals.getTimeout());
            //c.sendFile("appstatus.json");
            SendAppStatus sas = new SendAppStatus();
            sas.start();
        }
    }//GEN-LAST:event_buyButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        // Change status JSON to Buy (Buy=true, Sell=false)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Sell\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(false, true, "ManualOverride");
            Status_label.setText("System Status: Sell");
            //ClientConnect c = new ClientConnect(Globals.getHostName(), Globals.getHostPort(), Globals.getTimeout());
            //c.sendFile("appstatus.json");
            SendAppStatus sas = new SendAppStatus();
            sas.start();
        }
    }//GEN-LAST:event_sellButtonActionPerformed

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

    private void haltButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_haltButtonActionPerformed
        // Set status JSON to halt (Sell=false Buy=false)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Halt\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(false, false, "ManualOverride");
            Status_label.setText("System Status: Halt");
            //ClientConnect c = new ClientConnect(Globals.getHostName(), Globals.getHostPort(), Globals.getTimeout());
            //c.sendFile("appstatus.json");
            SendAppStatus sas = new SendAppStatus();
            sas.start();
        }
    }//GEN-LAST:event_haltButtonActionPerformed

    private void dataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataButtonActionPerformed
        // Go to the data panel and display the contents of the Today's Data CSV
        processData.populateTable("TodaysData.csv", dataTable);
        processData.populateTableWithJSON("v_log.json", voltsTable);
        processData.populateTableWithJSON("t_log.json", tempTable);
        
        priceTabGraphPanel.removeAll();
        voltsTabGraphPanel.removeAll();
        tempTabGraphPanel.removeAll();
        JPanel priceG = processData.plotPriceData();
        JPanel voltG = processData.plotPower();
        JPanel tempG = processData.plotTemp();
        
        GridBagConstraints c =  new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        priceTabGraphPanel.add(priceG, c);
        voltsTabGraphPanel.add(voltG, c);
        tempTabGraphPanel.add(tempG, c);
        
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card4");                    
    }//GEN-LAST:event_dataButtonActionPerformed

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        // Returns to the home panel
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card3");
    }//GEN-LAST:event_homeButtonActionPerformed

    private void feelingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feelingMenuItemActionPerformed
        // Create a new frame to handle look & feel settings
        // Takes this PRESS_hud as a variable so it can change its look and feel
        FeelingFrame frame = new FeelingFrame(this);
        frame.setVisible(true);
    }//GEN-LAST:event_feelingMenuItemActionPerformed

    private void connectionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionMenuItemActionPerformed
        // Opens up a connection frame where use can change server host
        ConnectionFrame frame = new ConnectionFrame(this);
        frame.setVisible(true);
    }//GEN-LAST:event_connectionMenuItemActionPerformed

    private void useAlgorithmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useAlgorithmBtnActionPerformed
        // Set status JSON to use algorithm, i.e. override off (Sell=true Buy=true)
        int response  = JOptionPane.showConfirmDialog(null, "Are you sure you would like to change the system state to \"Halt\"?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION)
        {
            parseJSON.createStatusJSON(true, true, "ManualOverride");
            Status_label.setText("System Status: Automatic");
            //ClientConnect c = new ClientConnect(Globals.getHostName(), Globals.getHostPort(), Globals.getTimeout());
            //c.sendFile("appstatus.json");
            SendAppStatus sas = new SendAppStatus();
            sas.start();
        }
    }//GEN-LAST:event_useAlgorithmBtnActionPerformed

    private void systemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_systemButtonActionPerformed
        // TODO add your handling code here:
        JPanel charge = processData.showChargeLevel();
        
        GridBagConstraints c =  new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0; c.gridy = 0;
        c.gridwidth = 1; c.gridheight = 2;
        c.weightx = .25; c.weighty = .25;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        
        systemPanel.add(charge, c);
        
        CardLayout cL = (CardLayout) mainPanel.getLayout();
        cL.show(mainPanel, "card5");
        
        JLabel profitLabel = new JLabel("Estimated Profit: $" + processData.getProfitMargin());
        profitLabel.setFont(new Font("Serif", Font.PLAIN, 28));
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 1; c.gridheight = 1;
        c.weightx = 0; c.weighty = .1;
        systemPanel.add(profitLabel, c);
    }//GEN-LAST:event_systemButtonActionPerformed

    
    private static Icon resizeIcon(String filename, int resizedWidth, int resizedHeight) {
        ImageIcon icon = new ImageIcon("/images/" + filename);
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
        return new ImageIcon(resizedImage);
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
    private javax.swing.JMenuItem connectionMenuItem;
    private javax.swing.JButton dataButton;
    protected javax.swing.JPanel dataPanel;
    private javax.swing.JTabbedPane dataTabbedPane;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton downloadButton;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenuItem feelingMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton haltButton;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton homeButton;
    protected javax.swing.JPanel homePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    protected javax.swing.JPanel navPanel;
    private javax.swing.JScrollPane priceScrollPane;
    private javax.swing.JPanel priceTabGraphPanel;
    private javax.swing.JPanel priceTabPanel;
    private javax.swing.JButton sellButton;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JButton systemButton;
    protected javax.swing.JPanel systemPanel;
    private javax.swing.JScrollPane tempScrollPane;
    private javax.swing.JPanel tempTabGraphPanel;
    private javax.swing.JPanel tempTabPanel;
    private javax.swing.JTable tempTable;
    private javax.swing.JButton useAlgorithmBtn;
    private javax.swing.JScrollPane voltsScrollPane;
    private javax.swing.JPanel voltsTabGraphPanel;
    private javax.swing.JPanel voltsTabPanel;
    private javax.swing.JTable voltsTable;
    // End of variables declaration//GEN-END:variables
}
