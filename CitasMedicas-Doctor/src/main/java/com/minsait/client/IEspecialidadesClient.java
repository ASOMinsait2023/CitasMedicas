package com.minsait.client;

import com.minsait.dtos.EspecialidadesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-especialidades", url = "http://localhost:7095/api/v1/especialidades")
public interface IEspecialidadesClient {

    @GetMapping("/search-by-especialidad/{idDoctor}")
    List<EspecialidadesDTO> findByIdEspecialidad (@PathVariable Long idDoctor);

}
