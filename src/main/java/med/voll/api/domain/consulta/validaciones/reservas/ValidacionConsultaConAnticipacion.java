package med.voll.api.domain.consulta.validaciones.reservas;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacionConsultaConAnticipacion implements ValidacionDeConsultas{

    public void validar(DatosReservarConsulta datos){
        var ahora = LocalDateTime.now();
        var fechaConsulta = datos.fecha();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

        if(diferenciaEnMinutos < 30) {
            throw new ValidacionException("Horario seleccionado con menos de 30 minutos de anticipacion");
        }
    }
}
