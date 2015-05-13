package test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("Hello world!", HttpStatus.OK);
	}

}
