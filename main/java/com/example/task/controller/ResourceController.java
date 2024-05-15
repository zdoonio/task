package com.example.task.controller;


import com.example.task.dao.ResourceDao;
import com.example.task.entity.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/resources")
public class ResourceController {

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
    private final ResourceDao resourceDao;

    public ResourceController(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }


    @GetMapping(value = "/showAll")
    @ResponseBody
    public String getResource() {
        List<Resource> res =  resourceDao.getAll();
        return res.stream()
                .map(resource -> resource.getId() + " " + resource.getName()+ " " + resource.getData().length)
                .collect(Collectors.joining("<br/>"));
    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST ,consumes = { "multipart/form-data" })
    @ResponseBody
    public String uploadResource(@RequestBody MultipartFile file) {
        logger.info("Odebrałem żądanie");
        if (!file.isEmpty()) {
            try {
                Resource resource = new Resource();
                logger.info("Przepisuje dane do obiektu.");
                resource.setName(file.getOriginalFilename());
                resource.setData(file.getBytes());
                resource.setSize(file.getBytes().length);
                resourceDao.saveResToDB(resource);
                return "Ządanie wysłano pomyślnie! Wątek: " + Thread.currentThread().getName();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return file.toString() + "Plik jest pusty";
        }
            return "Błąd wysyłania żądania.";
    }



}