package win.hgfdodo.course.response;

public class Response {

    private ResponseType type;
    private boolean success;

    public static Response of(ResponseType type){
        return new Response(type);
    }

    public Response(ResponseType type) {
        this.type = type;
        this.success = type.isPositive();
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

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", success=" + success +
                '}';
    }
}
