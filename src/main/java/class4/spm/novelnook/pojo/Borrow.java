package class4.spm.novelnook.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
//import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {

    String borrowid;
    int userid;
    int bookid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date   borrowtime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date   deadline;
    String status;

}
