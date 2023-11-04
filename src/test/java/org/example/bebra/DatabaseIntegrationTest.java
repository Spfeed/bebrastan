import org.example.bebra.model.Doctor;
import org.example.bebra.model.MedicalHistory;
import org.example.bebra.model.Owner;
import org.example.bebra.repo.DoctorRepository;
import org.example.bebra.repo.MedicalHistoryRepository;
import org.example.bebra.repo.OwnerRepository;
import org.example.bebra.repo.PetRepository;
import org.example.bebra.sochniy.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DatabaseIntegrationTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void testDatabaseIntegration() {
        // Создаем врача
        Doctor doctor = new Doctor();
        doctor.setName("Имя доктора");
        doctor.setSurname("Фамилия доктора");
        doctor.setJob("Специализация");
        doctorRepository.save(doctor);

        // Создаем пациента
        Pet pet = new Pet();
        pet.setName("Кличка питомца");
        pet.setKind("Вид питомца");
        pet.setDoctor(doctor);
        petRepository.save(pet);

        // Создаем медицинскую историю
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDate(new Date());
        medicalHistory.setType("Тип истории");
        medicalHistory.setDescription("Описание истории");
        medicalHistory.setPet(pet);
        medicalHistoryRepository.save(medicalHistory);

        // Создаем владельца
        Owner owner = new Owner();
        owner.setName("Имя владельца");
        owner.setSurname("Фамилия владельца");
        owner.setFathername("Отчество владельца");
        ownerRepository.save(owner);

        // Чтение записей из базы данных и проверка результатов
        Doctor retrievedDoctor = doctorRepository.findById(doctor.getId()).orElse(null);
        assertNotNull(retrievedDoctor);
        assertEquals("Имя доктора", retrievedDoctor.getName());
        assertEquals("Фамилия доктора", retrievedDoctor.getSurname());
        assertEquals("Специализация", retrievedDoctor.getJob());

        Pet retrievedPet = petRepository.findById(pet.getId()).orElse(null);
        assertNotNull(retrievedPet);
        assertEquals("Кличка питомца", retrievedPet.getName());
        assertEquals("Вид питомца", retrievedPet.getKind());
        assertEquals(doctor.getId(), retrievedPet.getDoctor().getId());

        MedicalHistory retrievedMedicalHistory = medicalHistoryRepository.findById(medicalHistory.getId()).orElse(null);
        assertNotNull(retrievedMedicalHistory);
        assertEquals(medicalHistory.getDate(), retrievedMedicalHistory.getDate());
        assertEquals("Тип истории", retrievedMedicalHistory.getType());
        assertEquals("Описание истории", retrievedMedicalHistory.getDescription());
        assertEquals(pet.getId(), retrievedMedicalHistory.getPet().getId());

        Owner retrievedOwner = ownerRepository.findById(owner.getId()).orElse(null);
        assertNotNull(retrievedOwner);
        assertEquals("Имя владельца", retrievedOwner.getName());
        assertEquals("Фамилия владельца", retrievedOwner.getSurname());
        assertEquals("Отчество владельца", retrievedOwner.getFathername());
    }
}
