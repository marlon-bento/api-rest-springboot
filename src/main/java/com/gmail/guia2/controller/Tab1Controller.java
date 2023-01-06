package com.gmail.guia2.controller;

import com.gmail.guia2.arq.ArquivoMestre;
import com.gmail.guia2.dto.Tab1;
import com.gmail.guia2.service.Tab1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/tarefasTab1", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tab1Controller {
    @Autowired
    private Tab1Service tarefasFacade;


    @PostMapping
    @ResponseBody
    public Tab1 criar(@RequestBody Tab1 tarefaDTO) throws Exception {
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de adicionar um novo veiculo na tebela 1");
        return tarefasFacade.criar(tarefaDTO);
    }

    @PutMapping("/{tarefaId}")
    @ResponseBody
    public Tab1 atualizar(@PathVariable("tarefaId") Long tarefaID, @RequestBody Tab1 tarefaDTO) throws Exception {
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de ATUALIZAR um item na tabela 1");
        return tarefasFacade.atualizar(tarefaDTO, tarefaID);
    }

    @GetMapping
    @ResponseBody
    public List<Tab1> getALL() throws Exception {
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de Recuperar todos dados da tabela 1");
        return tarefasFacade.getALL();
    }

    @DeleteMapping("/{tarefaId}")
    @ResponseBody
    public String deletar(@PathVariable("tarefaId") Long tarefaId) throws Exception {
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de deletar um item na tabela 1");
        return tarefasFacade.delete(tarefaId);
    }

}
