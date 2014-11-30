/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Yunho
 */
public class Simplexe {

    /**
     * @param args the command line arguments
     */
    private static int compteur =0;
    private static final String REGEX_LEADING_DIGITS = "^[0-9]{0,}([\\./][0-9]{0,}){0,1}";
    private static Vector<String> contraintes=new Vector();
    private static Vector<Vector<String>> data;
    private static Vector<Integer> b = new Vector();
    private static DecimalFormat df = new DecimalFormat("#.##");
    private static String z_type="max"; // probleme de min ou de max ?
    private static String z_type_courant="max"; // changement du type du problème du a la possibilité de passer a la phase1/2
    
    // cas de deux phases
    private static String ecart_phase1=""; //les variables d ecart a ne pas afficher en base
    private static String phase="normale"; // On est dans quel phase ?
    private static Vector<Integer> indice_artificielles = new Vector(); // indice des contraintes avec variables artificielles
    private static Vector<String> z_initial;
    public static void main(String[] args) {
        
        String z="1000x1+2000x2";
        
        
        contraintes.add("x1+x2<=150");
        contraintes.add("4x1+2x2<=440");
        contraintes.add("x1+4x2>=480");
        contraintes.add("x1=90");
        
        data = initialiser(z,contraintes);
        //String pivot = trouver_pivot(data);
        
        //System.out.println("Tableau Initial");
        //while(!pivot.equals("-1,-1")) 
        //{
        afficher_tableau(data);
        //calculer(data,pivot);
        //pivot = trouver_pivot(data);
        //}
        //System.out.println("Tableau final : ");
        //for(int i=0;i<data.size()-1;i++)
        //{
        //    data.get(i).remove(data.get(i).size()-1);
        //}
       //afficher_tableau(data);
        
        
        System.out.println();
        afficher_tableau(data);
        
    }
    public static Vector<Vector<String>> initialiser(String z,Vector<String> contraintes) {
        
        
        
        Vector<String> variables = new Vector();
        data = new Vector();
        compteur=0;
        phase="normale";
        ecart_phase1="";
        indice_artificielles = new Vector();
        b = new Vector();
        Simplexe.contraintes=contraintes;
        
        for(int i=0;i<contraintes.size();i++)
        {
            contraintes.set(i,equilibrer(contraintes.get(i))); // Equilibrer la contrainte remplacer les < > <= >= avec = et ajout de variables d ecarts
            b.add(Integer.parseInt(contraintes.get(i).split("=")[1])); // Enregistrement de la colonne b
            contraintes.set(i, contraintes.get(i).split("=")[0]); // Enlever partie droite de =
            if(!contraintes.get(i).startsWith("+") && !contraintes.get(i).startsWith("-")) //Ajouter un + au début s'il n y a pas de signe
                contraintes.set(i, "+"+contraintes.get(i));
        }
        
       
        String t2[] = z.split("\\+|-");
                for(int j=0;j<t2.length;j++)
                {
                if(!variables.contains(t2[j].replaceAll(REGEX_LEADING_DIGITS,"")) && !t2[j].replaceAll(REGEX_LEADING_DIGITS,"").isEmpty())
                    variables.add(t2[j].replaceAll(REGEX_LEADING_DIGITS,""));
                }
        
        for(int i=0;i<contraintes.size();i++)
        {
            String t[] = contraintes.get(i).split("\\+|-");
                for(int j=0;j<t.length;j++)
                {
                if(!variables.contains(t[j].replaceAll(REGEX_LEADING_DIGITS,"")) && !t[j].replaceAll(REGEX_LEADING_DIGITS,"").isEmpty())
                    variables.add(t[j].replaceAll(REGEX_LEADING_DIGITS,""));
                }
        }
        
        System.out.println("Liste des variables : ");
        Vector<String>vb=new Vector();
        vb.add("V.B");
        for(int i=0;i<variables.size();i++)
        {
            System.out.print(variables.get(i)+", ");
            vb.add(variables.get(i));
        }
        vb.add("b");
        data.add(vb);
        
        
        
        
        System.out.println("\nContraintes v2 : ");
        
        for(int i=0;i<variables.size();i++)
        {   
                
                z=z.replace(variables.get(i),variables.get(i)+"#");
        }
        System.out.println("Fonction Z "+z);
        
        
        
        for(int i=0;i<contraintes.size();i++)
        {
            for(int j=0;j<variables.size();j++)
            {   
                
                contraintes.set(i,contraintes.get(i).replace(variables.get(j),variables.get(j)+"#"));
            }
            System.out.println(contraintes.get(i));
        }
        
        
        
        
        
        
        
        Vector<String> ligne=new Vector();
        for(int i=0;i<variables.size();i++)
        {
            if(z.contains(variables.get(i)) || ecart_phase1.contains(variables.get(i)))
                continue;
            else
            {
                System.out.println("========== Contrainte ! ===========");
                for(int j=0;j<contraintes.size();j++)
                {   
                    if(contraintes.get(j).contains(variables.get(i)))
                    {
                        ligne = new Vector();
                        ligne.add(variables.get(i));
                        System.out.println("La contrainte est : "+contraintes.get(j));
                        String[] parties=contraintes.get(j).split("#");
                        for(int k=0;k<variables.size();k++)
                        {
                            if(contraintes.get(j).contains(variables.get(k)))
                            {
                                //System.out.println("La contrainte "+j+" contient la variable "+variables.get(k));
                                for(int l=0;l<parties.length;l++)
                                {
                                    if(parties[l].contains(variables.get(k)))
                                    {
                                        //System.out.println("Détails : "+parties[l].replace(variables.get(k), "").replace("+", "")+" ,taille :"+parties[l].replace(variables.get(k), "").replace("+", "").length());
                                        if(parties[l].replace(variables.get(k), "").replace("+", "").length()==0)
                                            ligne.add("1");
                                        else if(parties[l].replace(variables.get(k), "").replace("+", "").contains("-") && parties[l].replace(variables.get(k), "").replace("+", "").length()==1)
                                            ligne.add("-1");
                                        else
                                            ligne.add(parties[l].replace(variables.get(k), "").replace("+", ""));
                                    
                                    }
                                }
                            
                            }
                            else
                            {
                                //System.out.println("La contrainte "+j+" ne contient pas la variable "+variables.get(k));
                                ligne.add("0");
                            }
                        }
                        ligne.add(b.get(j)+"");
                        break;
                    }
                }
                data.add(ligne);
            }
        }
        
        
        ligne = new Vector();
        ligne.add("C.j");
        String[] parties=z.split("#");
                        for(int k=0;k<variables.size();k++)
                        {
                            if(z.contains(variables.get(k)))
                            {
                                //System.out.println("La contrainte "+j+" contient la variable "+variables.get(k));
                                for(int l=0;l<parties.length;l++)
                                {
                                    if(parties[l].contains(variables.get(k)))
                                    {
                                        //System.out.println("Détails : "+parties[l].replace(variables.get(k), "").replace("+", "")+" ,taille :"+parties[l].replace(variables.get(k), "").replace("+", "").length());
                                        if(parties[l].replace(variables.get(k), "").replace("+", "").length()==0)
                                            ligne.add("1");
                                        else if(parties[l].replace(variables.get(k), "").replace("+", "").contains("-") && parties[l].replace(variables.get(k), "").replace("+", "").length()==1)
                                            ligne.add("-1");
                                        else
                                            ligne.add(parties[l].replace(variables.get(k), "").replace("+", ""));
                                    
                                    }
                                }
                            
                            }
                            else
                            {
                                //System.out.println("La contrainte "+j+" ne contient pas la variable "+variables.get(k));
                                ligne.add("0");
                            }
                        }
                        ligne.add("0");
                        data.add(ligne);
                        
                        
                        
        // cas de la phase1, calcul de la somme des variables artificielles
        if(phase.equals("phase1"))
        {
            z_type_courant="min";
            afficher_tableau(data);
            for(int i=0;i<indice_artificielles.size();i++)
            {
                        System.out.println("Indice "+indice_artificielles.get(i));
            }
            z_initial = (Vector<String>) data.lastElement().clone();

            for(int i=1;i<((data.get(0).lastElement().equals("Ratio"))?data.get(0).size()-1:data.get(0).size());i++)
            {
                if(!data.get(0).get(i).matches("^a[0-9]+$"))
                {
                    float somme=0;
                    for(int j=0;j<indice_artificielles.size();j++)
                    {
                        somme += Float.parseFloat(data.get(indice_artificielles.get(j)+1).get(i));
                    }
                    data.lastElement().set(i, ((somme!=0)?somme*-1:somme)+"");

                }
            }
            
        }
        
        
        return data;

    
    }
    
