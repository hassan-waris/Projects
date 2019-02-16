/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.*;
import coursework.Database.Server;
import java.util.ArrayList;
import Both.Parcel;
import coursework.Database.ClientHandlerThread;
import coursework.Cheese;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import coursework.MyTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * create socket for both server and client sides... connect the 2 to speak each
 * other
 *
 * @author Hassan
 */
public class Board extends JFrame implements Serializable {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private Connection conn;
    private ResultSet rs;
    private Statement st;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    ArrayList columnNames = new ArrayList();
    ArrayList<Cheese> data = new ArrayList<>();
    
    public Board() {

        initComponents();
    }

    /**
     * this method connects to server
     *
     * @return no return
     * @param params
     */
    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                socket = null;
            }
        }
    }

    private void sendReceive() {
        if (ois != null) {
            
            try { 
                
//            ArrayList toSend = null;
//            
//            //Send data to server
//            oos.writeObject(new Parcel((ArrayList<Cheese>) toSend, null));
//            System.out.println(ois);

                // reply from server
                Parcel reply = (Parcel) ois.readObject();
                labelStatus.setText("Status: waiting for reply from server");
                System.out.println(reply);
                labelStatus.setText("Status: received reply from server");

                // display message on Jtable
                if (reply != null) {
                    data = reply.getData();
                    columnNames = reply.getColumnNames();
                    System.out.println(data);
                    System.out.println(columnNames);
                }

            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            labelStatus.setText("You must connect to the server first!!");
        }
    }


    private void reconnectToServer() {
        closeConnection();

        try {
            socket = new Socket("127.0.0.1", 2000);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server");
            labelStatus.setText("Connected to Server");
            
//    printWriter = new PrintWriter(socket.getOutputStream(), true);
//    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        MyTableModel myTable = new MyTableModel();
        myTable.setData(data);
        myTable.setColumn(columnNames);
        
        jTable1.setModel(myTable);

        } catch (IOException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);

        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        cheeseType = new javax.swing.JComboBox<>();
        cheeseLabel = new javax.swing.JLabel();
        style = new javax.swing.JComboBox<>();
        styleLabel = new javax.swing.JLabel();
        age = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        insertButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        strength = new javax.swing.JSpinner();
        connectButton = new javax.swing.JButton();
        jScrollPane = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        labelStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cheeseTypeText = new javax.swing.JTextField();
        styleText = new javax.swing.JTextField();
        ageText = new javax.swing.JTextField();
        approvalText = new javax.swing.JTextField();
        strengthText = new javax.swing.JTextField();
        milkText = new javax.swing.JTextField();
        quantityText = new javax.swing.JTextField();
        idText = new javax.swing.JTextField();
        originText = new javax.swing.JTextField();
        cheeseNameText = new javax.swing.JTextField();
        originText1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Title.setText("Cheese & Wine Club");

        cheeseType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cheeseType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheeseTypeActionPerformed(evt);
            }
        });

        cheeseLabel.setText("CheeseBoard");

        style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        styleLabel.setText("Style:");

        age.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Age:");

        insertButton.setText("Insert");
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");

        jLabel4.setText("Filter Option");

        jLabel5.setText("Strength:");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, "", null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "CheeseName", "CheeseType", "Style", "Origin", "Age(Months)", "Milk", "Approval", "Strength", "Quantity", "Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("ID");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("CheeseName");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("CheeseType");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Style");
            jTable1.getColumnModel().getColumn(4).setHeaderValue("Origin");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Age(Months)");
            jTable1.getColumnModel().getColumn(6).setHeaderValue("Milk");
            jTable1.getColumnModel().getColumn(7).setHeaderValue("Approval");
            jTable1.getColumnModel().getColumn(8).setHeaderValue("Strength");
            jTable1.getColumnModel().getColumn(9).setHeaderValue("Quantity");
            jTable1.getColumnModel().getColumn(10).setHeaderValue("Cost");
        }

        labelStatus.setText("Connect");

        jLabel1.setText("ID:");

        jLabel2.setText("Cheese Name:");

        jLabel6.setText("Cheese Type:");

        jLabel7.setText("Style:");

        jLabel8.setText("Origin:");

        jLabel9.setText("Age:");

        jLabel10.setText("Milk:");

        jLabel11.setText("Approval:");

        jLabel12.setText("Strength:");

        jLabel13.setText("Quantity:");

        jLabel14.setText("Cost:");

        jLabel15.setText("Insert New Cheese's");

        cheeseTypeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheeseTypeTextActionPerformed(evt);
            }
        });

        styleText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleTextActionPerformed(evt);
            }
        });

        ageText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageTextActionPerformed(evt);
            }
        });

        approvalText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvalTextActionPerformed(evt);
            }
        });

        strengthText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strengthTextActionPerformed(evt);
            }
        });

        milkText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                milkTextActionPerformed(evt);
            }
        });

        quantityText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextActionPerformed(evt);
            }
        });

        idText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTextActionPerformed(evt);
            }
        });

        originText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                originTextActionPerformed(evt);
            }
        });

        cheeseNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cheeseNameTextActionPerformed(evt);
            }
        });

        originText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                originText1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(492, 492, 492)
                        .addComponent(jLabel8)
                        .addGap(6, 6, 6)
                        .addComponent(originText1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13)
                        .addGap(6, 6, 6)
                        .addComponent(quantityText, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(updateButton)
                        .addGap(0, 0, 0)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(197, 197, 197)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(originText, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(strength, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(211, 211, 211)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel7)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cheeseTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(styleText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel12)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(approvalText, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(strengthText, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(Title))
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connectButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(cheeseLabel)
                            .addGap(18, 18, 18)
                            .addComponent(cheeseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(282, 282, 282)
                            .addComponent(jLabel1)
                            .addGap(6, 6, 6)
                            .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ageText))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(87, 87, 87)
                            .addComponent(styleLabel)
                            .addGap(18, 18, 18)
                            .addComponent(style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(211, 211, 211)
                            .addComponent(jLabel2)
                            .addGap(6, 6, 6)
                            .addComponent(cheeseNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(jLabel10)
                            .addGap(6, 6, 6)
                            .addComponent(milkText, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(142, 142, 142))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel4))
                    .addComponent(jLabel15))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(cheeseLabel))
                    .addComponent(cheeseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(ageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(styleLabel))
                    .addComponent(style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cheeseNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(milkText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel5))
                            .addComponent(strength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(cheeseTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(styleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel11)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel12))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(approvalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(strengthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(originText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13))))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertButton)
                    .addComponent(updateButton)
                    .addComponent(jButton3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(originText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cheeseTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheeseTypeActionPerformed

    }//GEN-LAST:event_cheeseTypeActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed

    }//GEN-LAST:event_insertButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

    }//GEN-LAST:event_updateButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        sendReceive();
        reconnectToServer();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void cheeseTypeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheeseTypeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cheeseTypeTextActionPerformed

    private void styleTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_styleTextActionPerformed

    private void ageTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageTextActionPerformed

    private void approvalTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvalTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_approvalTextActionPerformed

    private void strengthTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strengthTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_strengthTextActionPerformed

    private void milkTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_milkTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_milkTextActionPerformed

    private void quantityTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextActionPerformed

    private void idTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTextActionPerformed

    private void originTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_originTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_originTextActionPerformed

    private void cheeseNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cheeseNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cheeseNameTextActionPerformed

    private void originText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_originText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_originText1ActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);

            }

        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JComboBox<String> age;
    private javax.swing.JTextField ageText;
    private javax.swing.JTextField approvalText;
    private javax.swing.JLabel cheeseLabel;
    private javax.swing.JTextField cheeseNameText;
    private javax.swing.JComboBox<String> cheeseType;
    private javax.swing.JTextField cheeseTypeText;
    private javax.swing.JButton connectButton;
    private javax.swing.JTextField idText;
    private javax.swing.JButton insertButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JTextField milkText;
    private javax.swing.JTextField originText;
    private javax.swing.JTextField originText1;
    private javax.swing.JTextField quantityText;
    private javax.swing.JSpinner strength;
    private javax.swing.JTextField strengthText;
    private javax.swing.JComboBox<String> style;
    private javax.swing.JLabel styleLabel;
    private javax.swing.JTextField styleText;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

/*
public void addRowToJTable()
    {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<Cheese> list = data;
        Object rowData[] = new Object[4];
        for(int i = 0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).id;
            rowData[1] = list.get(i).cheeseName;
            rowData[2] = list.get(i).cheeseType;
            rowData[3] = list.get(i).age;
            rowData[4] = list.get(i).origin;
            rowData[5] = list.get(i).age;
            rowData[6] = list.get(i).milk;
            rowData[7] = list.get(i).approval;
            rowData[8] = list.get(i).strength;
            rowData[9] = list.get(i).quantity;
            rowData[10] = list.get(i).cost;
            model.addRow(rowData);
        }
                
    }
 */
