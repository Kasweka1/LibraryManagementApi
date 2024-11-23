package com.onesa.lms.LibraryManagementApi.domain.shelf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.domain.shelf.service.ShelfService;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    @Autowired 
    private ShelfService shelfService;

    
}
