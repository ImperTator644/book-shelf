package com.luximed.frontservice.controller;

import com.luximed.frontservice.client.ClientService;
import com.luximed.frontservice.client.ClinicClient;
import com.luximed.frontservice.dto.Gender;
import com.luximed.frontservice.dto.PatientDto;
import com.luximed.frontservice.dto.PersonalDataDto;
import com.luximed.frontservice.model.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ClinicClient clinicClient;
    private final ClientService clientService;
    private final CurrentUser currentUser;

    @GetMapping("patient/all")
    @ResponseBody
    public List<PatientDto> getPatients() {
        return clinicClient.getPatients();
    }

    @GetMapping("profile")
    public ModelAndView getPatient() {
        ModelAndView mav = new ModelAndView("user-profile");
        PersonalDataDto dataDto = clientService.getPersonalDataByPesel(currentUser.getUserName());
        mav.addObject("user", dataDto);
        return mav;
    }

    @GetMapping("profile-edit")
    public ModelAndView getPatientEdit() {
        ModelAndView mav = new ModelAndView("user-profile-edit");
        PersonalDataDto dataDto = clientService.getPersonalDataByPesel(currentUser.getUserName());
        mav.addObject("user", dataDto);
        return mav;
    }

    @PostMapping("profile/edit")
    public ModelAndView updateProfile(@RequestParam String name,
                                      @RequestParam String surname,
                                      @RequestParam String mail,
                                      @RequestParam String phone,
                                      @RequestParam String gender,
                                      @RequestParam String pesel) {
        PersonalDataDto personalData = new PersonalDataDto();
        personalData.setName(name);
        personalData.setSurname(surname);
        personalData.setMail(mail);
        personalData.setPhone(phone);
        personalData.setGender(Gender.valueOf(gender));
        personalData.setUsername(pesel);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8083/api/database/personalData/update";
        HttpMethod httpMethod = HttpMethod.PUT;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PersonalDataDto> requestEntity = new HttpEntity<>(personalData, headers);
        restTemplate.exchange(url, httpMethod, requestEntity, String.class);

        ModelAndView mav = new ModelAndView("user-profile");
        PersonalDataDto dataDto = clientService.getPersonalDataByPesel(currentUser.getUserName());
        mav.addObject("user", dataDto);
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, value = "my-calendar")
    public ModelAndView getMyCalendar() {
        ModelAndView mav = new ModelAndView("calendar");
        mav.addObject("pesel", currentUser.getUserName());
        return mav;
    }

}
