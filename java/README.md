# Java Example
Simple Java language example of calling EV Search API and retrieving the top terms (10 by default but up to 160 can be retrieved) from a database (default is Compendex) by result count.

Please update the API key and insttoken variables with values obtained from Elsevier customer support team:

```
private static String EV_API_KEY = "YOUR_API_KEY";
private static String EV_API_INSTTOKEN = "YOUR_INST_TOKEN";
```

Then simply compile and run the TopControlledTerms class.  It can take optional parameters:

```
javac TopControlledTerms.java
java TopControlledTerms database=ins
  Requesting:  'https://api.elsevier.com/content/ev/results?sortField=relevance&navigator=true&navigatorDataCount=10&apiKey=xxxxxxxxxxxxxxxx&insttoken=yyyyyyyyyyyyyyyy&query=ins%20WN%20db
  10 terms found:
  Organic Compounds (470591)
  X-Ray Diffraction (388067)
  Optimisation (311295)
  Scanning Electron Microscopy (303442)
  Finite Element Analysis (294147)
  Iii-V Semiconductors (288837)
  Silicon (281463)
  Elemental Semiconductors (253976)
  Nanofabrication (229252)
  Nanoparticles (216797)
```
