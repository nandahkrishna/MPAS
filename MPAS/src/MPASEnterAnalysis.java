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
public class MPASEnterAnalysis extends javax.swing.JFrame {

    /**
     * Creates new form EnterAnalysis
     */
    Connection con;
    Statement s;
    ResultSet rs;
    String movie;
    public MPASEnterAnalysis(String movie) {
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
        this.movie = movie;
        initComponents();
        try {
            s = con.createStatement();
            rs = s.executeQuery("select analysis from MovieAnalysis where name='"+movie+"';");
            if(rs.first()) {
                analysistext.setText(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportlabel = new javax.swing.JLabel();
        scrollpane = new javax.swing.JScrollPane();
        analysistext = new javax.swing.JTextArea();
        submitbutton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        reportlabel.setText("Analysis Report");

        analysistext.setColumns(20);
        analysistext.setRows(5);
        scrollpane.setViewportView(analysistext);

        submitbutton.setText("Submit Report");
        submitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollpane))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(reportlabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(submitbutton)))
                        .addGap(0, 144, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitbutton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitbuttonActionPerformed
        // TODO add your handling code here:
        try {
            s = con.createStatement();
            rs = s.executeQuery("select * from MovieAnalysis where name='"+movie+"';");
            if(!rs.first()) {
                s = con.createStatement();
                s.execute("insert into MovieAnalysis values('"+movie+"', '"+analysistext.getText()+"');");
            }
            else {
                s = con.createStatement();
                s.execute("update MovieAnalysis set analysis='"+analysistext.getText()+"' where name='"+movie+"';");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error!");
            this.setVisible(false);
            this.dispose();
            System.exit(0);
        }
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_submitbuttonActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea analysistext;
    private javax.swing.JLabel reportlabel;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JButton submitbutton;
    // End of variables declaration//GEN-END:variables
}
