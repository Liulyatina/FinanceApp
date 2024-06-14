//package by.it_academy.finance_management_user.service;
//
//import by.it_academy.finance_management_user.core.dto.UserDTO;
//import by.it_academy.finance_management_user.core.enums.UserRole;
//import by.it_academy.finance_management_user.core.enums.UserStatus;
//import by.it_academy.finance_management_user.service.api.IUserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdminSetUp implements CommandLineRunner {
//    private final IUserService userService;
//
//    public AdminSetUp(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void run(String... args){
//
//        UserDTO admin = UserDTO
//                .builder()
//                .mail(System.getenv("MAIL_LOGIN"))
//                .fio(System.getenv("ADMIN_FIO"))
//                .role(UserRole.ADMIN)
//                .status(UserStatus.ACTIVATED)
//                .password(System.getenv("ADMIN_PASSWORD"))
//                .build();
//
//        this.userService.create(admin);
//    }
//}
