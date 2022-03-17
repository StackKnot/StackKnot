package com.codeup.stackknot.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.codeup.stackknot.models.Whiteboard;
import com.codeup.stackknot.repositories.UserRepository;
import com.codeup.stackknot.repositories.WhiteboardRepository;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping(value="/whiteboard")
public class WhiteboardController {


//    @Value("${COM_CLOUDINARY_CLOUD_NAME}")
//    String mCloudName;
//
//
//    @Value("${COM_CLOUDINARY_API_KEY}")
//    String mApiKey;
//
//
//    @Value("${COM_CLOUDINARY_API_SECRET}")
//    String mApiSecret;




    // DEPENDENCY INJECTION
    private UserRepository userDao;
    private WhiteboardRepository whiteboardDao;
    private String imageTag;

    public WhiteboardController(WhiteboardRepository whiteboardDao) {
        this.whiteboardDao = whiteboardDao;
    }

//     SHOW WHITEBOARD SECTION
    @GetMapping("/whiteboard")
    public String getStartedPage() {
        whiteboardDao.findAll();
        return "whiteboard/whiteboardStart";
    }

    @GetMapping("/whiteboard/{id}")
    public String showEditor(@PathVariable long id, Model model) {
        model.addAttribute("whiteboard", whiteboardDao.getById(id));
        return "whiteboard/whiteboard";
    }

//     ADMIN UPLOAD ABILITY
    @GetMapping("/whiteboard/upload")
    public String uploadSolutionForm(Model model) {
        model.addAttribute("newBoard", new Whiteboard());
        return "whiteboard/upload";
    }

    @PostMapping("/whiteboard/upload")
    public String uploadSolution(@ModelAttribute Whiteboard whiteboard) {
        whiteboardDao.save(whiteboard);
        return "redirect:/whiteboard";
    }
}
