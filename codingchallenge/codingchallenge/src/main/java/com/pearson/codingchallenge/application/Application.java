package com.pearson.codingchallenge.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({ "com.pearson.codingchallenge.*" })
@SpringBootApplication
public class Application
{

	/**
     * @param args.
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
