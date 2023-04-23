package class4.spm.novelnook.pojo;
import lombok.*;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Repository
public class Staff {
    String username;
    String password;
    String firstname;
    String lastname;
    String telephone;
    String email;
    String avatar;
}
