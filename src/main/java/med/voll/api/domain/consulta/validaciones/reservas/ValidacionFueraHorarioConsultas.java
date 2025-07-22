package med.voll.api.domain.consulta.validaciones.reservas;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacionFueraHorarioConsultas implements ValidacionDeConsultas{
    public void  validar(DatosReservarConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;

        if(domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreClinica) {
            throw new ValidacionException("Horario seleccionado fuera del horario de atendimiento de la clinica");
        }
    }
}
