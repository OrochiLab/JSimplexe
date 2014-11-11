/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Yunho
 */
public class Fenetre extends javax.swing.JFrame implements MouseListener,ActionListener{

    /*============Composants 1er panel ============*/
    private Vector<JTextField> fields = new Vector();
    private Vector<JButton> btn_fields = new Vector();
    private int compteur=0;
    private GridBagConstraints cons = new GridBagConstraints();
    
    /*============Composants deuxième panel ========*/
    private Vector<TableIT> tables=new Vector();
    private Vector<JButton> btn_tables =new Vector();
    private GridBagConstraints tcon = new GridBagConstraints();
    private int tcompteur =0;
    private int iteration=0;
    private Vector<Vector<String>> data;
    /**
     * Creates new form Fenetre
     */
    public Fenetre() {
        initComponents();
        ButtonGroup grp = new ButtonGroup();
        grp.add(jRadioButton1);
        grp.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        
        /* =============== Initialisation du premier panel =============== */
        jPanel1.setLayout(new GridBagLayout());
        cons.insets= new Insets(5, 5, 5, 5);
        cons.gridx=0;
        cons.gridy=compteur++;
        jPanel1.add(new JLabel("Fonction Economique : "),cons);
        cons.gridx=1;
        cons.gridy=compteur++;
        fields.add(new JTextField(15));
        jPanel1.add(fields.get(fields.size()-1),cons);
        cons.gridx=0;
        cons.gridy=compteur++;
        jPanel1.add(new JLabel("Sujet aux contraintes : "),cons);
        /* =============== Initialisation du deuxieme panel =============== */
        
        jPanel2.setLayout(new GridBagLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ajouter contrainte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Valider");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel2);

        jRadioButton1.setText("Pivot automatique");

        jRadioButton2.setText("Pivot manuel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton2))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jButton2.getAccessibleContext().setAccessibleParent(jScrollPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       
        cons.fill = GridBagConstraints.VERTICAL;
        cons.gridx=1;
        cons.gridy=compteur++;
        
        fields.add(new JTextField(15));
        jPanel1.add(fields.lastElement(),cons);
        
        cons.gridx=2;
        JButton btn = new JButton("X");
        btn.addMouseListener(this);
        btn_fields.add(btn);
        jPanel1.add(btn_fields.lastElement(),cons);
        
        jPanel1.setVisible(false);
        jPanel1.setVisible(true);
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        TableIT tableit;
        initier_table();
        if(jRadioButton1.isSelected())
        {
        /* ==================== Traitement cas automatique ====================== */
            String pivot = Simplexe.trouver_pivot(data);
            while(!pivot.equals("-1,-1") && !pivot.equals("0,0")) 
            {   
                tcon.gridy=tcompteur++;

                if(iteration==0) 
                {
                    jPanel2.add(new JLabel("Tableau initial :"),tcon);

                }
                else{
                    jPanel2.add(new JLabel("Tableau "+iteration+" :"),tcon);
                }
            

                tcon.gridy=tcompteur++;   
                tableit = new TableIT(data,false,true);
                tableit.colorierPivot(pivot);
                tables.add(tableit);
                jPanel2.add(tables.lastElement(),tcon);


               Simplexe.calculer(data,pivot);
               pivot = Simplexe.trouver_pivot(data);
               iteration++;
               }
               tcon.gridy=tcompteur++;
               jPanel2.add(new JLabel("Tableau final :"),tcon);

               for(int i=0;i<data.size()-1;i++)
               {
                   data.get(i).remove(data.get(i).size()-1);
               }

               jPanel2.setVisible(false);
                jPanel2.setVisible(true);
        
               if(pivot.equals("0,0"))
                   JOptionPane.showMessageDialog(this, "Probleme non borné");
               
               tcon.gridy=tcompteur++;
               tableit = new TableIT(data,true,true);
               tables.add(tableit);
               jPanel2.add(tables.lastElement(),tcon);
        }
        else
        {
            /* ============= Traitement cas manuel ================ */
            tableau_manuel_suivant();
        }
        jPanel2.setVisible(false);
        jPanel2.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fenetre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fenetre().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables


    private String afficher_tableau(Vector<Vector<String>> data)
    {
        String stream="\n\n";
        DecimalFormat df = new DecimalFormat("#.##");
        for(int i=0;i<data.size();i++)
        {
            for(int j=0;j<data.get(i).size();j++)
            {
                if(i==0 || j==0)
                    stream+=data.get(i).get(j)+"\t";
                else
                {
                    if(data.get(i).get(j).contains("."))
                        stream+=df.format(Float.parseFloat(data.get(i).get(j)))+"\t";
                    else
                        stream+=data.get(i).get(j)+"\t";
                     
                }
            }
            stream += "\n";
        }
        return stream;
    }

    private void initier_table()
    {
        Vector<String> contraintes = new Vector();
        for(int i=1;i<fields.size();i++)
            contraintes.add(fields.get(i).getText().replaceAll(" ", ""));
        
        jPanel2.removeAll();
        tables = new Vector();
        btn_tables = new Vector();
        data = Simplexe.initialiser(fields.get(0).getText().replaceAll(" ", ""),contraintes);
        iteration=0;
        tcon.gridx=0;
        tcon.gridy=0;
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i=0;i<btn_fields.size();i++)
        {
            if(e.getSource()==btn_fields.get(i))
            {
                fields.get(i+1).setVisible(false);
                btn_fields.get(i).setVisible(false);
                fields.remove(i+1);
                btn_fields.remove(i);   
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<btn_tables.size();i++)
        {
            if(e.getSource()==btn_tables.get(i))
            {
                Simplexe.calculer(data, tables.get(i).getLigne_pivot()+","+tables.get(i).getCol_pivot());
                tableau_manuel_suivant();
                btn_tables.get(i).setEnabled(false);
            }
        }
    }

    public void tableau_manuel_suivant()
    {
        tcon.gridy=tcompteur++;
                
                float max =0;
                for(int i=1;i<data.get(0).size();i++)
                {
                    if(Float.parseFloat(data.lastElement().get(i))>max)
                        max = Float.parseFloat(data.lastElement().get(i));
                }
                if(iteration==0) 
                {
                    jPanel2.add(new JLabel("Tableau initial "+((max==0)?"& final":"") +":"),tcon);

                }
                else if(max==0){
                    jPanel2.add(new JLabel("Tableau final :"),tcon);
                }
                else
                {
                    jPanel2.add(new JLabel("Tableau "+iteration+" :"),tcon);
                }
            
                
                tcon.gridy=tcompteur++;   
                TableIT tableit = new TableIT(data,(max==0),false);
                tables.add(tableit);
                jPanel2.add(tables.lastElement(),tcon);
                if(max!=0)
                {
                    JButton btn = new JButton("Calculer");
                    btn_tables.add(btn);
                    tcon.gridy=tcompteur++;
                    jPanel2.add(btn_tables.lastElement(),tcon);
                    btn_tables.lastElement().addActionListener(this);
                }
                
                iteration++;
                
                jPanel2.setVisible(false);
                jPanel2.setVisible(true);
                
    }



}