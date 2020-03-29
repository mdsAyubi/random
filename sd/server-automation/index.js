var express = require("express");
var handler = require("./handler");
var app = express();

// respond with "hello world" when a GET request is made to the homepage
app.get("/", function(req, res) {
  res.send("hello world");
});

app.get("/start", function(req, res) {
  res.send(handler.startServer(req.query.browser, req.query.url));
});

app.get("/stop", function(req, res) {
  res.send(handler.stopServer(req.query.browser));
});

app.get("/cleanup", function(req, res) {
  res.send(handler.cleanup(req.query.browser));
});

app.get("/geturl", function(req, res) {
  res.send(handler.getUrl(req.query.browser));
});

var server = app.listen(3000, function() {
  console.log("Listening on 3000");
});