    public static void initialiser_phase2(Vector<Vector<String>> data)
    {
       data.set(data.size()-1, z_initial); 
       z_type_courant = z_type;
       System.out.println("Probleme de base : "+z_type_courant);
       
       for(int i=1;i<data.size()-1;i++)
       {
           for(int j=1;j<data.lastElement().size()-1;j++)
           {
               if(data.get(i).get(0).equals(data.get(0).get(j)))
               {
                   Simplexe.calculer(data, i+","+j);
               }
           }
       }
       
       boolean stop =false;
       while(!stop)
       {
        for(int i=0;i<data.lastElement().size();i++)
        {

            if(data.get(0).get(i).matches("^a[0-9]+$"))
            {
                System.out.println("Colonne "+i+" a supprimer");
                for(int j=0;j<data.size();j++)
                {
                    data.get(j).remove(i);
                }
                break;
            }
            if(i==data.lastElement().size()-1)
                stop=true;
        }
       }
       
    }
    
    public static String equilibrer(String contrainte)
    {
        
        if(contrainte.contains("<") || contrainte.contains("<="))
        {
            compteur++;
            return contrainte.replace("<","+e"+compteur+"=").replace("==", "=");
            
        }
        else if(contrainte.contains(">") || contrainte.contains(">="))
        {
            compteur++;
            if(Integer.parseInt(contrainte.split("=")[1])!=0)
            {
                ecart_phase1+="e"+compteur+",";
                phase ="phase1";
                indice_artificielles.add(contraintes.indexOf(contrainte));
                return contrainte.replace(">","-e"+compteur+"+a"+compteur+"=").replace("==", "=");
            }
            else
                return contrainte.replace(">","-e"+compteur+"=").replace("==","=");
        }
        else if(contrainte.contains("="))
        {
            compteur++;
            phase ="phase1";
            indice_artificielles.add(contraintes.indexOf(contrainte));
            return contrainte.replace("=","+a"+compteur+"=");
        }
        else
        {
            System.out.println("Aucun cas");
            return contrainte;
        }
        
        
        
    }
    
