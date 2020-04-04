package com.dawes.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.dawes.modelo.ProveedorBO;
import com.dawes.servicios.ProveedorServicios;

@Controller
public class Controlador {

  @Autowired
  ProveedorServicios servicio;

//obejto que nos permite hacer las peticiones a la apiREST
  RestTemplate plantillaPeticiones = new RestTemplate();

  // Coje la ruta del inicio tras el login
  // pero devuelve otro html
  @RequestMapping("/inicio")
  public String inicio(Model modelo) {
    // generamos una peticion HTTP que nos devuelva los proveedores

    // 1ª MANERA
    // getForObject no permite enviar listas de objetos(podriamos crear un wrapper)
    // pero si arrays
    ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
        ProveedorBO[].class);
    // le pasamos todos los proveedores
    modelo.addAttribute("proveedores", proveedores);
    // ademas de pasar un proveedor vacio que posteriormente rellenaremos con el
    // formulario de creado.
    modelo.addAttribute("nuevoproveedor", new ProveedorBO());// para crear
    modelo.addAttribute("proveedoractualizado", new ProveedorBO());// para actualizar

    return "proveedores";
  }

  @RequestMapping("/eliminarProveedor")
  public String eliminar(Model modelo, @RequestParam("id") Integer id) {

    // tenemos que sustituir las variables de la url
    Map<String, Integer> variablesUrl = new HashMap<String, Integer>();
    variablesUrl.put("id", id);
    plantillaPeticiones.delete("http://localhost:8080/proveedor/{id}", variablesUrl);
    // le pasamos todos los proveedores otra vez
    ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
        ProveedorBO[].class);
    modelo.addAttribute("proveedores", proveedores);
    // ademas de pasar un proveedor vacio que posteriormente rellenaremos con el
    // formulario de creado.
    modelo.addAttribute("nuevoproveedor", new ProveedorBO());
    modelo.addAttribute("proveedoractualizado", new ProveedorBO());

    return "proveedores";
  }

  @RequestMapping(value = "/crearProveedor", method = RequestMethod.POST)
  // request atribute determinadmos el tipo del objeto que hace la peticion al
  // servidor(nombbre igual que el form)
  public String crear(@ModelAttribute("nuevoproveedor") ProveedorBO proveedorNuevo, Model modelo) {

    // determinamos el servicio al que hacemos post, el objeto que pasamos y el tipo
    // de la respuesta que esperamos
    plantillaPeticiones.postForObject("http://localhost:8080/proveedor", proveedorNuevo,
        String.class);
    // le pasamos todos los proveedores otra vez
    ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
        ProveedorBO[].class);
    modelo.addAttribute("proveedores", proveedores);
    // ademas de pasar un proveedor vacio que posteriormente rellenaremos con el
    // formulario de creado.
    modelo.addAttribute("nuevoproveedor", new ProveedorBO());
    modelo.addAttribute("proveedoractualizado", new ProveedorBO());

    return "proveedores";
  }

  @RequestMapping(value ="/actualizarProveedor")
  public String actualizar(@ModelAttribute("proveedoractualizado") ProveedorBO proveedor, Model modelo){

    //en la url tenemos que poner el id de este proveedor
    Map<String,Integer> parametros = new HashMap<String,Integer>();
    parametros.put("id", proveedor.getIdproveedor());
    //peticion de actualizacion (actualizamos el proveedor)
    plantillaPeticiones.put("http://localhost:8080/proveedor/{id}", proveedor,parametros);

    //datos enviados al inicio
    ProveedorBO[] proveedores = plantillaPeticiones.getForObject("http://localhost:8080/proveedores",
        ProveedorBO[].class);
    modelo.addAttribute("proveedores", proveedores);
    modelo.addAttribute("nuevoproveedor", new ProveedorBO());
    modelo.addAttribute("proveedoractualizado", new ProveedorBO());

    return "proveedores";
  }
}
