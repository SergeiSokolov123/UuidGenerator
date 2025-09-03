package org.example.controllers;

import org.example.logic.UuidService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UuidController {

    private final UuidService uuidService;

    public UuidController(UuidService uuidService) {
        this.uuidService = uuidService;
    }

    @GetMapping("/")
    public String index(Model model) {
        String uuid = uuidService.generateRandomUuid();
        model.addAttribute("uuid", uuid);
        model.addAttribute("uuids", new ArrayList<String>());
        model.addAttribute("count", 1);
        return "index";
    }

    @PostMapping("/generate")
    public String generateNewUuid(Model model,
                                  @RequestParam(value = "count", defaultValue = "1") String countParam) {

        int count = 1;

        try {
            count = Integer.parseInt(countParam);
        } catch (NumberFormatException e) {
            count = 1;
        }

        if (count < 1) count = 1;
        if (count > 50) count = 50;

        if (count == 1) {
            String uuid = uuidService.generateRandomUuid();
            model.addAttribute("uuid", uuid);
            model.addAttribute("uuids", new ArrayList<String>());
        } else {
            List<String> uuids = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                uuids.add(uuidService.generateRandomUuid());
            }
            model.addAttribute("uuid", null);
            model.addAttribute("uuids", uuids);
        }

        model.addAttribute("count", count);

        return "index";
    }
}
