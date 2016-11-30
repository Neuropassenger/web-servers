package main;

import accounts.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

/**
 * Created by turge on 028 28.11.16.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AccountService accountService = new AccountService();

        // Создание обработчика для сервлетов
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        // Добавления сервлета, обрабатывающего регистрацию
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        // Добавление сервлета, обрабатывающего авторизацию
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        // Создание обработчика для статических ресурсов
        ResourceHandler resourceHandler = new ResourceHandler();
        // Добавление папки с index.html
        resourceHandler.setResourceBase("public_html");

        // "Запаковка" обработчиков для передачи серверу
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[] {resourceHandler, context});

        // Создание сервера
        Server server = new Server(8080);
        // Прикрепление обработчиков
        server.setHandler(handlerList);

        // Запуск сервера
        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
