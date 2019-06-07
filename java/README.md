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
java TopControlledTerms
java TopControlledTerms database=ins
java TopControlledTerms database=ins count=25
```
