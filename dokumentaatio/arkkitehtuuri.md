# Arkkitehtuurikuvaus

Ohjelman rakenne on hajautettu kolmen pakkauksen välille: 

![Untitled Diagram](https://user-images.githubusercontent.com/56148285/101542604-ab3ca000-39ab-11eb-8c8d-5b03fcb9aa37.png)


Pakkaus ssmonitor.gui sisältää käyttöliittymään tarvittavan toiminnallisuuden, pakkaus ssmonitor.file tiedostojen kanssa toimimiseen tarvittavan toiminnallisuuden, ja pakkaus ssmonitor.sysinfo sisältää järjestelmän kanssa toimimiseen tarvittavan toiminnallisuuden sekä muuta sovelluslogiikkaa kuten lankojen luonnin.

## Sovelluslogiikka

Luokkakaavio näyttää luokkien väliset riippuvaisuudet:

![luokkakaavio2](https://user-images.githubusercontent.com/56148285/100922887-10dde780-34e7-11eb-927d-0522032cbcf6.png)


Ohjelma lukee ensin konfiguraatiotiedoston, ja sen perusteella lähettää pyyntöjä GuiComponent-luokalle, joka annettujen parametrien perusteella pyytää RTNodes-luokkaa luomaan erilaisia JavaFX-nodeja. Mikäli on tarvetta päivittää informaatiota reaaliajassa, niin RTNodes-luokka pyytää RTExecutors-luokkaa luomaan uuden langan joka päivittää esimerkiksi LineChartin dataa annetun ajan välein SysInfo-luokan kutsujen avulla.

Sekvenssikaaviosta käy ilmi tapahtumasarja kun halutaan luoda viivakaavio joka näyttää tämänhetkisen CPU:n käyttöasteen:

![Creating a new lineChart depicting CPU usage(2)](https://user-images.githubusercontent.com/56148285/101542165-0e7a0280-39ab-11eb-9005-b84fa31383a3.png)

![Untitled Diagram](https://user-images.githubusercontent.com/56148285/101542604-ab3ca000-39ab-11eb-8c8d-5b03fcb9aa37.png)
