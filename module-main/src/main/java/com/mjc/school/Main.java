package com.mjc.school;

import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.helper.MenuHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.util.Scanner;

import static com.mjc.school.helper.Constant.COMMAND_NOT_FOUND;
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("com.mjc.school");
        Scanner keyboard = new Scanner(System.in);
        MenuHelper helper = ctx.getBean("helper", MenuHelper.class);
        NewsController newsController = ctx.getBean("newsController", NewsController.class);
        while (true) {
            try {
                helper.printMainMenu();
                String key = keyboard.nextLine();
                switch (key) {
                    case "1" -> helper.getNews(newsController);
                    case "2" -> helper.getNewsById(newsController, keyboard);
                    case "3" -> helper.createNews(newsController, keyboard);
                    case "4" -> helper.updateNews(newsController, keyboard);
                    case "5" -> helper.deleteNews(newsController, keyboard);
                    case "0" -> System.exit(0);
                    default -> System.out.println(COMMAND_NOT_FOUND);
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
