package pack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientTcp_2 {
    public static void main(String[] args){
        new ClientTcp_2().start();
    }

    final void start(){
        System.out.println("[CLIENT_2]");
        System.out.println("Введите комманду иди \"/q\" для выхода");
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        exec.execute(new RunnableClient());
        exec.shutdown();
    }
}
