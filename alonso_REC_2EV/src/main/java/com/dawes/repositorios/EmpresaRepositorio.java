package com.dawes.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelos.EmpresaVO;
@Repository
public interface EmpresaRepositorio extends CrudRepository<EmpresaVO, Integer> {

}
