package br.senac.tads.dsw.filmes;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("filmes", filmeService.listarFilmes());
        return "listagem";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("filme", new Filme());
        return "formulario";
    }

    @PostMapping
    public String salvar(@Valid Filme filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formulario";
        }
        filmeService.salvarFilme(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Optional<Filme> filmeOpt = filmeService.buscarPorId(id);
        if (filmeOpt.isPresent()) {
            model.addAttribute("filme", filmeOpt.get());
            return "formulario";
        }
        return "redirect:/filmes";
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @Valid Filme filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "formulario";
        }
        filme.setId(id);
        filmeService.salvarFilme(filme);
        return "redirect:/filmes";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return "redirect:/filmes";
    }
}
