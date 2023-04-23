package class4.spm.novelnook.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    String userid;
    String bookid;
    Date reservationtime;
    String status;


}
