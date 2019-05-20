import org.xml.sax.SAXException;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {

    public static void main(String[] args) {
        int port =4999;
        try {
            if (args.length > 0) port = Integer.valueOf(args[0]);
        } catch (IllegalArgumentException e){
            System.err.println("Не верный формат порта, введите значение int");
            System.exit(1
                );
        }

        String filename = System.getenv().get("FILENAME");
        ParserXML parser = new ParserXML();
        boolean error = true;
        try {
            String filestring = parser.readXML(filename);
            error = !filestring.isEmpty();
            parser.parseXML(filestring);
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("Файл не найден или к нему нет доступа");
            System.exit(0);
        } catch (IOException | SAXException e) {
            if (error) {
                System.err.println("Ошибка xml файла");
                System.exit(0);
            }
        }

        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.bind(new InetSocketAddress(port));
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            buffer.clear();
            while (true) {
                InetSocketAddress clientAddress = (InetSocketAddress) channel.receive(buffer);
                NewThread thread = new NewThread(buffer, channel, clientAddress);
                thread.start();
            }
        } catch (SocketException e) {
            System.err.println("Порт занят, попробуйте использовать другой");
        } catch (IOException e) {
            System.err.println("Ошибка, которая не должна была возникунуть, свяжитесь с разработчиком (код ошибки:01)");
        }


    }

}

class NewThread extends Thread {
    private ByteBuffer buffer;
    private DatagramChannel channel;
    private InetSocketAddress clientAddress;
    private CommandRunner reader = null;
    private Command command = new Command();

    NewThread(ByteBuffer buffer, DatagramChannel channel, InetSocketAddress clientAddress) {
        this.buffer = buffer;
        this.channel = channel;
        this.clientAddress = clientAddress;
    }

    @Override
    public void run() {
        ByteArrayInputStream in = new ByteArrayInputStream(buffer.array());
        ObjectInputStream object = null;
        try {
            object = new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            command = (Command) object.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        reader = new CommandRunner(buffer,channel,clientAddress,command);

        try {
            reader.runCom();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


