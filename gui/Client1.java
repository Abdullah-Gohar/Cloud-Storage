package gui;
import comp.*; 

import javax.swing.*;
public class Client1 extends javax.swing.JFrame {
    Client client = null;
    public Client1(Client c) {
        client = c; 
        initComponents();
    }

    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        UploadB = new javax.swing.JButton();
        ViewFilesB = new javax.swing.JButton();
        Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CLIENT");

        UploadB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UploadB.setText("Upload File");
        UploadB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UploadBActionPerformed(evt);
            }
        });

        ViewFilesB.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewFilesB.setText("View Files");
        ViewFilesB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewFilesBActionPerformed(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ViewFilesB, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UploadB, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Back)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(Back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addComponent(UploadB)
                .addGap(18, 18, 18)
                .addComponent(ViewFilesB, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void UploadBActionPerformed(java.awt.event.ActionEvent evt) {                                        
        JFileChooser jf = new JFileChooser();
        jf.showSaveDialog(null);
    }                                       

    private void ViewFilesBActionPerformed(java.awt.event.ActionEvent evt) {                                           
        dispose();
        ViewFiles v = new ViewFiles();
        v.setVisible(true);
    }                                          

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {                                     
        dispose();
        Main m = new Main();
        m.setVisible(true);
    }                                    

    // public static void main(String args[]) {
      
    //     java.awt.EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             new Client1().setVisible(true);
    //         }
    //     });
    // }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Back;
    private javax.swing.JButton UploadB;
    private javax.swing.JButton ViewFilesB;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration                   
}
