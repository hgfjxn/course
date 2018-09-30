package win.hgfdodo.course.response;

public class ExceptionResponse extends Response{

    private String cause;

    public ExceptionResponse(String cause) {
        super(ResponseType.EXCEPTION_RESPONSE);
        this.cause = cause;
    }

    public ExceptionResponse(Exception e){
        super(ResponseType.EXCEPTION_RESPONSE);
        this.cause = e.getMessage();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "cause='" + cause + '\'' +
                '}';
    }
}
