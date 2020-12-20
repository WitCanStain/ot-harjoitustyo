# Käyttöohje

Lataa ssmonitor.jar

## Ohjelman käynnistäminen

Ohjelman käynnistetään käskyllä 

`java -jar ssmonitor.jar "vapaaehtoinen konfiguraatiotiedosto"`

## Konfiguraatiotiedoston asettaminen

Ohjelma tukee seuraavien järjestelmätietojen näyttämistä: 

cpu_usage, drive_memory_percentage, drive_memory_total, ram_usage_percentage, ram_free, ram_total, os.name, os.version, user.name, os.arch

Eli prosessorin käyttöaste, juurihakemiston käyttöaste, juurihakemiston kokonaistilavuus, keskusmuistin käyttöaste, keskusmuistin tilavuus, käyttöjärjestelmän nimi, versio, käyttäjänimi sekä arkkitehtuuri.

Sekä seuraavia esitysmuotoja:

`presentation_type=linechart, presentation_type=progressbar, presentation_type=text`

Sekä myös päivitysaikamääriä jotka ovat yli 0. Oletusarvo on 500.

`refresh_rate=500`

Sovellukselle voi myös antaa tekstiä joka näkyy näkymäkomponentin vieressä:

`%tässä on tekstiä`

Rivejä voi myös kommentoida pois:

`//kommentti`

Ohjelma lukee konfiguraatiotiedostoa rivi kerrallaan, ja eri parametrit eritellää pystyviivalla: `|`.

Yksi mahdollinen konfiguraatiotiedosto olisi vaikka seuraava:

  `// this is a comment`
  
  `$cpu_usage|presentation_type=linechart|refresh_rate=500|%cpu usage: `
  
  `$system_memory|presentation_type=progressbar|%system memory: `
  
  `%Operating system: |$os.name`
  
  `%User name: |$user.name`
  
  `%Operating system version: |$os.version`
  
Käyttäjä voi myös käyttöliittymän sisältä luoda uusia komponentteja valikon Create -> New Component kautta. Tässä tapauksessa käyttäjän tulee antaa ylläolevat tiedot suoraan sovellukselle.
  
Ohjelmaa käynnistäessä tulee sille antaa konfiguraatiotiedoston polku, eli esimerkiksi:

`java -jar ssmonitor.jar "path/to/file`

