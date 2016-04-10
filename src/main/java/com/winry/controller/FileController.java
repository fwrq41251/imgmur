package com.winry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

/**
 * Created by cong on 2016/3/29.
 */
@Controller
public class FileController {

    @RequestMapping(value = "files/{year}/{month}/{day}/{name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] viewFile(@PathVariable String year, @PathVariable String month, @PathVariable String day, @PathVariable String name) throws IOException {
        String rawPath = MessageFormat.format("files/{0}/{1}/{2}/{3}", year, month, day, name);
        Path path = Paths.get(rawPath);
        return Files.readAllBytes(path);
    }
}
