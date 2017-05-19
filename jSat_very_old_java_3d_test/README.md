jSat
by mjt, 2007-2008
mixut@hotmail.com

Yksinkertainen 3D-frameworkki joka lataa obj ja md5 tiedostoja.
!! md5 lataus bugittaa joten jotkut objektit voi näkyä, useimmat ei !!


Vaatii vähintään Java5.
Käyttää lwjgl:ää: http://lwjgl.org/




---------
EXAMPLES:

Pieniä yksinkertaisia esimerkkejä.

käynnistä startdemo.bat tai kirjoita
  java -Djava.library.path=native/ -jar dist/jsat_demos.jar

ja esimerkkipaketti käynnistyy ja voit valita demon.

Ohjaus
------
Esimerkeissä käytetään hiirtä ja näppäimistöä.
  
  hiiri + vasen nappi vaihtaa suuntaa (kuten myös nuolinäppäimet)
  W,S,A,D kamera
  U,J,H,K valo
  R, F  ylös/alas
kala-demo:
  O ja P liikuttaa 2 objektia
  TAB vaihtaa 1st person ja 3th person moodin

6DOF-demossa Z ja X pyörittää kameraa.

esimerkki15 näppäimet 1 ja 2 vaihtaa skenen 1objekti/jaettu huoneisiin.
esimerkki17 (ode) välilyönti lisää objekteja. 

Test(numero).java -tiedostojen alussa kerrottu näppäimet.

Obj Blender export
------------------
jSat lukee wavefront obj formaattia, esim Blenderillä voit tallentaa 3d-mallin obj:ksi.
Exporttaa objekti kolmioina sekä normaalit, uv:t, materiaalit, edget.

kameralle/objektille voi luoda blenderillä radan viivoista, exportata objektina ja ottaa edge/material/ym tiedot pois että out.obj:iin jää vain vertex-infot.

3d-maailman mallintaminen
-------------------------
Ensin mallinnetaan koko skene, texturoidaan. Sitten joinataan kaikki yhteen ja pilkotaan
huoneiksi. Näin joka huone cullataan frustumiin eikä rendata koko skeneä joka framella.
knifellä saa näppärästi pilkottua. painaa K, sit knife exact, vetää viivan skenen päältä
ja painaa enter ja blenkku jakaa osiin viivan kohdilta. sit merkataan vertexei, painetaan P,
eli separate ja näin luodaan huoneet. 

Muut kirjastot
--------------
LWJGL   - The Lightweight Java Game Library. LWJGL is available under a BSD license.  http://lwjgl.org/

FScript - FScript is Copyright (c) murlen 2000-2002, and released under the Library Gnu Public License.  http://fscript.sourceforge.net/

Odejava - ODE stands for Open Dynamics Engine. It is an open source (BSD/LGPL) rigid body physics library written in C. http://odejava.org/OdejavaIntro

