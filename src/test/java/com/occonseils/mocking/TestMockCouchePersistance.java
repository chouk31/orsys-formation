/*
 * LIBRE DE DROIT
 */
package com.occonseils.mocking;

import com.occonseils.examen.EvaluateurNiveau;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import com.occonseils.mocking.CouchePersistance;
import com.occonseils.mocking.CoucheMetier;

/**
 *
 * @author Olivier CHARLES
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMockCouchePersistance {
    
    @Mock
    CouchePersistance mockCouchePersistance;
   
    
    @Test
    public void testMockCouchePersistance()
    {
        CoucheMetier cm = new CoucheMetier( mockCouchePersistance );
        Object personneImportante = new Object();
        cm.operationMetierComplexeAvec3CreationsAttendues( personneImportante );
        
        // On vérifie des appels indirects
        // verifier, debuger, concevoir et accélerer les tests ...
        
        verify( mockCouchePersistance , times(3)).creer(any());
        verify( mockCouchePersistance , times(1)).creer(personneImportante);
        
    }
    
    
}
