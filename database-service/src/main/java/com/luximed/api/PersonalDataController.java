package com.luximed.api;

import com.luximed.model.PersonalData;
import com.luximed.repository.PersonalDataRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Objects.isNull;

@Tag(name = "personal data", description = "Personal data API")
@RestController
@RequestMapping(value = "api/database/personalData")
public class PersonalDataController {

    private final PersonalDataRepository personalDataRepository;

    public PersonalDataController(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<PersonalData> getAllPersonalData() {
        return personalDataRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{pesel}")
    public PersonalData getPersonalDataByPesel(@PathVariable String pesel) {
        return personalDataRepository.findPersonalDataByPesel(pesel);
    }

    @RequestMapping(method = RequestMethod.POST, value = "add")
    public void addPersonalData(@RequestBody @Valid PersonalData personalData) {
        personalDataRepository.save(personalData);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public ResponseEntity<String> deletePersonalData(@RequestParam String pesel) {
        if (isNull(personalDataRepository.findPersonalDataByPesel(pesel))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no personal data with pesel: " + pesel);
        }
        personalDataRepository.deleteByPesel(pesel);
        return ResponseEntity.status(HttpStatus.OK).body("Personal data deleted");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<String> updatePersonalData(@RequestBody PersonalData personalData) {
        if (isNull(personalDataRepository.findPersonalDataByPesel(personalData.getPesel()))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no personal data with pesel: " + personalData.getPesel());
        }
        String pass = personalDataRepository.findPersonalDataByPesel(personalData.getPesel()).getPassword();
        personalData.setPassword(pass);
        personalDataRepository.save(personalData);
        return ResponseEntity.status(HttpStatus.OK).body("Personal data updated");
    }
}
