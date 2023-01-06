package com.gmail.guia2.controller;

import com.gmail.guia2.arq.ArquivoMestre;
import com.gmail.guia2.dto.Tab1;
import com.gmail.guia2.dto.Tab2;
import com.gmail.guia2.service.EmailSenderService;
import com.gmail.guia2.service.Tab1Service;
import com.gmail.guia2.service.Tab2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/tarefasTab2", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tab2Controller {
    @Autowired
    private Tab2Service tarefasFacade;

    @Autowired
    private EmailSenderService senderService;

    @PostMapping
    @ResponseBody
    public Tab2 criar(@RequestBody Tab2 tarefaDTO) throws Exception {
        double percurso;
        double transporte = 1;
        int carga;
        double custoTotal;
        Tab1Service aux = new Tab1Service();
        List<Tab1> lista = aux.getALL();
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de criar item na tabela 2");
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getVeiculo().equals(tarefaDTO.getVeiculoUtilizado())) {
                percurso = (tarefaDTO.getDistanciaPav() * 0.63) + (tarefaDTO.getDistanciaNaoPav() * 0.72);
                carga = tarefaDTO.getCarga();
                if (carga <= 5) {
                    transporte = lista.get(i).getFatorMult();
                } else {
                    transporte = lista.get(i).getFatorMult() + ((carga - 5) * 0.03);
                }
                custoTotal = percurso * transporte;
                tarefaDTO.setCustoTotal(custoTotal);
                senderService.sendEmail(tarefaDTO.getEmail(),
                        "Calculo do custo",
                        "O custo do percuso foi : "+ "R$"+percurso +
                                  "\n Seu custo de transporte total foi de: "+"R$"+ transporte +
                                  "\n Seu custo foi calculado (percurso x transporte) total foi de: "+ "R$"+custoTotal);
            }
        }


        return tarefasFacade.criar(tarefaDTO);
    }

    @PutMapping("/{tarefaId}")
    @ResponseBody
    public Tab2 atualizar(@PathVariable("tarefaId") Long tarefaID, @RequestBody Tab2 tarefaDTO) throws Exception {

        double percurso;
        double transporte = 1;
        int carga;
        double custoTotal;
        Tab1Service aux = new Tab1Service();
        List<Tab1> lista = aux.getALL();
        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de atualizar item na tabela 2");
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getVeiculo().equals(tarefaDTO.getVeiculoUtilizado())) {
                percurso = (tarefaDTO.getDistanciaPav() * 0.63) + (tarefaDTO.getDistanciaNaoPav() * 0.72);
                carga = tarefaDTO.getCarga();
                if (carga <= 5) {
                    transporte = lista.get(i).getFatorMult();
                } else {
                    transporte = lista.get(i).getFatorMult() + ((carga - 5) * 0.03);
                }
                custoTotal = percurso * transporte;
                tarefaDTO.setCustoTotal(custoTotal);
            }
        }
        return tarefasFacade.atualizar(tarefaDTO, tarefaID);
    }

    @GetMapping
    @ResponseBody
    public List<Tab2> getALL() throws Exception {

        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de listar todos os itens da tabela 2");
        return tarefasFacade.getALL();
    }

    @DeleteMapping("/{tarefaId}")
    @ResponseBody
    public String deletar(@PathVariable("tarefaId") Long tarefaId) throws Exception {

        ArquivoMestre arq = new ArquivoMestre();

        arq.escreveNoArq("Foi usada a função de deletar um item na tabela 2");
        return tarefasFacade.delete(tarefaId);
    }

}
