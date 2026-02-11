package auca.ac.rw.question6_user_profile_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.question6_user_profile_api.model.ApiResponse;
import auca.ac.rw.question6_user_profile_api.model.UserProfile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        // Pre-loading sample users
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software Developer", true));
        users.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 30, "UK", "Data Scientist", true));
        users.add(new UserProfile(3L, "mike_rwanda", "mike@example.com", "Mike Keza", 22, "Rwanda", "Student", false));
        users.add(new UserProfile(4L, "alice_fr", "alice@example.com", "Alice Dupont", 28, "France", "Artist", true));
        users.add(new UserProfile(5L, "david_music", "david@example.com", "David Rock", 35, "USA", "Musician", true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return new ResponseEntity<>(new ApiResponse<>(true, "Users fetched successfully", users), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                return new ResponseEntity<>(new ApiResponse<>(true, "User found", user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ResponseEntity<>(new ApiResponse<>(true, "User profile created successfully", user), HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long userId, @RequestBody UserProfile details) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(details.getUsername());
                user.setEmail(details.getEmail());
                user.setFullName(details.getFullName());
                user.setAge(details.getAge());
                user.setCountry(details.getCountry());
                user.setBio(details.getBio());
                return new ResponseEntity<>(new ApiResponse<>(true, "User updated successfully", user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found for update", null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        UserProfile toRemove = null;
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                toRemove = user;
                break;
            }
        }

        if (toRemove != null) {
            users.remove(toRemove);
            return new ResponseEntity<>(new ApiResponse<>(true, "User deleted successfully", null), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(false, "User not found for deletion", null), HttpStatus.NOT_FOUND);
        }
    }
 
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getUsername().toLowerCase().contains(username.toLowerCase())) {
                result.add(user);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results by username", result), HttpStatus.OK);
    }


    @GetMapping("/search/country")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@RequestParam String country) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results by country", result), HttpStatus.OK);
    }

    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAge(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = new ArrayList<>();
        for (UserProfile user : users) {
            if (user.getAge() >= min && user.getAge() <= max) {
                result.add(user);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(true, "Search results by age", result), HttpStatus.OK);
    }


    @PostMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                return new ResponseEntity<>(new ApiResponse<>(true, "User activated successfully", user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }
  
    @PostMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                return new ResponseEntity<>(new ApiResponse<>(true, "User deactivated successfully", user), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse<>(false, "User not found", null), HttpStatus.NOT_FOUND);
    }
}