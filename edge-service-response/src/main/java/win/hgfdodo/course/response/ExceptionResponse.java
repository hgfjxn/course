package win.hgfdodo.course.response;

public class ExceptionResponse extends Response {

    public ExceptionResponse(String cause) {
        super(ResponseType.EXCEPTION_RESPONSE);
    }

    public ExceptionResponse(Exception e) {
        super(ResponseType.EXCEPTION_RESPONSE, e.getMessage());
    }

}
