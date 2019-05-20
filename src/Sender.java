import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

class Sender {
    private ByteBuffer buffer;
    private DatagramChannel channel;
    private InetSocketAddress clientAddress;

    Sender(ByteBuffer buffer, DatagramChannel channel, InetSocketAddress clientAddress) {
        this.buffer = buffer;
        this.channel = channel;
        this.clientAddress = clientAddress;
    }

    void send(Object object) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(object);
        buffer.clear();
        buffer.put(out.toByteArray());
        buffer.flip();
        channel.send(buffer, clientAddress);
        buffer.clear();
    }
}
