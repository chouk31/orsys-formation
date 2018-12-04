/*
 * LIBRE DE DROIT
 */
package com.occonseils.examen;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author olivier
 */
@RunWith(Parameterized.class)
public class EvaluateurNiveauParametresTest {

    private String cours;
    private String examen;
    private String attendu;

    @Parameters
    public static Collection<Object[]> dataTests() {

        Object[][] data = new Object[][]{{"0", "0", "D"}, 
            {"15", "14", "D"}, {"1", "29", "C"}, {"15", "34", "C"}, 
            {"15", "35", "B"} , {"20", "49", "B"},{"20", "50", "A"} };

        return Arrays.asList(data);
    }

    public EvaluateurNiveauParametresTest(String cours, String examen, String attendu) {
        this.cours = cours;
        this.examen = examen;
        this.attendu = attendu;
    }

    @Test

    public void testAvecParametres() throws Exception {
        String note = EvaluateurNiveau.evaluerNiveau(cours, examen);
        assertEquals(attendu, note);
    }

}
