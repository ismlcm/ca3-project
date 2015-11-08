package facades;

import entity.Currency;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class XmlReader extends DefaultHandler
{

    private static final ArrayList<String> list = new ArrayList();
    private static final HashMap<String, Currency> map = new HashMap();

    @Override
    public void startDocument() throws SAXException
    {

    }

    @Override
    public void endDocument() throws SAXException
    {

    }

    @Override
    public void startElement( String uri, String localName, String qName, Attributes attributes ) throws SAXException
    {
        for ( int i = 0; i < attributes.getLength(); i++ )
        {
            list.add( attributes.getValue( i ) );
        }
    }

    public static ArrayList<Currency> xmlToArrayList()
    {
        try
        {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler( new XmlReader() );
            URL url = new URL( "http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en" );
            xr.parse( new InputSource( url.openStream() ) );
        }
        catch (SAXException | IOException e)
        {
            e.printStackTrace();
        }

        //------------------------------------------------
        
        ArrayList<Currency> curList = new ArrayList();
        //HashMap<String, Currency> curMap = new HashMap();

        for ( int i = 5; i < list.size(); i += 3 )
        {
            int j = i + 1;
            int k = j + 1;

            Currency cur = new Currency( list.get( i ), list.get( j ), list.get( k ) );
            curList.add( cur );
        }

        return curList;
    }

    public static void main( String[] argv )
    {
        ArrayList<Currency> curList = xmlToArrayList();

        for ( Currency curList1 : curList )
        {
            System.out.println( curList1.getCode() );
            System.out.println( curList1.getDescr() );
            System.out.println( curList1.getAmount() );
            System.out.println( "------------------------" );
        }
    }
}
