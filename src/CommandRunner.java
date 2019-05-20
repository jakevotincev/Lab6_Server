import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * <p>Класс для считывания и выполнения команд</p>
 *
 * @author Вотинцев Евгений
 * @version 1.0
 */
public class CommandRunner {
    private String command = "";
    private ByteBuffer buffer;
    private DatagramChannel channel;
    private InetSocketAddress clientAddress;
    private Sender sender;
    private Response response;
    private Command command1;


    /**
     * <p>Объект команды</p>
     */
    private Animal current_object = new Pyatachok();


    /**
     * <p>Считает количество заданных символов в строке</p>
     *
     * @param word Строка
     * @param s    Символ
     * @return Количество
     */
    public int charCounter(String word, char s) {
        int count = 0;
        for (char element : word.toCharArray()) {
            if (element == s) count += 1;
        }
        return count;
    }

    private int charCounter(String word, char s, char e) {
        int countS = 0;
        int countE = 0;
        for (char el : word.toCharArray()) {
            if (countE % 2 == 0) {
                if (el == s) countS += 1;
            }
            if (el == e) countE += 1;
        }
        return countS;
    }

    String filename = System.getenv().get("FILENAME");

    public CommandRunner(String command, ByteBuffer buffer, DatagramChannel channel, InetSocketAddress clientAddress, Animal current_object) {
        this.command = command;
        this.buffer = buffer;
        this.channel = channel;
        this.clientAddress = clientAddress;
        this.current_object = current_object;
        sender = new Sender(buffer, channel, clientAddress);
    }


    public CommandRunner(String command, ByteBuffer buffer, DatagramChannel channel, InetSocketAddress clientAddress) {
        this.command = command;
        this.buffer = buffer;
        this.channel = channel;
        this.clientAddress = clientAddress;
        sender = new Sender(buffer, channel, clientAddress);
    }

    public CommandRunner(ByteBuffer buffer, DatagramChannel channel, InetSocketAddress clientAddress, Command command1) {
        this.buffer = buffer;
        this.channel = channel;
        this.clientAddress = clientAddress;
        this.command1 = command1;
        sender = new Sender(buffer, channel, clientAddress);
    }

    /**
     * <p>Считывает и запускает команды</p>
     */
    public synchronized void runCom() throws IOException {
        switch (command1.getName()) {
            case ("remove"): {
                String message = "";
                if (CollectionManager.remove(command1.getObject())) {
                    message = "Объект " + command1.getObject().getName() + " удален";
                    response = new Response(message);
                    sender.send(response.getResponse());
                } else {
                    message = "Объект " + command1.getObject().getName() + " не найден в коллекции";
                    response = new Response(message);
                    sender.send(response.getResponse());
                }
            }
            break;
            case ("show"): {
                String message = "";
                message = CollectionManager.show();
                response = new Response(message);
                sender.send(response.getResponse());
            }
            break;
            case ("add_if_min"): {
                if (CollectionManager.add_if_min(command1.getObject())) {
                    String message = "Элемент добавлен в коллекцию";
                    response = new Response(message);
                    sender.send(response.getResponse());
                } else {
                    // System.out.println("Элемент не добавлен в коллекцию");
                    String message = "Элемент не добавлен в коллекцию";
                    response = new Response(message);
                    sender.send(response.getResponse());
                }
            }
            break;
            case ("remove_greater"): {
                String message = CollectionManager.remove_greater(command1.getObject());
                response = new Response(message);
                sender.send(response.getResponse());
            }
            break;
            case ("info"): {
                String message = CollectionManager.info();
                response = new Response(message);
                sender.send(response.getResponse());
            }
            break;
            case ("add"): {
                if (CollectionManager.add(command1.getObject())) {
                    String message = "Элемент добавлен в коллекцию";
                    response = new Response(message);
                    sender.send(response.getResponse());
                } else {
                    String message = "Элемент не добавлен в коллекцию";
                    response = new Response(message);
                    sender.send(response.getResponse());
                }
            }
            break;
            case ("help"): {
                String message = CollectionManager.help();
                response = new Response(message);
                sender.send(response.getResponse());
            }
            break;
              case ("import"): {
             String message = CollectionManager.import_(command1.getFile());
             response = new Response(message);
             sender.send(response.getResponse());
             }
             break;
            case ("save"): {
                try {
                    String message = CollectionManager.save(CollectionManager.animals, filename);
                    response = new Response(message);
                    sender.send(response.getResponse());
                } catch (IOException | NullPointerException e) {
                    String message ="Не удалось сохранить коллекцию";
                    response = new Response(message);
                    sender.send(response.getResponse());
                } catch (TransformerException | ParserConfigurationException e) {
                    String message = "Ошибка парсинга";
                    response = new Response(message);
                    sender.send(response.getResponse());
                }
            }break;
            case ("connect"):{
                String message ="Соединение с сервером установлено";
                response = new Response(message);
                sender.send(response.getResponse());
            }
        }
    }
}
