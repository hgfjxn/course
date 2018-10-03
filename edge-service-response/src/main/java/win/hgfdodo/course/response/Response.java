package win.hgfdodo.course.response;

public class Response {

    private ResponseType type;
    private boolean success;
    private Object message;

    public static Response of(ResponseType type){
        return new Response(type);
    }

    public Response(ResponseType type) {
        this.type = type;
        this.success = type.isPositive();
        this.message = null;
    }

    public Response(ResponseType type, Object message) {
        this.type = type;
        this.success = type.isPositive();
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", success=" + success +
                ", message=" + message +
                '}';
    }
}
