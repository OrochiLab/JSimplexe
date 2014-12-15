/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;


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
    private static DecimalFormat df = new DecimalFormat("#.##");
    /*==============Composants pour l'apercu graphique ========*/
    private Vector<String> init_contraintes = new Vector();
    private Vector<Vector<Double>> valeurs = new Vector();
    /**
     * Creates new form Fenetre
     */
    public Fenetre() {
        initComponents();
        
        ButtonGroup grp = new ButtonGroup();
        grp.add(jRadioButton1);
        grp.add(jRadioButton2);
        jRadioButton1.setSelected(true);
        
        grp = new ButtonGroup();
        grp.add(min_radio);
        grp.add(max_radio);
        max_radio.setSelected(true);
        
        /* =============== Initialisation du premier panel =============== */
        jPanel1.setLayout(new GridBagLayout());
        cons.insets= new Insets(5, 5, 5, 5);
        cons.gridx=0;
        cons.gridy=compteur++;
        jPanel1.add(new JLabel("Fonction Economique : "),cons);
        
        JPanel min_max_pan = new JPanel();
        min_max_pan.add(min_radio);
        min_max_pan.add(max_radio);
        
        cons.gridx=0;
        cons.gridy=compteur++;
        jPanel1.add(min_max_pan,cons);
        cons.gridx=1;
        fields.add(new JTextField(15));
        jPanel1.add(fields.get(fields.size()-1),cons);
        cons.gridx=0;
        cons.gridy=compteur++;
        jPanel1.add(new JLabel("Sujet aux contraintes : "),cons);
        /* =============== Initialisation du deuxieme panel =============== */
        
        jPanel2.setLayout(new GridBagLayout());
        jButton3.setVisible(false);
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
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simplexe - Morabit Mouad");

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
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel2);

        jRadioButton1.setText("Pivot automatique");

        jRadioButton2.setText("Pivot manuel");

        jCheckBox1.setText("Règle de \"Bland\"");

        jButton3.setText("Aperçu graphique");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jCheckBox1)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

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
            System.out.println("Le probleme est en "+Simplexe.getZTypeCourant()+" "+Simplexe.getPhase());
            String pivot = Simplexe.trouver_pivot(data,jCheckBox1.isSelected());
            while(!pivot.equals("-1,-1") && !pivot.equals("0,0")) 
            {   
                tcon.gridy=tcompteur++;
                
                if(iteration==0) 
                {
                    jPanel2.add(new JLabel((Simplexe.getPhase().equals("phase1")?"Phase 1 - ":"")+"Tableau initial :"),tcon);

                }
                else{
                    jPanel2.add(new JLabel((Simplexe.getPhase().equals("phase1")?"Phase 1 - ":"")+"Tableau "+iteration+" :"),tcon);
                }
            

                tcon.gridy=tcompteur++;   
                tableit = new TableIT(data,false,true);
                tableit.colorierPivot(pivot);
                tables.add(tableit);
                jPanel2.add(tables.lastElement(),tcon);


               Simplexe.calculer(data,pivot);
               pivot = Simplexe.trouver_pivot(data,jCheckBox1.isSelected());
               iteration++;
            }
               tcon.gridy=tcompteur++;
               jPanel2.add(new JLabel((Simplexe.getPhase().equals("phase1")?"Phase 1 - ":"")+"Tableau final :"),tcon);

               for(int i=0;i<data.size()-1;i++)
               {
                   data.get(i).remove(data.get(i).size()-1);
               }

               
               tcon.gridy=tcompteur++;
               tableit = new TableIT(data,true,true);
               tables.add(tableit);
               jPanel2.add(tables.lastElement(),tcon);
               
               //==================================
               
               if(Simplexe.getPhase().equals("phase1") && Float.parseFloat(data.lastElement().lastElement())==0)
               {
                   iteration=0;
                   Simplexe.initialiser_phase2(data);
                   tcon.gridy=tcompteur++;
                   jPanel2.add(new JLabel("Début de la phase 2 : "),tcon);

                   pivot = Simplexe.trouver_pivot(data,jCheckBox1.isSelected());
            
                   
                while(!pivot.equals("-1,-1") && !pivot.equals("0,0")) 
                {   
                    tcon.gridy=tcompteur++;

                    if(iteration==0) 
                    {
                        jPanel2.add(new JLabel("Phase 2 - "+"Tableau initial :"),tcon);

                    }
                    else{
                        jPanel2.add(new JLabel("Phase 2 - "+"Tableau "+iteration+" :"),tcon);
                    }


                    tcon.gridy=tcompteur++;   
                    tableit = new TableIT(data,false,true);
                    tableit.colorierPivot(pivot);
                    tables.add(tableit);
                    jPanel2.add(tables.lastElement(),tcon);


                   Simplexe.calculer(data,pivot);
                   pivot = Simplexe.trouver_pivot(data,jCheckBox1.isSelected());
                   iteration++;
                }
               tcon.gridy=tcompteur++;
               jPanel2.add(new JLabel((Simplexe.getPhase().equals("phase1")?"Phase 1 - ":"")+"Tableau final :"),tcon);

               if(data.get(0).lastElement().equals("Ratio"))
               {
                for(int i=0;i<data.size()-1;i++)
                {
                    data.get(i).remove(data.get(i).size()-1);
                }
               }

               
               tcon.gridy=tcompteur++;
               tableit = new TableIT(data,true,true);
               tables.add(tableit);
               jPanel2.add(tables.lastElement(),tcon);
               
               }else if(Simplexe.getPhase().equals("phase1") && Float.parseFloat(data.lastElement().lastElement())!=0)
               {
                   tcon.gridy=tcompteur++;
                    jPanel2.add(new JLabel("Le problème n'a pas de solution !!!"),tcon);
               }
               
               //==================================
                if(pivot.equals("0,0"))
                {
                 tcon.gridy=tcompteur++;
                 jPanel2.add(new JLabel("Problème non borné !!!"),tcon);    
                }
               
                jPanel2.setVisible(false);
                jPanel2.setVisible(true);
        
        }
        else
        {
            /* ============= Traitement cas manuel ================ */
            tableau_manuel_suivant();
        }
        jPanel2.setVisible(false);
        jPanel2.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new GeomTest(init_contraintes, valeurs);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    //de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel
                    //com.alee.laf.WebLookAndFeel
                    
                    UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel");
                     new Fenetre().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
    private JRadioButton min_radio = new JRadioButton("Min");
    private JRadioButton max_radio = new JRadioButton("Max");
    

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
        if(min_radio.isSelected()) 
        {
            Simplexe.setZType("min");
            Simplexe.setZTypeCourant("min");
        }
        else
        {
            Simplexe.setZType("max");
            Simplexe.setZTypeCourant("max");
        
        }
        data = Simplexe.initialiser(fields.get(0).getText().replaceAll(" ", ""),contraintes);
        
        iteration=0;
        tcon.gridx=0;
        tcon.gridy=0;
        
        int count=0;
        for(int i=1;i<data.firstElement().size()-1;i++)
        {
            if(!data.firstElement().get(i).matches("^e[0-9]{0,}"))
                count++;
        }
        System.out.println("Nombre de variables : "+count);
        if(count==2)
        {
            jButton3.setVisible(true);
            
            
            Vector<Double> tmp;
            for(int i=1;i<fields.size();i++)
                init_contraintes.add(fields.get(i).getText().replaceAll(" ", ""));
        
            for(int i=1;i<data.size()-1;i++)
            {
                tmp = new Vector();
                for(int j=1;j<count+1;j++)
                {
                    System.out.print(data.get(i).get(j)+" ");
                    tmp.add(Double.parseDouble(data.get(i).get(j)));

                }
                tmp.add(Double.parseDouble(data.get(i).lastElement()));
                valeurs.add(tmp);
                System.out.println(data.get(i).lastElement());
            }
            
        }
        else
            jButton3.setVisible(false);
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
