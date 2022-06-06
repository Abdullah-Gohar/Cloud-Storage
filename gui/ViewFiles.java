package gui;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import comp.*;

public class ViewFiles extends javax.swing.JFrame {

    Client client = null;
    String[] files;
    JRadioButton currentSelected;
    public ViewFiles() {
        initComponents();
    }

    public ViewFiles(Client c) {
        client=c;
        files= c.FilesNames();
        initComponents();

    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        Delete = new javax.swing.JButton();
        Download = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Files");

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        
        
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Download.setText("Download");
        Download.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(189, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(96, 96, 96)
                        .addComponent(Back))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(Delete)
                .addGap(50, 50, 50)
                .addComponent(Download)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Back)))
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Delete)
                    .addComponent(Download))
                .addContainerGap())
        );
        setResizable(false);
        for(int i=0; i< files.length; i++) {
            JRadioButton b1= new JRadioButton();
            b1.setActionCommand(files[i]);
            b1.addActionListener(new selectButton());
            jPanel1.add(b1);
            b1.setText(files[i]);
            buttonGroup1.add(b1);

        }
        Download.setEnabled(false);
        Delete.setEnabled(false);
        // JScrollPane pane = new JScrollPane(jPanel1);
        // pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane scrollPane = new JScrollPane(jPanel1,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        scrollPane.setViewportView(jPanel1);


        add(scrollPane);




        pack();
    }// </editor-fold>                        

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // dispose();
        // Client1 c1 = new Client1();
        // c1.setVisible(true);
    }                                    

    private void DownloadActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String name = buttonGroup1.getSelection().getActionCommand();
        client.download(name);
        client.try_download();
        JOptionPane.showMessageDialog(null, "File Downloaded!");
    }                                        

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {                                       
        String name = buttonGroup1.getSelection().getActionCommand();
        client.delete();
        int status = client.try_delete(name);
        if (status == 1){
            JOptionPane.showMessageDialog(null, "File Deleted!");
            buttonGroup1.remove(currentSelected);
            jPanel1.remove(currentSelected);
            jPanel1.revalidate();
            Delete.setEnabled(false);
            Download.setEnabled(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "File failed to be deleted!");
        }
        revalidate();
    }                                      

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewFiles().setVisible(true);
            }
        });
    }

    private class selectButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // if move_selected is true
            currentSelected= (JRadioButton) e.getSource();
            System.out.println(((JRadioButton) e.getSource()).getText());
            Delete.setEnabled(true);
            Download.setEnabled(true);

        }

    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Back;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Download;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
