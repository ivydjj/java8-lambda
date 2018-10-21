/*
 在Shell环境下执行：
 $ vertx run server.js
 在浏览器输入地址：http://localhost:8080/
 */
load('vertx.js');

vertx.createHttpServer().requestHandler(function(req){
    req.response.end("hello vertx!");
}).listen(8080, 'localhost');