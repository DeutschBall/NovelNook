package class4.spm.novelnook.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    int userid;
    int bookid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date reservationtime;
    String status;


}
