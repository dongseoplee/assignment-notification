package mobile.gachonapp.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;


public interface Response {

    int getStatusCode();
    String getMessage();
}
