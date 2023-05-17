package class4.spm.novelnook.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRecords {

    String borrowid;
    int bookid;
    String bookname;
    String location;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date borrowtime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date   deadline;
    String status;

}
