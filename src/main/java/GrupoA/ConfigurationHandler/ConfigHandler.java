package GrupoA.ConfigurationHandler;


import java.io.IOException;

public class ConfigHandler {
    public static void main(String[] args) throws IOException, InterruptedException {
        boolean useGoogleCloud = false;
        if(args.length > 0) {
            useGoogleCloud = args[0].equals("-gc");
        }
        ConfigurationHandlerServer server = new ConfigurationHandlerServer(useGoogleCloud);
        server.start();
        server.blockUntilShutdown();
    }

}
