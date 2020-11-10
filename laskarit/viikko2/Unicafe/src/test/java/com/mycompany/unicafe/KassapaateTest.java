package com.mycompany.unicafe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ruby
 */
public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void rahamaaraOikea() {
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void edullisiaLounaitaMyyty() {
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaitaLounaitaMyyty() {
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisenOstoToimiiKassanRaha() {
        kassapaate.syoEdullisesti(1000);
        assertEquals(100240,kassapaate.kassassaRahaa());
    }
    @Test
    public void edullisenOstoToimiiVaihtoRaha() {
        kassapaate.syoEdullisesti(1000);
        assertEquals(760,kassapaate.syoEdullisesti(1000));
                
    }
    @Test
    public void edullisenOstoToimiiMyytyMaara() {
        kassapaate.syoEdullisesti(1000);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaanOstoToimiiMyytyMaara() {
        kassapaate.syoMaukkaasti(1000);
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void maukkaanOstoToimiiKassanRaha() {
        kassapaate.syoMaukkaasti(1000);
        assertEquals(100400,kassapaate.kassassaRahaa());
    }
    @Test
    public void maukkaanOstoToimiiVaihtoRaha() {
        kassapaate.syoMaukkaasti(1000);
        assertEquals(600,kassapaate.syoMaukkaasti(1000));
                
    }
    @Test
    public void edullisenOstoMaksuEiRiitaKassaRahaEiMuutu() {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000,kassapaate.kassassaRahaa());
        
    }
    @Test
    public void maukkaanOstoMaksuEiRiitaKassaRahaEiMuutu() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000,kassapaate.kassassaRahaa());
        
    }
    @Test
    public void edullisenOstoMaksuEiRiitaRahatTakas() {
        
        assertEquals(200,kassapaate.syoEdullisesti(200));
        
    }
    @Test
    public void maukkaanOstoMaksuEiRiitaRahatTakas() {
        
        assertEquals(200,kassapaate.syoMaukkaasti(200));
        
    }
    @Test
    public void edullisenOstoMaksuEiRiitaMyytyEiMuutu() {
        kassapaate.syoEdullisesti(200);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaanOstoMaksuEiRiitaMyytyEiMuutu() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisenOstoKortillaOnnistuu() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals("saldo: 7.60",kortti.toString());
    }
    @Test
    public void maukkaanOstoKortillaOnnistuu() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals("saldo: 6.00",kortti.toString());
    }
    @Test
    public void edullisenOstoKortillaOnnistuuTrue() {
        
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void maukkaanOstoKortillaOnnistuuTrue() {
        
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void maukkaanOstoKortillaOnnistuuMyydytKasvaa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void edullisenOstoKortillaOnnistuuMyydytKasvaa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaKorttiEiMuutuMaukkaasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals("saldo: 2.00",kortti.toString());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaKorttiEiMuutuEdullisesti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals("saldo: 2.00",kortti.toString());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaMyydytEiMuutuEdullisesti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaMyydytEiMuutuMaukkaasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void kortillaEiTarpeeksiRahaaFalseEdullisesti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        
    }
    @Test
    public void kortillaEiTarpeeksiRahaaFalseMaukkaasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        
    }
    @Test
    public void kassanRahaEiMuutuKortillaEdullisesti() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void kassanRahaEiMuutuKortillaMaukkaasti() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    @Test
    public void lataaRahaaSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti,10);
        assertEquals(1010,kortti.saldo());
    }
    @Test
    public void lataaRahaaKassaKasvaa() {
        kassapaate.lataaRahaaKortille(kortti,10);
        assertEquals(100010,kassapaate.kassassaRahaa());
    }
    @Test
    public void lataaRahaaReturn() {
        kassapaate.lataaRahaaKortille(kortti, -10);
        assertEquals(100000,kassapaate.kassassaRahaa());
    }
    
}
