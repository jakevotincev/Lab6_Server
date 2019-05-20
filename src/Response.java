import java.io.Serializable;

class Response implements Serializable {
    private Object response;

    Response(Object response) {
        this.response = response;
    }

    Object getResponse() {
        return response;
    }
}
