package org.example.rest.controller;

import org.example.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    @RequestMapping(
            value = "/hello/{nome}",
            method = RequestMethod.POST,
            consumes = { "application/json", "application/xml"},
            produces = { "application/json", "application/xml"}
    )
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCLiente, @RequestBody Cliente cliente) {
        return String.format("Hello %s ", nomeCLiente);
    }
}
