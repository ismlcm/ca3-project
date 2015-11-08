/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.User;
import facades.UserFacade;
import facades.XmlReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;
import org.eclipse.persistence.internal.core.helper.CoreClassConstants;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Ismail Cam
 */
public class RunFacade
{

    public static void main( String[] args )
    {
        
        
        
        //x.getCurrencies();
        
//        UserFacade uu = new UserFacade();
//        
//        uu.deleteUser( "ismail" );

//        User u = new User( "ismail2", "ismail2" );
//        u.AddRole( "User" );
//        uu.addUser( u );

        //System.out.println( uu.getUser2( "maz" ).getRoles().get( 0 ) );

        //System.out.println( u.getAll() );
//        UserFacade uf = new UserFacade(Persistence.createEntityManagerFactory("PU"));
//        List roles = new ArrayList();
//        roles.add("User");
//        User u = new User("Test", "Test", roles);
//        uf.addUser( u );
    }
}
