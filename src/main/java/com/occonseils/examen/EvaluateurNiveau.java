  /*
 * LIBRE DE DROIT
 */
package com.occonseils.examen;

/**
 *
 * @author olivier charles
 */
public class EvaluateurNiveau {
    
    private static final int MIN_COURS   = 0;
    private static final int MIN_EXAMEN  = 0;
    private static final int MAX_COURS   = 25;
    private static final int MAX_EXAMEN  = 75;
    
    private static final int MIN = 0;
    private static final int LIMITE_DC = 30;
    private static final int LIMITE_CB = 50;
    private static final int LIMITE_BA = 70;
    private static final int MAX = 100;
        
    public static boolean isInRange( int value, int min, int max )
    {
        return( value >= (min + 0)  && value <= max );
    }
    
    static String  niveau( int  somme )
    {   
        String niv = "";
        if (   isInRange(  somme ,   MIN ,   LIMITE_DC-1 )) {  niv = "D"; } 
        else if (   isInRange(  somme , LIMITE_DC ,   LIMITE_CB-1 )) { niv="C";}
        else if (   isInRange(  somme , LIMITE_CB ,   LIMITE_BA-1 )) {niv="B";}
        else if (   isInRange(  somme , LIMITE_BA ,   MAX )) {  niv="A"; }
        return  niv;
    }
    
    
    public static boolean isInt( String sval )
    {
        try { Integer.valueOf( sval);}
        catch( Exception e ) { return false;}
        return true;
    }
    
    public static String evaluerNiveau(  String str_cours,  String str_examen ) 
            throws Exception
    {
         String niveau = ""; 
         String message = "Format Cours ["+  MIN_COURS+".."+  MAX_COURS+
                   "] Examen ["+  MIN_EXAMEN+".."+  MAX_EXAMEN+"]";
        if (   isInt( str_cours) && isInt( str_examen ))
        {
            int cours = Integer.valueOf( str_cours );
            int examen = Integer.valueOf( str_examen );
            niveau = "";
            if ( isInRange( cours, MIN_COURS, MAX_COURS )
              && isInRange( examen, MIN_EXAMEN, MAX_EXAMEN ))
            {
                 niveau =   niveau(  cours +  examen  );
            }
            else {
                throw new Exception( "Valeur(s) hors bornes. " + message );
            }
        }
        else
        {
            throw new Exception("Mauvais format, entiers attendus. "+ message ); 
        }
        return  niveau;
    }
}
