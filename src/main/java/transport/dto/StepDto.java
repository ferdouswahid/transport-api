package transport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class StepDto {

    private String line;
    private String hour;
    private String realtime;

}
