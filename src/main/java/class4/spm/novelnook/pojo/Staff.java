package class4.spm.novelnook.pojo;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Repository
public class Staff {
    int userid;
    String password;
    String firstname;
    String lastname;
    String telephone;
    String email;

    //4.30新加，在数据库中存储头像信息，
    String avatar;


/*  例子：
    Staff对象1
    username: "john_doe"
    password: "123456"
    firstname: "John"
    lastname: "Doe"
    telephone: "1234567890"
    email: "john_doe@example.com"
    avatar: "/path/to/avatar1.jpg"


    Staff对象2
    username: "jane_smith"
    password: "qwerty"
    firstname: "Jane"
    lastname: "Smith"
    telephone: "0987654321"
    email: "jane_smith@example.com"
    avatar: "/path/to/avatar2.jpg"

    Staff对象3
    username: "jimmy_nguyen"
    password: "abc123"
    firstname: "Jimmy"
    lastname: "Nguyen"
    telephone: "5555555555"
    email: "jimmy_nguyen@example.com"
    avatar: "/path/to/avatar3.jpg"*/
}
