package med.voll.api.paciente;

import med.voll.api.direccion.Direccion;

public record DatosRegistroPaciente(String nombre,
                                    String email,
                                    String telefono,
                                    String documento_identidad,
                                    Direccion direccion) {
}
