package class4.spm.novelnook.controller;


import class4.spm.novelnook.pojo.Book;
import class4.spm.novelnook.pojo.Patron;
import class4.spm.novelnook.service.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    StaffServiceImpl staffServiceImpl;




}
