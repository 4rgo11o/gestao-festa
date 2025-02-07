package com.algaworks.convidados.controller;

import com.algaworks.convidados.model.Convidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.algaworks.convidados.repository.Convidados;

import java.util.Optional;

@Controller
public class ConvidadoController {
    @Autowired
    private Convidados convidados;
    @GetMapping("/convidados")
    ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("ListaConvidados");
        modelAndView.addObject("convidados", convidados.findAll());
        modelAndView.addObject(new Convidado());
        return modelAndView;
    }

    @PostMapping("/convidados")
    public String salvar(Convidado convidado) {
        this.convidados.save(convidado);
        return "redirect:/convidados";
    }

    @PostMapping("/delete/{id}")
    public String deletar(@PathVariable Long id) {
        convidados.deleteById(id);
        return "redirect:/convidados";
    }

    @GetMapping("/update/{id}")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("UpdateConvidado");
        Optional<Convidado> convidado = convidados.findById(id);
        convidado.ifPresent(c -> modelAndView.addObject("convidado", c));
        return modelAndView;
    }

    @PostMapping("/update")
    public String atualizar(@ModelAttribute Convidado convidado) {
        if (convidados.existsById(convidado.getId())) {
            convidados.save(convidado);
        }
        return "redirect:/convidados";
    }
}
