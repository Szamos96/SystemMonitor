# SystemMonitor

## Számítógép monitorozó kliens és szerver megvalósítása (REST)

Írjunk egy REST kommunikáción alapuló szerver és kliens modult. A kliens feladata, hogy egy számítógépen folyamatosan fusson, és percenként teljesítmény adatokat küldjön a szerverre: CPU terheltség, memória fogyasztás, stb. A szerver implementálja a megfelelő REST végpontokat az adatok fogadásához, illetve lekérdezéséhez. A kliensnek legyen lehetősége az egyes paraméterek múltban elküldött értékeit visszakérni egy adott intervallumon belül. Az adatokat a szerver valamilyen NoSQL adatbázisban tárolja el (megfontolható valamilyen time series adatbázis használata is, de nem kötelező).
