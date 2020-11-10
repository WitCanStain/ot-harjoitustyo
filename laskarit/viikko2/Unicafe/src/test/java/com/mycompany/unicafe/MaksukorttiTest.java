package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void rahanLatausKasvattaaSaldo() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    @Test
    public  void saldoVaheneeOikein() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05",kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    @Test
    public void palauttaaTrueJosRiitti() {
        
        assertTrue(kortti.otaRahaa(5));
    }
    @Test
    public void PalauttaaFalseJosEiRiittanyt() {
        assertFalse(kortti.otaRahaa(20));
    }
    
}
