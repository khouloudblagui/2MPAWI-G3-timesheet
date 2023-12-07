package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.dto.ContratDto;
import tn.esprit.spring.entities.Contrat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class ContratServiceImplTest {

    @Autowired
    ContratService cn;



    @Test
    @Order(1)
    void testAddContrat() {
        try {
            log.info("Starting testAddContrat...");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = dateFormat.parse("2023-01-01");

            ContratDto contratDto = new ContratDto(d, "CDI", 5000.0f);

            Contrat contratAdded = cn.addContrat(contratDto);

            Assertions.assertNotNull(contratAdded);
            Assertions.assertEquals(contratDto.getDateDebut(), contratAdded.getDateDebut());
            Assertions.assertEquals(contratDto.getTypeContrat(), contratAdded.getTypeContrat());
            Assertions.assertEquals(contratDto.getSalaire(), contratAdded.getSalaire());
            log.info("Contrat successfully added with ID: {}", contratAdded.getReference());
            log.info("Test testAddContrat completed successfully");

        } catch (Exception e) {
            log.error("Error in testAddContrat", e);
        }
    }

    @Test
    @Order(3)
    void testUpdateContrat() {
        try {
            log.info("Starting testUpdateContrat...");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newDateDebut = dateFormat.parse("2023-05-01");

            Contrat existingContrat = cn.retrieveAllContrats().get(0);

            // Mettez à jour tous les champs du contrat
            ContratDto contratDto = new ContratDto(newDateDebut, "CDD", 6000.0f);
            contratDto.setReference(existingContrat.getReference());

            Contrat contratUpdated = cn.updateContrat(contratDto);

            Assertions.assertNotNull(contratUpdated);
            Assertions.assertEquals(newDateDebut, contratUpdated.getDateDebut());
            Assertions.assertEquals("CDD", contratUpdated.getTypeContrat());
            Assertions.assertEquals(6000.0f, contratUpdated.getSalaire());
            log.info("Contrat successfully updated with ID: {}", contratUpdated.getReference());

            log.info("Test testUpdateContrat completed successfully");
        } catch (Exception e) {
            log.error("Error in testUpdateContrat", e);
        }
    }

    @Test
    @Order(4)
    void testDeleteContrat() {
        try {
            log.info("Starting testDeleteContrat...");
            List<Contrat> contratsBeforeDelete = cn.retrieveAllContrats();
            if (!contratsBeforeDelete.isEmpty()) {
                Long referenceToDelete = contratsBeforeDelete.get(0).getReference();
                cn.deleteContrat(referenceToDelete);
                List<Contrat> contratsAfterDelete = cn.retrieveAllContrats();
                Assertions.assertEquals(contratsBeforeDelete.size() - 1, contratsAfterDelete.size());
                log.info("Test testDeleteContrat completed successfully.");

            } else {
                Assertions.fail("No contracts available for deletion");
            }
        }
        catch (Exception e) {
            log.error("Error of testDeleteContrat", e);
        }
    }


}