package hello.hellospring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello!");
        // model attribute의 데이터가 hello.html에서 thymeleaf에 의해 data attribute를 hello로 치환함.
        return "hello";
        // spring-boot-devtools 라이브러리 -> html 컴파일시 서버 재시작 없이 뷰파일 변경 가능
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // hello spring
        // return 한 것이 그대로 html body로 전송됨.
    }

    @GetMapping("hello-api")
    @ResponseBody // API 방식 -> 객체를 반환 (JSON으로 Jackson 라이브러리에 의해 자동 변환)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
