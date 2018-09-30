package win.hgfdodo.course.response;

public enum ResponseType {
    EXCEPTION_RESPONSE(false),
    LOGIN_SUCCESS(true),
    USERNAME_OR_PASSWORD_MISSING(false),
    PASSWORD_INCORRECT(false),
    VERIFYCODE_IS_MISSING_OR_OUT_OF_TIME(false),
    SIGNUP_SUCCESS(true),
    EMAIL_PHONE_BOTH_EMPTY(false),
    USERNAME_EXISTS(false),
    VERIFYCODE_SEND_SUCCESS(true),
    ;

    private String describe;
    private boolean positive;

    ResponseType(boolean positive) {
        this.positive = positive;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ResponseType{" +
                "describe='" + describe + '\'' +
                ", positive=" + positive +
                '}';
    }
}
