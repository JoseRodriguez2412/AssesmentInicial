package com.aplication.prco.data;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
//Definimos la clase como una clase incrustada o compuesta
@Embeddable
//Esta clase implementa la serialización de objetos
public class RegistroAsistenciaId implements Serializable {
    //Se define el código de serialización, que se utiliza por convención en Java para identificar la versión de la clase
    private static final long serialVersionUID = 7920772106078036867L;

    //Atributos que componen la llave primaria compuesta
    private Integer usuarioId;
    private Integer sesionId;

    //Comparación y búsqueda precisa de los objetos (usuarios)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegistroAsistenciaId entity = (RegistroAsistenciaId) o;
        return Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.sesionId, entity.sesionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, sesionId);
    }
}
