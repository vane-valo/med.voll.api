package med.voll.api.domain.consulta.validaciones.reservas;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionPacienteActivo implements ValidacionDeConsultas{

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservarConsulta datos){
        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());

        if (!pacienteEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
