package com.tss.URL_Shortening.controller.admin;


import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.service.AdminUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
@AllArgsConstructor
public class AdminUserController {


        private final AdminUserService adminUserService;

        @GetMapping
        public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
            return ResponseEntity.ok(
                    adminUserService.getAllUsers(pageable)
            );
        }

        @GetMapping("/{userId}")
        public ResponseEntity<User> getUser(
                @PathVariable Long userId) {

            return ResponseEntity.ok(
                    adminUserService.getUserByUserId(userId)
            );
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<Void> deleteUser(
                @PathVariable Long userId) {

            adminUserService.deleteUserById(userId);

            return ResponseEntity.noContent().build();
        }

}
