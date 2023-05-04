package class4.spm.novelnook.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Returned {

    String borrowid;

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date returntime;

    int fineamount;

    boolean ispay;



}
