package class4.spm.novelnook.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class admin {

    int userid;
    String password;
    String firstname;
    String lastname;
    String telephone;
    String email;
    String avatar;
}
