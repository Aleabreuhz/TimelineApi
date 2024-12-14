package timelines.controller;

import timelines.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import timelines.repository.UsuarioRepository;
import timelines.ResourceNotFoundException;


import java.util.List;

@CrossOrigin(origins = "*") // Permite solicitudes desde Ionic
@RestController
@RequestMapping("/api/usuarios")
public class TimelineController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario crearUsuarios(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuariosPorId(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("") );
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuarios(@PathVariable("id") Long id, @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
        usuario.setId(detallesUsuario.getId());
        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setApellidos(detallesUsuario.getApellidos());
        usuario.setNombreUsuario(detallesUsuario.getNombreUsuario());
        usuario.setCorreoElectronico(detallesUsuario.getCorreoElectronico());
        usuario.setFechaDeNacimiento(detallesUsuario.getFechaDeNacimiento());
        usuario.setNumeroTelefono(detallesUsuario.getNumeroTelefono());
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public Usuario eliminarUsuarios(@PathVariable("id") Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("usuario no encontrado"));
        usuarioRepository.deleteById(id);
        return usuario;
    }
}
