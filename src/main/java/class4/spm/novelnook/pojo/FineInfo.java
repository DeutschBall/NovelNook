package class4.spm.novelnook.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineInfo {

    String borrowid;

    int userid;

    String bookname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date borrowtime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date deadline;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date returntime;

    int fineamount;



}