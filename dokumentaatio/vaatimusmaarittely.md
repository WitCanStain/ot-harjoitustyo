# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tavoitteena on luoda sovellus, joka mahdollistaa käyttäjän systeemin erinäisten
ominaisuuksien monitoroinnin, samaan tapaan siis kuin [conky](https://en.wikipedia.org/wiki/Conky_(software)).
Sovellus siis piirtää eräänlaisen monitorointi-ikkunan, johon käyttäjä voi sijoittaa
haluamiaan parametrejä. Käyttäjä voi myös tallettaa useamman parametritiedoston joista
valitsemalla saadaan erilaisia näkymiä.

## Käyttäjät

Sovelluksella on vain yksi käyttäjätyyppi, _normaali käyttäjä_.

## Käyttöliittymä

Sovelluksella on yksinkertainen käyttöliittymä: sovellus aukeaa komponenttinäkymään. Jos käyttäjä haluaa, hän voi lisätä valikon kautta lisää komponentteja. Tässä tilanteessa avautuu uusi ikkuna jossa käyttäjä syöttää komponentin parametrit.

## Perusversion tarjoama toiminnallisuus

Perusversio tarjoaa seuraavanlaisia toimintoja:
- käynnistyessä käyttäjä voi määrittää mitä tiedostoa käytetään parametritiedostona.
- käyttäjä voi parametritiedosto(j)a muokkaamalla päättää, minkälaista toiminnallisuutta sovelluksen piirtämä ikkuna sisältää.
- käyttäjä voi myös ohjelman graafisen käyttöliittymän avulla lisätä komponentteja näkymään sekä tallentaa muutokset parametritiedostoon.
- perusversion näkymäkomponentteja:
	- keskusmuistin tämänhetkinen käyttö ja keskumuistin tilavuus
	- tämänhetkinen prosessorien käyttöaste
	- erinäisiä systeemin ominaisuuksia, kuten käyttöjärjestelmän arkkitehtuuri ja nimi
	- juurihakemiston tilavuus sekä käytetty tila
	- käyttäjän määrittämää tekstiä
	Näkymäkomponentit voi tyypistä riippuen ilmaista joko edistymispalkin, viivakaavion tai tekstin avulla. Käyttäjä voi määrittää millä tavalla tieto 		esitetään sekä kuinka usein tietoa päivitetään.
	

## Jatkokehitysideoita

Sovellusta voisi jatkokehittää lisäämällä useampia käyttäjän määrittämiä ominaisuuksia, kuten ip-osoitteen, latausnopeuden, tai tällä hetkellä käynnissä olevia prosesseja. Lisäksi voisi tulevaisuudessa lisätä drag-n-drop ominaisuuden, joka mahdollistaisi näkymäkomponettien siirtämisen hiiren avulla.


