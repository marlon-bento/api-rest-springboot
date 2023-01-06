package com.gmail.guia2.service;

import com.gmail.guia2.dto.Tab2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Tab2Service {

    private static final Map<Long, Tab2> tarefas = new HashMap<>();

    public Tab2 criar(Tab2 tarefaDTO) {
        Long proximoId = tarefas.keySet().size() + 1L;
        tarefaDTO.setId(proximoId);
        tarefas.put(proximoId, tarefaDTO);

        return tarefaDTO;
    }

    public Tab2 atualizar(Tab2 tarefaDTO, Long tarefaId) {
        tarefas.put(tarefaId, tarefaDTO);
        return tarefaDTO;
    }

    public Tab2 getById(Long tarefaId) {
        return tarefas.get(tarefaId);
    }

    public List<Tab2> getALL() {
        return new ArrayList<>(tarefas.values());
    }

    public String delete(Long tarefaId) {
        tarefas.remove(tarefaId);
        return "DELETED";
    }
}
