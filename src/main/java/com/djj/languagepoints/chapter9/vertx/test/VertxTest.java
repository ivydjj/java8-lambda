package com.djj.languagepoints.chapter9.vertx.test;

import io.vertx.core.Vertx;

/**
 * Created by djj on 2018/10/21.
 *
 * Vert.x 框架
 */
public class VertxTest {
    public static void main(String[] args) {
        /*
        Create an HTTP server which simply returns "Hello World!" to each request.

        执行后，通过 localhost:8080 访问
         */
        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8080);
    }
}
