package app.factMap.factMapControllers;


import app.factMap.factMapServices.FactMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@ComponentScan("factMapServices")
public class FactMapController {

    private final FactMapService factMap;

    @Autowired
    public FactMapController(FactMapService factMap) {
        this.factMap = factMap;
    }

    @RequestMapping(value = "/factmap", method = RequestMethod.GET)
    public String getWelcome() {
        return "factmap";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String submit(@RequestParam("file") MultipartFile file) throws IOException {

        factMap.readCpdXFile(file);
        return file.getOriginalFilename();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class BadRequestException extends RuntimeException {
        //
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        //
    }

}