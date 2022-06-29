package pack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTcp_1 {
    public static void main(String[] args){
        new ClientTcp_1().start();
    }

    final void start(){
        System.out.println("[CLIENT_1]");
        System.out.println("Введите комманду иди \"/q\" для выхода");
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        exec.execute(new RunnableClient());
        exec.shutdown();
    }
}