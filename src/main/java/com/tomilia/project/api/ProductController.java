package com.tomilia.project.api;

import com.tomilia.project.model.Compo_Product_Info;
import com.tomilia.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1")
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public Compo_Product_Info getMemberByID(@RequestParam("p_code") String p_code) {
        return productService.getMemberByID(p_code);
    }
    @RequestMapping(value="/transfer", method=RequestMethod.POST)
    public String transferInventory(@RequestParam("p_code") String p_code,
                                    @RequestParam("amount") int amount,
                                    @RequestParam("from_location") String from,
                                    @RequestParam("to_location") String to) {
        return productService.transferInventory(p_code,amount,from,to);
       // return memberService.getMemberByID(p_code);
    }


}
