package class4.spm.novelnook.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    String bookid;
    String bookname;
    String press;
    String author;
    String publishtime;
    String catagory;
    int remain;

    String introduction;

}
