/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author nanda
 */
public class MPASUtility extends javax.swing.JFrame {

    /**
     * Creates new form MPASUtility
     */
    Connection con;
    Statement s;
    ResultSet rs;
    public MPASUtility() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mpas", "root", "root");
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Database Error!");
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }
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

        analysisname = new javax.swing.JTextField();
        analysisnamelabel = new javax.swing.JLabel();
        analystlabel = new javax.swing.JLabel();
        analystbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        analysisnamelabel.setText("Movie Name");

        analystlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        analystlabel.setText("Hello, Analyst!");

        analystbutton.setText("Fetch Data");
        analystbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analystbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(analystlabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(analysisnamelabel)
                        .addGap(18, 18, 18)
                        .addComponent(analysisname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(analystbutton)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(analystlabel)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(analysisnamelabel)
                    .addComponent(analysisname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(analystbutton)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void analystbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analystbuttonActionPerformed
        // TODO add your handling code here:
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from MovieData where name='"+analysisname.getText()+"';");
            if(!rs.first()) throw new Exception();
            this.setVisible(false);
            this.dispose();
            new MPASAnalysisData(analysisname.getText()).setVisible(true);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No such movie!");
        }
    }//GEN-LAST:event_analystbuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField analysisname;
    private javax.swing.JLabel analysisnamelabel;
    private javax.swing.JButton analystbutton;
    private javax.swing.JLabel analystlabel;
    // End of variables declaration//GEN-END:variables
}
