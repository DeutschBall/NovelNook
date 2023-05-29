package class4.spm.novelnook.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
/*import jakarta.persistence.Id;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

/*    @Id*/
    int reservationid;
    int userid;
    int bookid;
    String bookname;
    String location;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date reservationtime;
    String status;


    public Reservation( int userid, int bookid, Date reservationtime, String status) {
        this.userid = userid;
        this.bookid = bookid;
        this.reservationtime = reservationtime;
        this.status = status;
    }

}
