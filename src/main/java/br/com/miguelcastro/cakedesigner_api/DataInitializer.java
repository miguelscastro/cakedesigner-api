package br.com.miguelcastro.cakedesigner_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.miguelcastro.cakedesigner_api.enums.UserRole;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserEntity;
import br.com.miguelcastro.cakedesigner_api.modules.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    if (userRepository.count() == 0) {
      UserEntity admin = new UserEntity();
      admin.setName("admin");
      admin.setPassword(passwordEncoder.encode("Teste@123A"));
      admin.setEmail(("admin@gmail.com"));
      admin.setRole(UserRole.ADMIN);
      userRepository.save(admin);
      System.out.println("Admin user created.");
    }
  }
}
