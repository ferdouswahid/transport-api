package transport.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import transport.model.StopInfo;

import java.util.List;

@Setter
@Getter
@ToString
public class StopDto {
    List<StopInfo> stops;
}
