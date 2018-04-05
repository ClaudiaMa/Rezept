/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import javax.ejb.EJB;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Alina
 */
public class RezeptBeanTest {
   
    
    @Test
    public void testStringHasNumbers() {
        RezeptBean instance = new RezeptBean();
        System.out.println("testStringHasNumbers");

        
        String testSearch = "1234keinRezept";

        boolean expected = false;
        boolean result = instance.isValid(testSearch);

        Assert.assertEquals(expected, result);
        
    }

  
   @Test
   public void testStringHasChars() {
       RezeptBean instance = new RezeptBean();
       System.out.println("testStringHasChars");
      
      String testSearch = "1-2-3..@%.rezept..*";
      
      boolean expected = false;
      boolean result = instance.isValid(testSearch);
      
      Assert.assertEquals(expected, result);
      
  }
   
   @Test 
   public void testStringIsValid() {
      RezeptBean instance = new RezeptBean(); 
      System.out.println("testStringIsValid");

      String testSearch = "Rezept richtig eingegeben";
      
      boolean expected = true;
      boolean result = instance.isValid(testSearch);
      
      Assert.assertEquals(expected, result);
       
   }
  
  
    
} 
