package facades;

import entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserFacade
{

   // private final Map<String, User> users = new HashMap<>();
    private EntityManagerFactory emf;

    public UserFacade()
    {
        EntityManagerFactory e = Persistence.createEntityManagerFactory( "PU" );
        emf = e;
//        //Test Users
//        User user = new User( "user", "test" );
//        user.AddRole( "User" );
//        users.put( user.getUserName(), user );
//        User admin = new User( "admin", "test" );
//        admin.AddRole( "Admin" );
//        users.put( admin.getUserName(), admin );
//
//        User both = new User( "user_admin", "test" );
//        both.AddRole( "User" );
//        both.AddRole( "Admin" );
//        users.put( both.getUserName(), both );

//        String username = "iso";
//        String password = "iso";
////
//        User u = new User( username, password );
//        u.AddRole( "User" );
//        users.put( username, u );
//        addUser( u );
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public User getUserByUserId( String username )
    {
        return getUser2( username );
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser( String userName, String password )
    {
        User user = getUser2( userName ); //users.get( userName );
        return user != null && user.getPassword().equals( password ) ? user.getRoles() : null;
    }

    public boolean addUser( User user )
    {
        boolean con = false;
        EntityManager em = getEntityManager();

        try
        {
            if ( !em.contains( user ) )
            {
                em.getTransaction().begin();
                em.persist( user );
                em.getTransaction().commit();
            }

            if ( em.contains( user ) )
            {
                con = true;
            }
            else
            {
                System.out.println( " Dont  Exists " );
            }
        }
        finally
        {
            em.close();
        }
        return con;
    }

    public User getUser2( String username )
    {
        EntityManager em = getEntityManager();
        //Long idL = (long) id;

        User user = null;

        try
        {
            user = em.find( User.class, username );
        }
        catch (Exception e)
        {

        }

        return user;
    }

    public int getAllSize()
    {
        int i = 0;

        EntityManager em = getEntityManager();

        User user = null;
        Query query = null;

        try
        {
            query = em.createQuery( "SELECT u from USER u" ); //em.find( User.class, username );
            //query.setParameter( "username", username);
            List<String> list = query.getResultList();

            i = list.size();
        }
        catch (Exception e)
        {
            em.close();
        }

        return i;
    }

    static List<User> users1 = new ArrayList();

    public List<User> getAllUsers()
    {

        EntityManager em = getEntityManager();
        Query query = null;

        query = em.createQuery( "SELECT u FROM User u" );
        users1 = query.getResultList();

//        User user1 = new User("Alexander", "Lortepassword");
//        users1.add(user1);
//        User user2 = new User("User2", "User2");
//        users1.add(user2);
//        User user3 = new User("User3", "User3");
//        users1.add(user3);
        return users1;
    }

    public void deleteUser( String username )
    {
        //users1.remove(users1.get(0));

        EntityManager em = getEntityManager();
        User u = null;

        try
        {
            u = em.find( User.class, username );
        }
        catch (Error e)
        {
            // Does nothing.
        }

        try
        {
            if ( u != null )
            {
                em.getTransaction().begin();
                em.remove( u );
                em.getTransaction().commit();
                em.close();
            }
        }
        catch (Error e)
        {
            // Does nothing.
        }
    }

}
