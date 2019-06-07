# Node.js Example
This folder contains a very simple Node web server.  It facilitates to URLs - /top and /graph.

- /top: A simple service that returns the top 10 terms by result count for Compendex
- /graph:  An example of rendering a graph (via [Highcharts.js](https://www.highcharts.com/demo/pie-basic)) using the /top service

## Setup
You must have a relatively newer version of [Node.js](https://nodejs.org/) and npm installed in order to run this sample.  The [nodemon](https://www.npmjs.com/package/nodemon) package is highly suggested as well.  Start by creating a new directory to host the project:

```
npm install -g nodemon 
git clone https://github.com/tharover/ev-examples.git
cd ev-examples/node
npm init
```

This should initialize the project workspace.  The express and request libraries are required to run the project so please add them if they are not in your global Node modules:

```
npm install express
npm install request
```
Now open the app.js file and replace the "apiKey" and "insttoken" variables with the values obtained from Elseiver support team.  

The application should now be ready to run!  The [package.json](package.json) file contains script commands for running or debugging the application via "npm start" or "npm debug":

```
"start": "nodemon app.js"
"debug": "nodemon --inspect app.js"
```

