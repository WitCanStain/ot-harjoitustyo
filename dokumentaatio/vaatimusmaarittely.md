# Vaatimusmäärittely

## Sovelluksen tarkoitus

Tavoitteena on luoda sovellus, joka mahdollistaa käyttäjän systeemin erinäisten
ominaisuuksien monitoroinnin, samaan tapaan siis kuin [conky](https://en.wikipedia.org/wiki/Conky_(software)).
Sovellus siis piirtää eräänlaisen monitorointi-ikkunan, johon käyttäjä voi sijoittaa
haluamiaan parametrejä. Käyttäjä voi myös tallettaa useamman parametritiedoston joista
valitsemalla saadaan erilaisia näkymiä.

## Käyttäjät

Sovelluksella on vain yksi käyttäjätyyppi, _normaali käyttäjä_.

## Perusversion tarjoama toiminnallisuus

Perusversio tarjoaa seuraavanlaisia toimintoja:
- käynnistyessä käyttäjä voi määrittää mitä tiedostoa käytetään parametritiedostona. Sovellus tallentaa myös tiedon oletustiedostosta.
- käyttäjä voi parametritiedosto(j)a muokkaamalla päättää, minkälaista toiminnallisuutta sovelluksen piirtämä ikkuna sisältää.
- perusversion näkymäkomponentteja:
	- keskusmuistin tämänhetkinen käyttö
	- läppäriä käytettäessä akun tila
	- uptime eli kuinka pitkään järjestelmä on ollut käynnissä
	- järjestelmän nimi
	- top x lista tällä hetkellä käynnissä olevista prosesseista.
	- [lisäksi tehty mahdollisuus määrittää näkymäkomponteille nimiä / asettaa tekstiä ikkunaan]

## Jatkokehitysideoita

- lisää parametrejä, kuten järjestelmän ip-osoite, prosessorin käyttöaste [tehty], levymuistin tila [tehty], järjestelmän kellonaika, top x lista tällä hetkellä käynnissä olevista prosesseista.

- käyttäjä voi päättää käynnistetäänkö sovellus omassa ikkunassaan vai piiretäänkö se suoraan X11:n root-ikkunaan.
- käyttäjä voi päättää komponenttien värin.
- parametritiedostot toteutetaan tietokantana.
- ohjelman vipujen avulla voi tehdä joitain muutoksia parametritiedostoon.