    public static void afficher_tableau(Vector<Vector<String>> data)
    {
        for(int i=0;i<data.size();i++)
        {
            for(int j=0;j<data.get(i).size();j++)
            {
                if(i==0 || j==0)
                    System.out.print(data.get(i).get(j)+"\t");
                else
                {
                    if(data.get(i).get(j).contains("."))
                        System.out.print(df.format(Float.parseFloat(data.get(i).get(j)))+"\t");
                    else
                        System.out.print(data.get(i).get(j)+"\t");
                     
                }
                    
            }
            System.out.println();
        }
    }
    
    
    
    public static String trouver_pivot(Vector<Vector<String>> data)
    {
        /* ======== Recherche de la plus grande valeur = la variante entrante = la colonne du pivot ======== */
        Vector<String> z = data.get(data.size()-1);
        float max=0;
        float min=0;
        int VE=1;
        int VS=1;
        for(int i=1;i<z.size()-1;i++)
        {
            if(z_type_courant.equals("max"))
            {
                if(Float.parseFloat(z.get(i))>max)
                {
                    max=Float.parseFloat(z.get(i));
                    VE=i;
                }
            }
            else
            {
                if(Float.parseFloat(z.get(i))<min)
                {
                    min=Float.parseFloat(z.get(i));
                    VE=i;
                }
            }
        }
        if(max==0 && min==0) return "-1,-1";
        
        /* =========== Calcul du tableau de ratio = colonne b / colonne du pivot ======== */
        //boolean non_borne=true;
        Vector<Float>ratio=new Vector();
        int col_b=0;
        if(data.get(0).get(data.get(0).size()-1).equals("Ratio"))
            col_b=data.get(0).size()-2;
        else
            col_b=data.get(0).size()-1;
        
        for(int i=1;i<data.size()-1;i++)
        {
            if(Float.parseFloat(data.get(i).get(VE))>0)
            {
                ratio.add(Float.parseFloat(data.get(i).get(col_b))/Float.parseFloat(data.get(i).get(VE)));
                //non_borne=false;
            }
            else
                ratio.add(0f);
            
            
        }
        
        /* ==== Vérifier si la colonne ratio existe deja dans la matrice */
        boolean ajout_ratio=false;
        if(col_b==data.get(0).size()-1)
        {
            data.get(0).add("Ratio");
            ajout_ratio=true;
        }
        
        
        min=-1;
        for(int i=0;i<ratio.size();i++)
        {
            if(min==-1 && ratio.get(i)!=0)
            {
                min = ratio.get(i);
                VS=i+1;
            }
            else
            {
                if(ratio.get(i)<min && ratio.get(i)!=0)
                {
                    min = ratio.get(i);
                    VS=i+1;
                }
                
            }
            
            //System.out.print(ratio.get(i)+" ");
            if(ajout_ratio) // Ajouter la cellule ratio si on vient d'ajouter la colonne ratio
                data.get(i+1).add(ratio.get(i)+"");
            else // La modifier sinon
                data.get(i+1).set(data.get(0).size()-1 ,ratio.get(i)+"");
        }
        
        
        
        System.out.println("Maximum : "+max+" Variable entrante : "+data.get(0).get(VE));
        System.out.println("Minimum du ratio : "+Float.parseFloat(df.format(min).replace(",", ".")) +" Variante sortante : "+data.get(VS).get(0));
        
        if(max!=0 && min==-1)
            return "0,0";
        if(min==-1) 
            return "-1,-1";
        else
            return VS+","+VE;
    }
    
    
    public static void calculer(Vector<Vector<String>> data,String pivot)
    {
        int ligne_pivot = Integer.parseInt(pivot.split(",")[0]);
        int col_pivot = Integer.parseInt(pivot.split(",")[1]);
        
        
        /* ===================== Copie de la matrice ================= */
        Vector<Vector<String>> copie = new Vector();
        Vector<String> ligne;
        for(int i=0;i<data.size();i++)
        {
            ligne = new Vector();
            for(int j=0;j<data.get(i).size();j++)
            {
                ligne.add(data.get(i).get(j));
            }
            copie.add(ligne);
        }
        
        /* =============== Affichage de la variable entrante =============== */
        data.get(ligne_pivot).set(0, data.get(0).get(col_pivot));
        
        /* =============== Calcul du nombre de colonnes a parcourir ( sans prendre en compte la colonne ratio )=====*/
        int nbre_col = data.get(0).size();
        if(data.get(0).get(data.get(0).size()-1).equals("Ratio"))
            nbre_col--;
        
        /* ========= Calcul de la ligne du pivot, toutes les valeurs divisés par le pivot==========*/
        for(int i=1;i<nbre_col;i++)
        {
            data.get(ligne_pivot).set(i, (Float.parseFloat(copie.get(ligne_pivot).get(i))/Float.parseFloat(copie.get(ligne_pivot).get(col_pivot)))+"");
        }
        
        /* ============== Calculer le reste de la matrice en sautant la ligne du pivot deja calculée*/
        for(int i=1;i<copie.size();i++)
        {
            for(int j=1;j<nbre_col;j++)
            {
                if(i==ligne_pivot)
                    continue;
                else
                {
                    data.get(i).set(j,""+(Float.parseFloat(copie.get(i).get(j))-Float.parseFloat(copie.get(i).get(col_pivot))*Float.parseFloat(copie.get(ligne_pivot).get(j))/Float.parseFloat(copie.get(ligne_pivot).get(col_pivot))));
                }
            }
        }
       
    }
    
    public static void setZType(String type)
    {
        Simplexe.z_type=type;
    }
    
    public static void setZTypeCourant(String type)
    {
        Simplexe.z_type_courant=type;
    }
    
    public static String getZTypeCourant()
    {
        return Simplexe.z_type_courant;
    }
    
    
    public static String getPhase()
    {
        return Simplexe.phase;
    }
    
    
}
