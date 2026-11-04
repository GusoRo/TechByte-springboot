
package com.TechByte.TechByte.controller;

import com.TechByte.TechByte.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class controller {

    @GetMapping("produto")
    public Produto show() {

        Produto produto = new Produto(
                "Mouse",
                "Mouse gamer",
                new BigDecimal("150.00")
        );

        return produto;
    }
}
