jSat
by mjt, 2007-2008
mixut@hotmail.com

Yksinkertainen 3D-frameworkki joka lataa obj ja md5 tiedostoja.
!! md5 lataus bugittaa joten jotkut objektit voi n�ky�, useimmat ei !!


Vaatii v�hint��n Java5.
K�ytt�� lwjgl:��: http://lwjgl.org/




---------
EXAMPLES:

Pieni� yksinkertaisia esimerkkej�.

k�ynnist� startdemo.bat tai kirjoita
  java -Djava.library.path=native/ -jar dist/jsat_demos.jar

ja esimerkkipaketti k�ynnistyy ja voit valita demon.

Ohjaus
------
Esimerkeiss� k�ytet��n hiirt� ja n�pp�imist��.
  
  hiiri + vasen nappi vaihtaa suuntaa (kuten my�s nuolin�pp�imet)
  W,S,A,D kamera
  U,J,H,K valo
  R, F  yl�s/alas
kala-demo:
  O ja P liikuttaa 2 objektia
  TAB vaihtaa 1st person ja 3th person moodin

6DOF-demossa Z ja X py�ritt�� kameraa.

esimerkki15 n�pp�imet 1 ja 2 vaihtaa skenen 1objekti/jaettu huoneisiin.
esimerkki17 (ode) v�lily�nti lis�� objekteja. 

Test(numero).java -tiedostojen alussa kerrottu n�pp�imet.

Obj Blender export
------------------
jSat lukee wavefront obj formaattia, esim Blenderill� voit tallentaa 3d-mallin obj:ksi.
Exporttaa objekti kolmioina sek� normaalit, uv:t, materiaalit, edget.

kameralle/objektille voi luoda blenderill� radan viivoista, exportata objektina ja ottaa edge/material/ym tiedot pois ett� out.obj:iin j�� vain vertex-infot.

3d-maailman mallintaminen
-------------------------
Ensin mallinnetaan koko skene, texturoidaan. Sitten joinataan kaikki yhteen ja pilkotaan
huoneiksi. N�in joka huone cullataan frustumiin eik� rendata koko skene� joka framella.
knifell� saa n�pp�r�sti pilkottua. painaa K, sit knife exact, vet�� viivan skenen p��lt�
ja painaa enter ja blenkku jakaa osiin viivan kohdilta. sit merkataan vertexei, painetaan P,
eli separate ja n�in luodaan huoneet. 

Muut kirjastot
--------------
LWJGL   - The Lightweight Java Game Library. LWJGL is available under a BSD license.  http://lwjgl.org/

FScript - FScript is Copyright (c) murlen 2000-2002, and released under the Library Gnu Public License.  http://fscript.sourceforge.net/

Odejava - ODE stands for Open Dynamics Engine. It is an open source (BSD/LGPL) rigid body physics library written in C. http://odejava.org/OdejavaIntro

