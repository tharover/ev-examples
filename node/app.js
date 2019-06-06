const express = require('express');
const request = require('request');
const app = express();

const apiKey = "06c115a36721e78c955a7bb152835b35";
const insttoken = "f0df1b6d76f2281ef8e475260910c83f";

//
// Simple service to return the first 10 terms by results count from Compendex
//
app.get('/top', (req, res) => {
    // Call Search API
    request.get("https://api.elsevier.com/content/ev/results?sortField=relevance&navigator=true&navigatorDataCount=10&query=cpx%20WN%20db&apiKey="+apiKey+"&insttoken="+insttoken, 
        (error, response, body) => {
            let results = JSON.parse(body);
            if (error || results.PAGE['RESULTS-COUNT'] == 0 || !results.PAGE.NAVIGATORS) {
                res.json(results);
            } else {
                let cvnav = results.PAGE.NAVIGATORS.NAVIGATOR.filter(nav => nav.NAME == 'cvnav');
                if (cvnav && cvnav[0].MODIFIER) {
                    res.json({"PAGE": { "RESULTS-COUNT":results.PAGE['RESULTS-COUNT'], TERMS: cvnav[0].MODIFIER}});
                } else {
                    res.json({ERROR:"No terms are available for this search"});
                }
            }
        });
});

app.get('/graph', (req, res) => {
    res.sendFile(__dirname + "/graph.html");
});

// Listen to the App Engine-specified port, or 8080 otherwise
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server listening on port ${PORT}...`);
});