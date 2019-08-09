package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppController {
    @RequestMapping(value = "/factmap", method = RequestMethod.GET)
    public String getWelcome() {
        return "factmap";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String submit(@RequestParam("files") MultipartFile[] files, ModelMap modelMap) {
        modelMap.addAttribute("files", files);
        return "index";
    }
}