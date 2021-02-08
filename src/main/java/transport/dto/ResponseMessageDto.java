package transport.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ResponseMessageDto <T>{
    private Boolean status;
    private String message;
    private T data;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public ResponseMessageDto(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseMessageDto(Boolean status, String message, T data, int currentPage, long totalItems, int totalPages) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }
}
