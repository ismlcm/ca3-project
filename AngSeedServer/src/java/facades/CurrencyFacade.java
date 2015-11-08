/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entity.Currency;
import static facades.UserFacade.users1;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Ismail Cam
 */
public class CurrencyFacade
{

    public static void main( String[] args )
    {
        //CurrencyFacade c = new CurrencyFacade();

        //c.insertCurrencies( XmlReader.xmlToArrayList() );
    }

    private EntityManagerFactory emf;

    public CurrencyFacade()
    {

        EntityManagerFactory e = Persistence.createEntityManagerFactory( "PU" );
        emf = e;
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    /**
     * ************************************************
     * INSERT ALL CURRENCIES TO DATABASE
     *************************************************
     */
    public void insertCurrencies( ArrayList<Currency> cur )
    {
        EntityManager em = getEntityManager();

        java.util.Date date = new java.util.Date();
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear() + 1900;
        
        String now = day + "-" + month + "-" + year;

        try
        {
            for ( Currency c : cur )
            {
                c.setCurDate( now );

                em.getTransaction().begin();
                em.persist( c );
                em.getTransaction().commit();
            }
        }
        finally
        {
            em.close();
        }
    }

    /**
     * ************************************************
     * GET ALL CURRENCIES FROM DATABASE
     *************************************************
     */
    public List<Currency> getAll( String date )
    {
        EntityManager em = getEntityManager();
        Query query = null;

        query = em.createQuery( "SELECT c FROM Currency AS c WHERE c.curDate = :date" );
        query.setParameter( "date", date );
        List<Currency> list = query.getResultList();

        return list;
    }

    /**
     * ************************************************
     * GET CURRENCY FROM DATABASE
     *************************************************
     */
    public Currency getCurrency( String code )
    {
        Currency cur;

        EntityManager em = getEntityManager();
        Query query = null;

        query = em.createQuery( "SELECT c FROM Currency AS c WHERE c.code = :code" );
        query.setParameter( "code", code );
        cur = (Currency) query.getResultList().get( 0 );

        return cur;
    }

    /**
     * ************************************************
     * CALCULATE CURRENCY
     *************************************************
     */
    public String calCur( String amount, String curFrom, String curTo )
    {
        int am = Integer.parseInt( amount );
        double from = Double.parseDouble( curFrom );
        double to = Double.parseDouble( curTo );

        double cal = (((from / 100) / to) * am * 100);

        String result = Double.toString( cal );

        return result;
    }

}
