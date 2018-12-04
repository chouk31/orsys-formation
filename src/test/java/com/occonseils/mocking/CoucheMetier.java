/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occonseils.mocking;

/**
 *
 * @author olivier
 */
public class CoucheMetier {
    
    private CouchePersistance cp = null;
    public CoucheMetier( CouchePersistance cp )
    {
        this.cp = cp;
    }
    
    public void operationMetierComplexeAvec3CreationsAttendues( Object personne )
    {
        Object[] personnes = new Object[2];
        // ..... CODE COMPLEXE
         cp.creer( personnes[0]);
        // ..... CODE COMPLEXE 
         cp.creer( personne );
        // ..... CODE COMPLEXE
         cp.creer( personnes[1]);
        // ..... CODE COMPLEXE
    }
    
    public Object lirePersonne( long id )
    {
        return cp.lire( id );
    }
}
