package transport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class StopInfo {

    @Id
    private String id;
    private String lat;
    private String lng;
    @Column(name = "lines_info")
    private String lines;
    private String location;
    private String name;
    private String placeName;
    private String type;

}
