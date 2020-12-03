# ssmonitor - Simple System Monitor

Sovelluksen tarkoituksena on auttaa käyttäjää seuraamaan systeeminsä erinäisiä mittareita, kuten prosessorikuormitusta, vapaata muistitilaa, ynnä muuta. 


# Dokumentaatio

[Vaatimusmäärittely](https://github.com/WitCanStain/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/WitCanStain/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Luokkakaavio](https://github.com/WitCanStain/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Viikko 5 release](https://github.com/WitCanStain/ot-harjoitustyo/releases/tag/viikko5)

# Ohjeita

Ohjelmaan on tähän mennessä lisätty kaksi näkymäkomponenttia: prosessorikuormitus sekä vapaa muistitila. Ohjelmalle voi antaa (jos koodin ./src/main/resources hakemistossa ei ole exampleConf.conf tiedostoa, pitää antaa) konfiguraatiotiedosto, jota ohjelma lukee ja jonka määrittelemät näkymäkomponentit se luo ikkunaan.
Konfiguraatiotiedosto rakentuu seuraavasti:

// - alkuiset rivit jätetään huomioimatta (kommentti)

$ - alkuiset rivit otetaan mukaan ohjelman määrittelyyn. 

Joka rivin eri komponentit eritellään | - merkillä. Esimerkkirivi voisi olla vaikka seuraava: 

$cpu_usage|%cpu usage:

Tämä rivit luo näkymään viivakaavion joka kuvaa tämänhetkistä prosessorin käyttöastetta (cpu_usage) sekä lisää sen eteen tekstin joka kuvailee viivakaaviota (%cpu usage: ). %-alkuiset komponentit käsitellään tekstinä ja niihin voi laittaa mitä haluaa.
Ohjelmaan voi laittaa useampia komponentteja samaan aikaan. Esimerkiksi

$cpu_usage|%cpu usage: 

$system_memory|%system memory:

on pätevä konfiguraatio. Jos haluaa itse luoda konfiguraatiotiedoston, niin ohjelmaa ajaessa jarilla sille täytyy antaa konfiguraatiotiedoston polku:
java -jar ssmonitor-1.0.jar "/polku/konfiguraatioon/tiedosto"

Testaus: mvn test, mvn test jacoco:report

CheckStyle: mvn jxr:jxr checkstyle:checkstyle

Jarin generointi: mvn package
