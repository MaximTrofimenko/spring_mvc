package com.trofimenko.controller;

import com.trofimenko.entites.Person;
import com.trofimenko.entites.Product;
import com.trofimenko.service.PersonService;
import com.trofimenko.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    public ProductService productService;
    public PersonService personService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(path="/add", method=RequestMethod.GET)
    public String addProduct(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product",product);
        return "add";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String addProductResult(Product product) {
        productService.addProduct(product);
        return "redirect:/add";
    }


    @RequestMapping ("/list")
    public String showAllProducts(Model uiModel){
        List<Product> list = productService.getAllProductsList();
        uiModel.addAttribute("list",list);
        return "list";
    }


    @RequestMapping(path="/id", method=RequestMethod.GET)
    public String getByIdProduct(Model uiModel,@ModelAttribute("product")Product product){
        uiModel.addAttribute("product",product);
        return "id";
    }

    @RequestMapping(path="/id", method = RequestMethod.POST)
    public String getByIdProductResult(Model uiModel,@RequestParam int id){
        Product product = productService.getProductById(id);
        uiModel.addAttribute("product",product);
        return "idResult";
    }

    @RequestMapping(path="/deleteById", method=RequestMethod.GET)
    public String delByIdProduct(Model uiModel,@ModelAttribute("product")Product product){
        uiModel.addAttribute("product",product);
        return "deleteById";
    }

    @RequestMapping(path="/deleteById", method = RequestMethod.POST)
    public String delByIdProductResult(Model uiModel,@RequestParam int id){
        productService.removeById(id);
        return "index";
    }



    @RequestMapping(path="/getProductsByPersonId", method=RequestMethod.GET)
    public String getProductsByPersonId(Model uiModel,@ModelAttribute("product")Person person){
        uiModel.addAttribute("person",person);
        return "getProductsByPersonId";
    }

    @RequestMapping(path="/getProductsByPersonId", method=RequestMethod.POST)
    public String getProductsByPersonIdResult(Model uiModel,@RequestParam int id){

        List<Product> list = personService.getProductsByPersonId(id);

        uiModel.addAttribute("list",list);
        return "getProductsByPersonIdResult";
    }


    @RequestMapping ("/cost")
    public String cost(Model uiModel){
        List<Product> list = productService.findProductByCost(100);
        uiModel.addAttribute("list",list);
        return "list";
    }

    @RequestMapping ("/findId")
    public String findId(Model uiModel){
        List<Integer> list = productService.findId(100);
        uiModel.addAttribute("list",list);
        return "findId";
    }







//
//
//
//
//    @RequestMapping(path="/getProductsByPersonId", method=RequestMethod.GET)
//    public String getProductsByPersonId(){
//
//        List<Product> l = personService.getProductsByPersonId(1);
//        System.out.println("хуй");
//
//        for (Product p:l) {
//            System.out.println(p.toString());
//        }
//
//        return "add";
//    }





//json
//    @RequestMapping(path = "/showProductById", method = RequestMethod.GET)
//    @ResponseBody
//    public Product showStudentById(Model uiModel, @RequestParam int id) {
//        return productService.getProductById(id);
//    }
//
//    @RequestMapping(path = "/showProductById/{sid}", method = RequestMethod.GET)
//    @ResponseBody
//    public Product showStudentById(@PathVariable("sid") int id) {
//        return productService.getProductById(id);
//    }





}
