/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExecuteService;

import facades.CurrencyFacade;
import facades.XmlReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

/**
 *
 * @author AlexanderNielsen
 */
public class ExecuteService
{
    
    CurrencyFacade cf = new CurrencyFacade();
    
    private final ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool( 1 );

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args )
    {
        ExecuteService e = new ExecuteService();
        e.runTimer();
    }
    
    public void runTimer()
    {
        final Runnable beeper = new Runnable()
        {
            public void run()
            {
                System.out.println( "beep" );

                // INSERT TO DATABASE
                cf.insertCurrencies( XmlReader.xmlToArrayList() );
                
                
            }
        };
        final ScheduledFuture<?> beeperHandle
                = scheduler.scheduleAtFixedRate( beeper, 0, 24, HOURS );
        scheduler.schedule( new Runnable()
        {
            public void run()
            {
                beeperHandle.cancel( true );
            }
        }, 999 * 999, DAYS );
        
    }
    
}
