
package test;

import entity.User;
import facades.UserFacade;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lenovo
 */
public class FacadeTest {
    UserFacade uf = new UserFacade();
    
    public FacadeTest() {
        
        
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
     @Test
     public void addUser() {
         User user = new User();
         user.setUserName("FacadeTest");
         user.setPassword("FacadeTest");
         uf.addUser(user);
         User user2 = new User();
         user2 = uf.getUser2(user.getUserName());
         assertEquals(user2,user);
     }
     
     @Test
     public void deleteUser() {
         uf.deleteUser("FacadeTest");
         
         User user = uf.getUser2("FacadeTest");
         assertEquals(user, null);
     }
}
