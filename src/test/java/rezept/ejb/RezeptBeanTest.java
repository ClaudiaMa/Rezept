/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rezept.ejb;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alina
 */
public class RezeptBeanTest {
    
  
  @Test
  public void testStringHasNumbers() {
       System.out.println("testStringHasNumbers");
      
      RezeptBean rezeptBean = new RezeptBean();
      String testSearch = "1234keinRezept";
      
      boolean expected = false;
      boolean result = rezeptBean.isValid(testSearch);
      
      Assert.assertEquals(expected, result);
      //Entweder oben oder unten
      Assert.assertFalse(result);
  }
  
  
   @Test
   public void testStringHasChars() {
      System.out.println("testStringHasChars");
      
      RezeptBean rezeptBean = new RezeptBean();
      String testSearch = "1-2-3..@%.rezept..*";
      
      boolean expected = false;
      boolean result = rezeptBean.isValid(testSearch);
      
      Assert.assertEquals(expected, result);
      
  }
   
   @Test 
   public void testStringIsValid() {
       
      System.out.println("testStringIsValid");
      
      RezeptBean rezeptBean = new RezeptBean();
      String testSearch = "Rezept richtig eingegeben";
      
      boolean expected = true;
      boolean result = rezeptBean.isValid(testSearch);
      
      Assert.assertEquals(expected, result);
       
   }
  
  
    
}
