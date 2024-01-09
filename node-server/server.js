var fs = require("fs");
var path = require("path");
var host = "localhost";
var port = 8085;
var express = require("express");

var app = express();
app.use('/static', express.static('static'));

app.get("/", function(request, response){
    response.sendFile(path.resolve(__dirname)+"/index.html");
});

app.set("port", port);
app.listen(app.get("port"),function(){
    console.log("Servier listening on port:"+port);
})