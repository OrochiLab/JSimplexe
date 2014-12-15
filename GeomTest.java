/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;
import java.awt.Color;
import java.awt.FlowLayout;
import static java.lang.System.out;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import math.geom2d.Box2D;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.line.StraightLine2D;
import org.math.plot.Plot2DPanel;

/**
 *
 * @author Yunho
 */
public class GeomTest extends javax.swing.JFrame {

    /**
     * Creates new form GeomTest
     */
    private Vector<String> equations=new Vector();
    private Vector<Vector<Double>> valeurs = new Vector();
    public GeomTest(Vector<String> equations,Vector<Vector<Double>> valeurs) {
        this.equations=equations;
        this.valeurs =valeurs;
        initComponents();
        this.draw();      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Aperçu graphique");
        setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GeomTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeomTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeomTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeomTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    private void draw()
    {
        
                Plot2DPanel plot = new Plot2DPanel();
                Color[] couleurs = new Color[]{Color.red,Color.blue,Color.ORANGE,Color.green,Color.yellow};
                plot.removePlotToolBar();
                plot.addLegend("SOUTH");
                
                plot.getAxis(0).setLabelText("X");
                plot.getAxis(1).setLabelText("Y");
                Map droites = new HashMap();
                /*Vector<String> equations=new Vector();
                equations.add("x1+x2=150");
                equations.add("4x1+2x2=440");
                equations.add("x1+4x2=480");
                equations.add("x1=90");
                
                Vector<Vector<Double>> valeurs = new Vector();
                Vector<Double> val_eq = new Vector();
                val_eq.add(1d);
                val_eq.add(1d);
                val_eq.add(150d);
                valeurs.add(val_eq);
                
                val_eq = new Vector();
                val_eq.add(4d);
                val_eq.add(2d);
                val_eq.add(440d);
                valeurs.add(val_eq);
                
                val_eq = new Vector();
                val_eq.add(1d);
                val_eq.add(4d);
                val_eq.add(480d);
                valeurs.add(val_eq);
                
                val_eq = new Vector();
                val_eq.add(1d);
                val_eq.add(0d);
                val_eq.add(90d);
                valeurs.add(val_eq);*/
                
                
                plot.setFixedBounds(0, 0,200);
                plot.setFixedBounds(1, 0,200);
                double max =0;
                for(int i=0;i<equations.size();i++)
                {
                    if(valeurs.get(i).get(0)==0)
                    {
                        droites.put(equations.get(i),new double[][]{
                        {plot.plotCanvas.base.getMinBounds()[0],(valeurs.get(i).get(2)!=0)?(double)valeurs.get(i).get(2):0},
                        {plot.plotCanvas.base.getMaxBounds()[0],(double)valeurs.get(i).get(2)}
                        
                        });
                    }else if(valeurs.get(i).get(0)==0)
                    {
                        droites.put(equations.get(i),new double[][]{
                        {(valeurs.get(i).get(2)!=0)?(double)valeurs.get(i).get(2):0,0},
                        {(double)valeurs.get(i).get(2),plot.plotCanvas.base.getMaxBounds()[0]}
                        
                        });
                    }
                    else
                    {
                        droites.put(equations.get(i),new double[][]{
                            /*{0,(valeurs.get(0).get(2)!=0)?(double)valeurs.get(i).get(2)/valeurs.get(i).get(1):0},
                            {(valeurs.get(0).get(2)!=0)?(double)valeurs.get(i).get(2)/valeurs.get(i).get(0):0,0}*/
                            {(valeurs.get(i).get(2)!=0)?(double)valeurs.get(i).get(2)/valeurs.get(i).get(0):0,plot.plotCanvas.base.getMinBounds()[1]},
                            {(valeurs.get(i).get(2)!=0)?(double)(valeurs.get(i).get(2)-(plot.plotCanvas.base.getMaxBounds()[1]*valeurs.get(i).get(1)))/valeurs.get(i).get(0):0,plot.plotCanvas.base.getMaxBounds()[1]}

                        });
                    }
                    out.println("Equation : "+equations.get(i)+" coordonnées : "+((double[][])droites.get(equations.get(i)))[0][0]+","+((double[][])droites.get(equations.get(i)))[0][1]+" "+((double[][])droites.get(equations.get(i)))[1][0]+","+((double[][])droites.get(equations.get(i)))[1][1]);
                    plot.addLinePlot(equations.get(i),couleurs[i],((double[][])droites.get(equations.get(i)))[0],((double[][])droites.get(equations.get(i)))[1]);
                    max = Math.max(max,Math.max(Math.max(((double[][])droites.get(equations.get(i)))[0][0],((double[][])droites.get(equations.get(i)))[0][1]),Math.max(((double[][])droites.get(equations.get(i)))[1][0],((double[][])droites.get(equations.get(i)))[1][1])));
                   
                }
                    
                    out.println("Le plus grand nombre : "+max);
               
                plot.setFixedBounds(0, 0,200);
                plot.setFixedBounds(1, 0,200);
                //plot.setAutoBounds();
                
                this.setContentPane(plot);
                this.setVisible(true);
    }


}
