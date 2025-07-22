package med.voll.api.domain.consulta.validaciones.reservas;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo implements ValidacionDeConsultas{

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservarConsulta datos){
        if (datos.idMedico() == null){
            return;
        }

        var medicoEstaActivo = repository.findActivoById(datos.idMedico());

        if (!medicoEstaActivo){
            throw new ValidacionException("Consulta no puede ser reservada con medico inactivo");
        }
    }
}
