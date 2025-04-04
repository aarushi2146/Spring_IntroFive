package com.example.address_book_apps.service;

import com.example.address_book_apps.dto.UserDTO;
import com.example.address_book_apps.model.User;
import com.example.address_book_apps.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import com.example.address_book_apps.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register User
    @Override
    public String registerUser(UserDTO userdto) {
        try {
            log.info("Registering new user: {}", userdto.getEmail());
            if (userRepository.existsByEmail(userdto.getEmail())) {
                return "Email is already in use!";
            }

            User user = new User();
            user.setUsername(userdto.getName());
            user.setEmail(userdto.getEmail());
            user.setPassword(passwordEncoder.encode(userdto.getPassword())); // Encrypt password
            userRepository.save(user);

            // Send welcome email
            String subject = "Welcome to Our Platform!";
            String body = "<h1>Hello " + userdto.getName() + "!</h1>"
                    + "<p>Thank you for registering on our platform.</p>";
            emailService.sendEmail(user.getEmail(), subject, body);

            log.info("User {} registered successfully.", user.getEmail());

            // Clear Redis cache when a new user is registered
            clearUserCache(user.getEmail());

            return "User registered successfully!";
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage(), e);
            return "Error registering user.";
        }
    }

    // Authenticate User and Generate Token
    @Override
    @Cacheable(value = "users", key = "#email")  // Cache user authentication
    public String authenticateUser(String email, String password) {
        try {
            log.info("Login attempt for email: {}", email);
            Optional<User> userOpt = userRepository.findByEmail(email);

            if (userOpt.isEmpty()) {
                log.warn("Login failed: No user found for email: {}", email);
                return "User not found!";
            }

            User user = userOpt.get();

            if (!passwordEncoder.matches(password, user.getPassword())) {
                log.warn("Login failed: Incorrect password for email: {}", email);
                return "Invalid email or password!";
            }

            log.info("Login successful for user: {}", email);
            return jwtUtil.generateToken(email);
        } catch (Exception e) {
            log.error("Error during authentication: {}", e.getMessage(), e);
            return "Authentication error!";
        }
    }

    // Forgot Password Implementation
    @Override
    @CacheEvict(value = "users", key = "#email")  // Remove cached user data when password is updated
    public String forgotPassword(String email, String newPassword) {
        try {
            log.info("Processing forgot password request for email: {}", email);
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isEmpty()) {
                log.warn("Forgot password request failed: No user found for email: {}", email);
                return "Sorry! We cannot find the user email: " + email;
            }

            User user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Send email notification
            String subject = "Password Change Notification";
            String content = "<h2>Hello " + user.getUsername() + ",</h2>"
                    + "<p>Your password has been changed successfully.</p>";
            emailService.sendEmail(user.getEmail(), subject, content);

            log.info("Password updated successfully for email: {}", email);
            return "Password has been changed successfully!";
        } catch (Exception e) {
            log.error("Error processing forgot password request: {}", e.getMessage(), e);
            return "Error processing forgot password request.";
        }
    }

    // Reset Password Implementation
    @Override
    @CacheEvict(value = "users", key = "#email")  // Clear cache after password reset
    public String resetPassword(String email, String currentPassword, String newPassword) {
        try {
            log.info("Resetting password for email: {}", email);
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isEmpty()) {
                log.warn("Password reset failed: No user found for email: {}", email);
                return "User not found with email: " + email;
            }
            User user = userOpt.get();

            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.warn("Password reset failed: Incorrect current password for email: {}", email);
                return "Current password is incorrect!";
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Send email notification
            String subject = "Password Reset Notification";
            String content = "<h2>Hello " + user.getUsername() + ",</h2>"
                    + "<p>Your password has been reset successfully.</p>";
            emailService.sendEmail(user.getEmail(), subject, content);

            log.info("Password reset successful for email: {}", email);
            return "Password reset successfully!";
        } catch (Exception e) {
            log.error("Error resetting password: {}", e.getMessage(), e);
            return "Error resetting password.";
        }
    }

    // Clear user cache after an update
    private void clearUserCache(String email) {
        try {
            log.info("Clearing Redis cache for user: {}", email);
        } catch (Exception e) {
            log.error("Error clearing cache for user {}: {}", email, e.getMessage(), e);
        }
    }
}
