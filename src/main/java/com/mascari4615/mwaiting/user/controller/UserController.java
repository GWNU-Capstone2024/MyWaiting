package com.mascari4615.mwaiting.user.controller;

import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.ticket.service.TicketService;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.controller.dto.UserRegisterDTO;
import com.mascari4615.mwaiting.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// @RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    // RestAPI
    // Representational State Transfer
    // HTTP Method : CRUD를 통해 표현한다
    // Stateless 모든 요청은 독립정인 요청이다

    // @GetMapping : 자원을 가져오는
    // @PutMapping
    // @PostMapping : 자원을 넣어주는
    // @DeleteMapping

    // 원래는 직접 만들어주고 해야하는데, 생성자 주입을 받는다
    private final UserService userService;
    private final TicketService ticketService;

    // Notation
    // @RequestMapping("/hello") -> Method를 지정하지 않으면 모든 Method에 대해 응답
    // @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    @GetMapping("/hello")
//    public String getHello() {
//        return "hello";
//    }

    @GetMapping("/")
    public String index(Model model) {

        // 사용자가 로그인을 진행한 뒤 사용자 정보는 SecurityContextHolder에 의해서 서버 세션에 관리된다.
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("email", email);
        model.addAttribute("role", role);

        System.out.println("email:" + email);
        System.out.println("role: " + role);

        if (email != null)
        {
            UserDTO userDTO = userService.findByEmail(email);

            List<TicketDTO> ticketDTOS = ticketService.findAll();

            for (TicketDTO ticketDTO : ticketDTOS) {
                if (Objects.equals(userDTO.getId(), ticketDTO.getUser().getId())) {
                    if (ticketDTO.getState() == TicketState.WAITING)
                        model.addAttribute("ticket", ticketDTO);
                }
            }
        }

        return "index";
    }

    // 회원가입 페이지 출력 요청
    @GetMapping("/user/register")
    public String registerForm() {
        return "user-register";
    }

    @PostMapping("/user/register")
    // public String join(@RequestBody JoinRequest joinRequest) {
    public String registerUser(@ModelAttribute UserRegisterDTO userRegisterDTO) {
        System.out.println("UserController.registerUser");
        System.out.println("UserRegisterDTO = " + userRegisterDTO);

        String result = userService.register(userRegisterDTO);

        // if (result.equalsIgnoreCase("success"))
        if ("success".equalsIgnoreCase(result)) // Null Exception 방지
        {
            return "redirect:/user/login";
        } else {
            return "fail";
        }

        // Talend API Tester
        // @valid, @validated
        // exceptionhandler, controlleradive
        // restTemplate, webClient
        // 탈퇴, 휴먼
    }

//    @PostMapping("/user/save")
//    public String saveUser(
//            @RequestParam("userEmail") String userEmail,
//            @RequestParam("userPassword") String userPassword,
//            @RequestParam("userName") String userName)
//    {
//        System.out.println("UserController.save");
//        System.out.println("userEmail = " + userEmail + ", userPassword = " + userPassword + ", userName = " + userName);
//        return "index";
//    }

    @GetMapping("/user/login")
    public String login() {
        return "user-login";
    }

//    @PostMapping("/user/login")
//    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
//        System.out.println("UserController.login");
//        System.out.println("userDTO = " + userDTO);
//        UserDTO loginResult = userService.login(userDTO);
//
//        if (loginResult != null) {
//            // 세션 : 정보 유지 (로그인 정보 유지)
//            session.setAttribute("userName", loginResult.getUserName());
//            System.out.println("userName = " + loginResult.getUserName());
//            System.out.println("userDTO = " + userDTO);
//            return "hello";
//        } else {
//            return "user-login";
//        }
//    }

    @GetMapping("/user/list")
    public String findAll(Model model) // 스프링에서 제공하는 객체 표현
    {
        List<UserDTO> userDTOList = userService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model 사용
        model.addAttribute("userList", userDTOList);
        return "user-list";
    }

    @GetMapping("/user/{id}")
    public String findById(@PathVariable Long id, Model model) {
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        return "user-detail";
    }
}