/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ismail Cam
 */
@Entity
public class Currency implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String code;
    private String descr;
    private String amount;
    private String curDate;


    public Currency()
    {
    }

    public Currency( String code, String descr, String amount )
    {
        this.code = code;
        this.descr = descr;
        this.amount = amount;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode( String code )
    {
        this.code = code;
    }

    public String getDescr()
    {
        return descr;
    }

    public void setDescr( String descr )
    {
        this.descr = descr;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount( String amount )
    {
        this.amount = amount;
    }

    public String getCurDate()
    {
        return curDate;
    }

    public void setCurDate( String curDate )
    {
        this.curDate = curDate;
    }

    

}
