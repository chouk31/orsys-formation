/*
 * LIBRE DE DROIT
 */
package com.occonseils.examen;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author olivier
 */
public class EvaluateurNiveauTest {
    
    
    
    public EvaluateurNiveauTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testDeBase() throws Exception
    {
      String niveau = EvaluateurNiveau.evaluerNiveau("10", "10");
      assertTrue( niveau.equals("D"));
    }
    
    @Test( expected=Exception.class )
    public void testInvalide() throws Exception
    {
      EvaluateurNiveau.evaluerNiveau("AAA", "100");
    }
    
}
