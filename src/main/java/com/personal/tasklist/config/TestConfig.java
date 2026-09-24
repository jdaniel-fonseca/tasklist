package com.personal.tasklist.config;
import com.personal.tasklist.dto.auth.RegisterDTO;
import com.personal.tasklist.dto.request.TaskRequestDTO;
import com.personal.tasklist.dto.response.TaskResponseDTO;
import com.personal.tasklist.dto.response.UserResponseDTO;
import com.personal.tasklist.entitites.Task;
import com.personal.tasklist.entitites.User;
import com.personal.tasklist.repositories.TaskRepository;
import com.personal.tasklist.repositories.UserRepository;
import com.personal.tasklist.services.RegisterService;
import com.personal.tasklist.services.TaskService;
import com.personal.tasklist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TestConfig implements CommandLineRunner {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        // 1. Criar o Usuário através do Serviço
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setName("Jose Daniel");
        registerDTO.setEmail("jdaniel@gmail.com");
        registerDTO.setAge(18);
        registerDTO.setPassword(passwordEncoder.encode("senhateste123@"));

        UserResponseDTO userDTO = registerService.create(registerDTO);

        // 2. Buscar a Entidade de Domínio pelo ID retornado pelo DTO
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 3. Criar e Persistir a Task associando a Entidade User
        Task task = new Task();
        task.setTitle("Ingles");
        task.setAnnotation("Aula de ingles no final de semana 27/09.");
        task.setUser(user);

        taskRepository.save(task);
    }
}
