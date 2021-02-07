package transport.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageDto <T>{
    private Boolean status;
    private String message;
    private T data;
}
