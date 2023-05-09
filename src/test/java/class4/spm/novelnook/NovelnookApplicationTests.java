package class4.spm.novelnook;

import class4.spm.novelnook.service.PatronServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NovelnookApplicationTests {

	@Autowired
	PatronServiceImpl patronServiceImpl;

	@Test
	void contextLoads() {
		System.out.println(patronServiceImpl.updateBorrow(1,2));
	}

}
