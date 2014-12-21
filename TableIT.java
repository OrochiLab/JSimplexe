/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Yunho
 */
public class TableIT extends javax.swing.JPanel implements MouseListener{

    /**
     * Creates new form TableIT
     */
    private Vector<Vector<String>> data= new Vector();
    private Vector<Vector<JLabel>> labels= new Vector();
    private GridBagConstraints con=new GridBagConstraints();
    private DecimalFormat df = new DecimalFormat("#.##");
    private int ligne_pivot=0;
    private int col_pivot=0;
    private boolean finale=false;
    private boolean automatique=false;
    
    public TableIT(Vector<Vector<String>> data,boolean finale,boolean automatique) {
        initComponents();
        
        Vector<String> ligne;
        for(int i=0;i<data.size();i++)
        {
            ligne = new Vector();
            for(int j=0;j<data.get(i).size();j++)
            {
                ligne.add(data.get(i).get(j));
            }
            this.data.add(ligne);
        }
        
        this.setLayout(new GridBagLayout());
        this.finale=finale;
        this.automatique=automatique;
        drawTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void drawTable()
    {
        Vector<JLabel> row ;
        JLabel cellule;
        con.insets=new Insets(10, 5, 5, 10);
        
        
        for(int i=0;i<data.size();i++)
        {
            row = new Vector();
            for(int j=0;j<data.get(i).size();j++)
            {
                if(data.get(i).get(j).contains(".") && i!=0 && j!=0)
                    cellule = new JLabel(df.format(Float.parseFloat(data.get(i).get(j))));
                else
                    cellule = new JLabel(data.get(i).get(j)+"\t");
                
                
                if(!this.finale && !this.automatique && i!=0 && j!=0 && (i!=data.size()-1) && (j!=data.get(0).size()-1) && j!=(data.get(0).lastElement().equals("Ratio")?data.get(0).size()-2:data.get(0).size()-1))
                    cellule.addMouseListener(this);
                
                
                row.add(cellule);
                con.gridx=j;
                con.gridy=i;
                this.add(row.get(row.size()-1),con);
            }
            labels.add(row);
        }
        
        int max = 0;
        
        // ============= Gerer la largeur des colonnes ================= //
        for(int i=0;i<labels.size();i++)
        {
            for(int j=0;j<labels.get(i).size();j++)
            {
                if(labels.get(i).get(j).getPreferredSize().width>max)
                {
                    max = labels.get(i).get(j).getPreferredSize().width;
                }
            }
        }
        // =============== Regler la largeur et modifier les couleurs ======= //
        for(int i=0;i<labels.size();i++)
        {
            for(int j=0;j<labels.get(i).size();j++)
            {
                labels.get(i).get(j).setPreferredSize(new Dimension(max+2,20));
                labels.get(i).get(j).setHorizontalAlignment(SwingConstants.CENTER);
                labels.get(i).get(j).setOpaque(true);
                if(i==0 || j==0)
                    labels.get(i).get(j).setBackground(Color.lightGray);
                labels.get(i).get(j).setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
            }
        }
    }

    public void colorierPivot(String pivot)
    {
        ligne_pivot = Integer.parseInt(pivot.split(",")[0]);
        col_pivot = Integer.parseInt(pivot.split(",")[1]);
        
                for(int i=0;i<labels.size();i++)
                {
                    for(int j=0;j<labels.get(i).size();j++)
                    {
                        if(i!=0 && j!=0 && (i!=data.size()-1) && (j!=data.get(0).size()-1) && j!=(data.get(0).lastElement().equals("Ratio")?data.get(0).size()-2:data.get(0).size()-1))
                        {
                            labels.get(i).get(j).setOpaque(true);
                            labels.get(i).get(j).setBackground(null);
                        }
                    }
                }
                labels.get(ligne_pivot).get(col_pivot).setOpaque(true);
                labels.get(ligne_pivot).get(col_pivot).setBackground(Color.green);
   
    }


    @Override
    public void mouseClicked(MouseEvent e) {
       
        boolean b=false;
        for(int i=1;i<labels.size()-1;i++)
        {
            for(int j=1;j<labels.get(i).size()-1;j++)
            {
                if(e.getSource()==labels.get(i).get(j) && (i!=ligne_pivot || j!=col_pivot))
                {  
                    
                    if(Float.parseFloat(data.get(i).get(j))>0)
                    {
                        
                        if(Float.parseFloat(data.get(i).get(j))==1)
                        {
                            
                            for(int k=1;k<data.size();k++)
                            {
                                if(k!=i && Float.parseFloat(data.get(k).get(j))!=0)
                                {
                                    b=true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            b=true;
                        }
                        
                        if(b)
                        {
                            String msg;
                            if(ligne_pivot==0 && col_pivot==0) msg ="Etes vous sur de vouloir choisir cette cellule comme pivot ?";
                            else msg="Souhaitez vous changer de pivot et choisir cette cellule comme nouveau pivot ?";
                            
                            if(JOptionPane.showConfirmDialog(this, msg)==JOptionPane.OK_OPTION)
                            {
                            colorierPivot(i+","+j);
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(this, "Vous ne pouvez pas choisir ce pivot");
                        }
                    }else
                    {
                        JOptionPane.showMessageDialog(this, "Vous ne pouvez pas choisir un pivot nul ou négatif");
                    }
                }
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

    public int getLigne_pivot() {
        return ligne_pivot;
    }

    public int getCol_pivot() {
        return col_pivot;
    }
    
    public Vector<Vector<String>> getData()
    {
        return data;
    }

    
}
